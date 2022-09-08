package com.mira.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menu 
{
	public static int optSelected = 0, optMax = 2;
	
	private BufferedImage background;
	public boolean down, up; 
	
	public Menu(String path)
	{
		try 
		{
			background = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick()
	{
		if(up)
		{
			up = false;
			
			optSelected--;
			
			if(optSelected < 0)
				optSelected = optMax;
		}
		else if(down)
		{
			down = false;
			
			optSelected++;
			
			if(optSelected > optMax)
				optSelected = 0;

		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(background, 0, 0, null);
		
		g.setColor(new Color(0,0,0, 120));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.setColor(Color.white);
		g.drawString("War in Pixel", (Game.WIDTH/2) - 84, 60);
		
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.setColor(Color.white);
		g.drawString("- WINP-", (Game.WIDTH/2) - 30, 80);

		g.setFont(new Font("Arial", Font.BOLD, 11));
		
		if(!Game.save.exists())
			g.setColor(Color.gray);
		else
			g.setColor(Color.white);
		
		g.drawString("Continue", (Game.WIDTH/2) - 24, 115);
		
		g.setFont(new Font("Arial", Font.BOLD, 11));
		g.setColor(Color.white);
		g.drawString("Start Game", (Game.WIDTH/2) - 28, 130);
		
		g.setFont(new Font("Arial", Font.BOLD, 11));
		g.setColor(Color.white);
		g.drawString("Exit", (Game.WIDTH/2) - 8, 145);
		
		if(optSelected == 0) 
		{
			if(!Game.save.exists())
				g.setColor(Color.gray);
			else
				g.setColor(Color.white);
			g.drawString(">", (Game.WIDTH/2) - 32 , 115);
		}
		else if(optSelected == 1) 
		{
			g.drawString(">", (Game.WIDTH/2) - 36, 130);
		}
		else if(optSelected == 2) 
		{
			g.drawString(">", (Game.WIDTH/2) - 16, 145);
		}	
	}
}
