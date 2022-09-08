package com.mira.mundo;

import java.awt.image.BufferedImage;

import com.mira.main.Game;

public class WorldTile extends Tile
{
	public WorldTile(int x, int y, BufferedImage sprite)
	{
		super(x, y, sprite);
	}

	public static BufferedImage Flor1 = Game.spritesheet.getSprite(144, 80, 16, 16);
	public static BufferedImage Flor2 = Game.spritesheet.getSprite(144, 64, 16, 16);
	public static BufferedImage Flor3 = Game.spritesheet.getSprite(144, 48, 16, 16);
	public static BufferedImage Flor4 = Game.spritesheet.getSprite(144, 32, 16, 16);
	public static BufferedImage Flor5 = Game.spritesheet.getSprite(128, 32, 16, 16);
	public static BufferedImage Flor6 = Game.spritesheet.getSprite(96, 48, 16, 16);
	public static BufferedImage Flor7 = Game.spritesheet.getSprite(80, 48, 16, 16);
	public static BufferedImage Flor8 = Game.spritesheet.getSprite(64, 48, 16, 16);
	public static BufferedImage Flor9 = Game.spritesheet.getSprite(48, 48, 16, 16);
	public static BufferedImage Flor10 = Game.spritesheet.getSprite(32, 48, 16, 16);
	public static BufferedImage Flor11 = Game.spritesheet.getSprite(16, 48, 16, 16);
}
