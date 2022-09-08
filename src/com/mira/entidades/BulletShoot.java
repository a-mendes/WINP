package com.mira.entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.mira.main.Game;
import com.mira.mundo.Camera;

public class BulletShoot extends Entity 
{

	private int dx, dy;
	private double speedShoot = 5;
	private int life = 100, curLife = 0;
	
	public BulletShoot(int x, int y, int width, int height, BufferedImage sprite, int dx, int dy) 
	{
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
		
		curLife++;
		
		if(curLife == life)
			Game.bullets.remove(this);
			return;
	}
	
	public void tick()
	{
		if(!isCollidingWithWall())
		{
			x += dx * speedShoot;
			y += dy * speedShoot;
		} else
		{
			Game.bullets.remove(this);
		}
			
	}
	
	private boolean isCollidingWithWall()
	{
		Rectangle currentBullet = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	
		for(int i = 0; i < Game.wallTile.size(); i++)
		{
			Rectangle currentWall = new Rectangle(Game.wallTile.get(i).getX(), Game.wallTile.get(i).getY(), 16, 16);
		
			if(currentBullet.intersects(currentWall))
				return true;
		}
		return false;
	}
	
	
	public void render(Graphics g)
	{
		g.setColor(Color.yellow);
		g.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, width, height);
	}
	

}
