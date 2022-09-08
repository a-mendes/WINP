package com.mira.mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.mira.main.Game;

public class Tile 
{
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 16, 16, 16);
	public static BufferedImage TILE_FLOOR2 = Game.spritesheet.getSprite(64, 64, 16, 16);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16, 16, 16, 16);
	public static BufferedImage TILE_GRIT = Game.spritesheet.getSprite(32, 16, 16, 16);
	public static BufferedImage TILE_MOSS = Game.spritesheet.getSprite(16, 32, 16, 16);
	public static BufferedImage TILE_GRASS_WALL = Game.spritesheet.getSprite(144, 16, 16, 16);
	
	public static BufferedImage Casa1 = Game.spritesheet.getSprite(48, 80, 16, 16);
	public static BufferedImage Casa2 = Game.spritesheet.getSprite(64, 80, 16, 16);
	public static BufferedImage Casa3 = Game.spritesheet.getSprite(80, 80, 16, 16);
	public static BufferedImage Casa4 = Game.spritesheet.getSprite(96, 80, 16, 16);
	public static BufferedImage Casa5 = Game.spritesheet.getSprite(80, 64, 16, 16);
	public static BufferedImage Casa6 = Game.spritesheet.getSprite(96, 64, 16, 16);
	public static BufferedImage Casa8 = Game.spritesheet.getSprite(48, 64, 16, 16);
	public static BufferedImage Casa9 = Game.spritesheet.getSprite(32, 64, 16, 16);
	public static BufferedImage Casa10 = Game.spritesheet.getSprite(16, 64, 16, 16);
	public static BufferedImage Casa11 = Game.spritesheet.getSprite(0, 64, 16, 16);

	
	private BufferedImage sprite;
	private int x, y;
	
	public Tile( int x, int y, BufferedImage sprite)
	{
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}
}
