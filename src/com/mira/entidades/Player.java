package com.mira.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.mira.main.Game;
import com.mira.main.Sound;
import com.mira.mundo.Camera;
import com.mira.mundo.World;

public class Player extends Entity
{

	public boolean right, left, up, down;
	public boolean rightSprite = true, leftSprite;
	public double speed = 1.3 ;
	public double life = 100;
	
	public int ammo = 0;
	
	private int frames = 0, maxFrames = 7, index = 0, maxIndex = 2;
	private int framesDano = 0;
	
	public static boolean movendo;
	public boolean recebendoDano = false;
	public boolean hasGun = false;
	public boolean isShooting = false;
	
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] playerDamage;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite)
	{
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[3];
		leftPlayer = new BufferedImage[3];
		playerDamage = new BufferedImage[2];
		
		playerDamage[0] = Game.spritesheet.getSprite(64, 32, 16, 16);
		playerDamage[1] = Game.spritesheet.getSprite(80, 32, 16, 16);
		
		for(int i = 0; i < 3; i++)
		{
			rightPlayer[i] = Game.spritesheet.getSprite(48 + (i * 16), 0, 16, 16);
			leftPlayer[i] = Game.spritesheet.getSprite(48 + (i * 16), 16, 16, 16);
		}
	}
	
	public void tick()
	{
		movendo = false;
		

		if(right && World.isFree((int)(x + speed), getY()))	
		{	movendo = true;
			x += speed;
			
		} else if(left && World.isFree((int)(x - speed), getY()))
		{
			movendo = true;
			x -= speed;
		}
		
		if(up && World.isFree(this.getX(), (int)(y - speed)))
		{
			movendo = true;
			y -= speed;
			
		} else if(down && World.isFree(this.getX(), (int)(y + speed)))
		{
			movendo = true;
			y += speed;
		}
		
		//faz a camera seguir o jogador
		Camera.x = Camera.clamp(getX() - (Game.WIDTH / 2) + 8, 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(getY() - (Game.HEIGHT / 2) + 8, 0, World.HEIGHT*16 - Game.HEIGHT);
		
		//tempo de animação das perninhas
		if(movendo)
		{
			frames++;
			if(frames == maxFrames)
			{	frames = 0;
				index ++;
				if(index > maxIndex)
					index = 0;
			}
		}
		
		if(recebendoDano)
		{
			Sound.hitPlayerMusic.play();
			framesDano++;
			if(framesDano == 10)
			{
				framesDano = 0;
				recebendoDano = false;
			}
		}
		
		if(life <= 0)
		{
			Game.gameState = "gameOver";
		}
		
		if(isShooting )
		{
			isShooting = false;
			
			if(hasGun && ammo > 0)
			{
				Sound.bulletMusic.play();
				ammo--;
		
				int dx;
				
				if(rightSprite)
					dx = 1;
				else 
					dx = -1;
				
				BulletShoot bullet = new BulletShoot(this.getX()+ (6*dx), this.getY()+11, 2, 2, null, dx, 0);
				Game.bullets.add(bullet);
			}
		}
		
		//verificando coleta de itens 
		collectingItens();
	}
	
	public void collectingItens()
	{
		for(int i = 0; i < Game.entities.size(); i++)
		{
			Entity atual = Game.entities.get(i);
			
			if(Entity.isColliding(this, atual))
			{
				
				//coleta de vida
				if(atual instanceof Trigo)
				{
					life += 10;
					Game.entities.remove(atual);
					
					if(life > 100)
						life = 100;
					
					Sound.getEntityMusic.play();
				}
				
				//coleta de munição
				else if(atual instanceof Bullet)
				{
					ammo += 20;
					Game.entities.remove(atual);
					
					Sound.getEntityMusic.play();
				}
				
				else if(atual instanceof Gun)
				{
					hasGun = true;
					Game.entities.remove(atual);
					
					Sound.getEntityMusic.play();
				}
				
			}

		}
	}

	
	public void render(Graphics g)
	{
		if(rightSprite)
		{
			if(!recebendoDano)
			{
				if(movendo == false)
					g.drawImage(rightPlayer[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
				
				else
				g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				
				if(hasGun)
					g.drawImage(Entity.RIGHT_GUN, this.getX() - Camera.x + 8, this.getY() - Camera.y, null);
			}
			else
				g.drawImage(playerDamage[1], this.getX() - Camera.x, this.getY() - Camera.y, null);
		} 
		
		else if(leftSprite)
		{
			if(!recebendoDano)
			{
				if(movendo == false)
					g.drawImage(leftPlayer[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
				else
				g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				
				if(hasGun)
					g.drawImage(Entity.LEFT_GUN, this.getX() - Camera.x - 8, this.getY() - Camera.y, null);
			}
			else
				g.drawImage(playerDamage[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}

}
