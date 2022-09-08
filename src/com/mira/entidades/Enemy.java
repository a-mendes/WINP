package com.mira.entidades;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.mira.main.Game;
import com.mira.mundo.Camera;
import com.mira.mundo.World;

public class Enemy extends Entity 
{
	public boolean right, left, up, down;
	public boolean rightSprite = true, leftSprite;
	public double speed = 0.7;
	
	private int frames = 0, maxFrames = 7, index = 0, maxIndex = 2;
	private int life = 10;
	private int cont = 0, max = 100, change = 0;
	
	private boolean recebendoDano = false;
	private int framesDano;
	
	//private int cont;
	
	private BufferedImage[] leftEnemy;
	private BufferedImage[] rightEnemy;
	private BufferedImage[] enemyDamage;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) 
	{
		super(x, y, width, height, sprite);
		
		rightEnemy = new BufferedImage[3];
		leftEnemy = new BufferedImage[3];
		enemyDamage = new BufferedImage[2];
		
		enemyDamage[0] = Game.spritesheet.getSprite(96, 32, 16, 16);
		enemyDamage[1] = Game.spritesheet.getSprite(112, 32, 16, 16);
		
		for(int i = 0; i < 3; i++)
		{
			rightEnemy[i] = Game.spritesheet.getSprite(96 + (i * 16), 0, 16, 16);
			leftEnemy[i] = Game.spritesheet.getSprite(96 + (i * 16), 16, 16, 16);
		}
	}
	
	public void tick()
	{	
		if(!isColiddingWithPlayer())
		{
			if(Math.abs(Game.player.getX() - (int)x ) < 100 && Math.abs(Game.player.getY() - (int)y) < 100 )
			{
				 if(Game.player.getX() > (int)x 
	                        && World.isFree((int)(x + speed), getY())
	                        && !isColidding((int)(x + speed), getY()))
	                {
	                    x += speed;

	                    leftSprite = false;
	                    rightSprite = true;

	                } else if(Game.player.getX() < (int)x 
	                        && World.isFree((int)(x - speed), getY())
	                        && !isColidding((int)(x - speed), getY()))
	                {
	                    x -= speed;

	                    leftSprite = true;
	                    rightSprite = false;
	                }

	                if(Game.player.getY() < (int)y 
	                        && World.isFree(this.getX(), (int)(y - speed))
	                        && !isColidding(this.getX(), (int)(y - speed)))
	                {
	                    y -= speed;

	                } else if(Game.player.getY() > (int)y 
	                        && World.isFree(this.getX(), (int)(y + speed))
	                        && !isColidding(this.getX(), (int)(y + speed)))
	                {
	                    y += speed;
	                }
					
				if(Game.gameState == "normal")
				{
					frames++;
					if(frames == maxFrames)
					{	frames = 0;
						index ++;
						if(index > maxIndex)
							index = 0;
					}
				}
			} 
			
			else
			{
				cont++;
				if(cont == max)
				{	cont = 0;
					change ++;
					if(change > 2)
						change = 0;
				}
				
				if(change == 0 && World.isFree((int)(x - speed), getY()) && !isColidding((int)(x - speed), getY()))
				{
					x-=speed;
					leftSprite = true;
					rightSprite = false;
				}
				else if(change == 1 && World.isFree((int)(x + speed), getY()) && !isColidding((int)(x + speed), getY()))
				{
					x+=speed;
					leftSprite = false;
					rightSprite = true;
				}
			}
			
		} else
		{
			if(Game.random.nextInt(100) < 20)
			{
				Game.player.life--;
				Game.player.recebendoDano = true;
				
			}
		}
		
		isColiddingWithBullet();
		
		if(recebendoDano)
		{
			framesDano++;
			if(framesDano == 5)
			{
				framesDano = 0;
				recebendoDano = false;
			}
		}
		
		if(life <= 0)
		{
			destroyItSelf();
		}
	}
	
	private void destroyItSelf() 
	{
		Game.entities.remove(this);
		Game.enemies.remove(this);
		return;
	}

	public void render(Graphics g)
	{
		if(rightSprite)
		{
			if(!recebendoDano)
			{
				if(Game.player.getY() == (int)y && Game.player.getX() == (int)x)
					g.drawImage(rightEnemy[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
				else	
				g.drawImage(rightEnemy[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			else
				g.drawImage(enemyDamage[1], this.getX() - Camera.x, this.getY() - Camera.y, null);
		} 
		
		else if(leftSprite)
		{
			if(!recebendoDano)
			{
				if(Game.player.getY() == (int)y && Game.player.getX() == (int)x)
					g.drawImage(leftEnemy[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
				else
				g.drawImage(leftEnemy[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			else
				g.drawImage(enemyDamage[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		//visualizar retângulo de colisão
		/*g.setColor(new Color(0,0,0, 120));
		g.fillRect(this.getX() - Camera.x, this.getY() - Camera.y, 16, 16);*/
	}

	public void isColiddingWithBullet()
	{
		for(int i = 0; i < Game.bullets.size(); i++)
		{
			if(Entity.isColliding(this, Game.bullets.get(i)))
			{
				recebendoDano = true;
				life-=2;
				Game.bullets.remove(i);
				return;
			}
		}
	}
	
	public boolean isColiddingWithPlayer()
	{
		Rectangle currentEnemy = new Rectangle(this.getX(), this.getY(), 12, 16);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 12, 16);
		return player.intersects(currentEnemy); 
	}
	
	public boolean isColidding(int xnext, int ynext)
	{
		Rectangle currentEnemy = new Rectangle(xnext, ynext, 12, 16);
		
		for(int i = 0; i < Game.enemies.size(); i++)
		{
			Enemy e = Game.enemies.get(i);
			if(e == this)
				continue;
			
			Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), 12, 16);
			
			if(currentEnemy.intersects(targetEnemy))
				return true;
		}
		
		return false;
	}
}
