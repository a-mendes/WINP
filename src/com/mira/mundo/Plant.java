package com.mira.mundo;

import java.awt.image.BufferedImage;

import com.mira.main.Game;

public class Plant extends WorldTile
{

	public Plant(int x, int y, BufferedImage sprite) 
	{
		super(x, y, sprite);
	}

	public static BufferedImage Planta1 = Game.spritesheet.getSprite(128, 64, 16, 16);
	public static BufferedImage Planta2 = Game.spritesheet.getSprite(128, 48, 16, 16);
	public static BufferedImage Arvore1 = Game.spritesheet.getSprite(112, 48, 16, 16);
	public static BufferedImage Arvore2 = Game.spritesheet.getSprite(112, 64, 16, 16);
}
