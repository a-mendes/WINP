package com.mira.graficos;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet 
{
	private BufferedImage biSpritesheet;
	
	public Spritesheet(String path)
	{
		try {
			biSpritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x, int y, int w, int h)
	{
		return biSpritesheet.getSubimage(x, y, h, w);
	}
}
