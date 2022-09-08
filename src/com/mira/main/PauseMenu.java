package com.mira.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PauseMenu 
{
	public int optSelected = 0, optMax = 2;
	public boolean down, up; 
	
	public void tick()
	{
		if(up)
		{
			up = false;
			
			optSelected--;
			
			if(optSelected < 0)
				optSelected = this.optMax;
		}
		else if(down)
		{
			down = false;
			
			optSelected++;
			
			if(optSelected > this.optMax)
				optSelected = 0;

		}
	}
	
	public void render(Graphics g)
	{
		
		g.setColor(new Color(0,0,0, 120));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.setColor(Color.yellow);
		g.drawString("Pause", (Game.WIDTH/2) - 47, 60);
		
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.setColor(Color.white);
		g.drawString("- WINP-", (Game.WIDTH/2) - 30, 80);
		
		g.setFont(new Font("Arial", Font.BOLD, 11));
		g.setColor(Color.white);
		g.drawString("Resume", (Game.WIDTH/2) - 23, 115);
		
		//o save estará disponível na versão com mais levels
		g.setFont(new Font("Arial", Font.BOLD, 11));
		g.setColor(Color.white);
		g.drawString("Save", (Game.WIDTH/2) - 14, 130);
		
		g.setFont(new Font("Arial", Font.BOLD, 11));
		g.setColor(Color.white);
		g.drawString("Exit", (Game.WIDTH/2) - 9, 145);
		
		if(optSelected == 0) 
		{
			g.drawString(">", (Game.WIDTH/2) - 31, 115);
		}
		else if(optSelected == 1) 
		{
			g.setColor(Color.white);
			g.drawString(">", (Game.WIDTH/2) - 22, 130);
		}	
		else if(optSelected == 2) 
		{
			g.drawString(">", (Game.WIDTH/2) - 17, 145);
		}	
	}
}
