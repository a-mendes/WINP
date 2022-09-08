package com.mira.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class WinMenu extends PauseMenu 
{
	public void render(Graphics g)
	{
		
		g.setColor(new Color(0,0,0, 120));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.setColor(Color.cyan);
		g.drawString("You Win!", (Game.WIDTH/2) - 68, 60);
		
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.setColor(Color.white);
		g.drawString("- WINP-", (Game.WIDTH/2) - 30, 80);
		
		g.setFont(new Font("Arial", Font.BOLD, 11));
		g.setColor(Color.white);
		g.drawString("Main Menu", (Game.WIDTH/2) - 33, 115);
		
		g.setFont(new Font("Arial", Font.BOLD, 11));
		g.setColor(Color.white);
		g.drawString("Play Again", (Game.WIDTH/2) - 32, 130);
		
		g.setFont(new Font("Arial", Font.BOLD, 11));
		g.setColor(Color.white);
		g.drawString("Exit", (Game.WIDTH/2) - 10, 145);
		
		if(optSelected == 0) 
		{
			g.drawString(">", (Game.WIDTH/2) - 41, 115);
		}
		else if(optSelected == 1) 
		{
			g.drawString(">", (Game.WIDTH/2) - 40, 130);
		}	
		else if(optSelected == 2) 
		{
			g.drawString(">", (Game.WIDTH/2) - 18, 145);
		}	
	}
	
}
