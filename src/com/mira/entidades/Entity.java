package com.mira.entidades;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.mira.main.Game;
import com.mira.mundo.Camera;

public class Entity 
{
	public static BufferedImage LIFE_PACK = Game.spritesheet.getSprite(48, 32, 16, 16);
	public static BufferedImage GUN = Game.spritesheet.getSprite(0, 0, 16, 16);
	public static BufferedImage RIGHT_GUN = Game.spritesheet.getSprite(0, 0, 16, 16);
	public static BufferedImage LEFT_GUN = Game.spritesheet.getSprite(16, 0, 16, 16);
	public static BufferedImage SWORD = Game.spritesheet.getSprite(16, 0, 16, 16);
	public static BufferedImage BULLET = Game.spritesheet.getSprite(32, 0, 16, 16);
	public static BufferedImage ENEMY = Game.spritesheet.getSprite(96, 0, 16, 16);
	public static BufferedImage TRIGO = Game.spritesheet.getSprite(32, 32, 16, 16);
	
	protected int width, height;
	protected double x, y;
	protected BufferedImage sprite;
	
	private int maskx, masky, mheight, mwidth;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.mheight = height;
		this.mwidth = width;
	}
	
	public void setMask(int maskx, int masky, int mwidth, int mheight)
	{
		this.maskx = maskx;
		this.masky = masky;
		this.mheight = mheight;
		this.mwidth = mwidth;
	}
	
	public void setX(int newX)
	{
		this.x = newX;
	}
	
	public void setY(int newY)
	{
		this.y = newY;
	}
	
	public int getX()
	{
		return (int)x;
		
	}
	
	public int getY()
	{
		return (int)y;
		
	}
	
	public int getHeight()
	{
		return height;
		
	}
	
	public int getWidth()
	{
		return width;
		
	}
	
	public void tick()
	{
		
	}
	
	public static boolean isColliding(Entity a, Entity b)
	{
		Rectangle aMask = new Rectangle(a.getX() + a.maskx, a.getY() + a.masky, a.mwidth, a.mheight);
		Rectangle bMask = new Rectangle(b.getX() + b.maskx, b.getY() + b.masky, b.mwidth, b.mheight);
		
		return aMask.intersects(bMask); 
	}
	
	public void render(Graphics g)
	{
		g.drawImage(sprite, getX() - Camera.x, getY() - Camera.y, null);
	}
}
