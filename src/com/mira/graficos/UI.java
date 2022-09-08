package com.mira.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.mira.main.Game;

public class UI 
{
	public void render(Graphics g)
	{
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.setColor(Color.white);
		g.drawString("L:", 2, 15);
		
		g.setColor(Color.red);
		g.fillRect(12, 8, 50, 8);
	
		g.setColor(Color.green);
		g.fillRect(12, 8, (int)((Game.player.life/100) * 50), 8);
		
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.setColor(Color.white);
		g.drawString("A: " +Game.player.ammo , 200, 15);
	}
}
