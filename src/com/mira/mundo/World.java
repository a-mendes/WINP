package com.mira.mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mira.entidades.*;
import com.mira.main.Game;

public class World
{
	private static final int tileSize = 16;
	private int pixels[];
	public static Tile[] tiles;
	
	public static int WIDTH, HEIGHT;
	
	public World(String path)
	{
		try {
			BufferedImage spriteMap = ImageIO.read(getClass().getResource(path));
			
			pixels = new int[spriteMap.getHeight() * spriteMap.getWidth()];
			tiles = new Tile[spriteMap.getHeight() * spriteMap.getWidth()];
			spriteMap.getRGB(0, 0, spriteMap.getWidth(), spriteMap.getHeight(), pixels, 0, spriteMap.getWidth());
			
			WIDTH = spriteMap.getWidth();
			HEIGHT = spriteMap.getHeight();
			
			for(int xx = 0; xx < spriteMap.getWidth() /*ou simplesmente WIDTH*/; xx++)
			{
				for(int yy = 0; yy < spriteMap.getHeight() /*ou simplesmente HEIGHT*/; yy++)
				{
					int pixelAtual = xx + (yy * spriteMap.getWidth());
					tiles[xx + (yy* WIDTH)] = new FloorTile(xx * tileSize, yy * tileSize, Tile.TILE_FLOOR);
					
					//Game 1
					
					if(pixels[pixelAtual] == 0xFFFFFFFF)
					{
						//Wall
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, Tile.TILE_WALL);
						Game.wallTile.add(new WallTile(xx * tileSize, yy * tileSize, Tile.TILE_WALL));
					}
					
					else if(pixels[pixelAtual] == 0xFF000000)
					{
						//Floor
						tiles[xx + (yy* WIDTH)] = new FloorTile(xx * tileSize, yy * tileSize, Tile.TILE_FLOOR);
					}
					else if(pixels[pixelAtual] == 0xFF000CFF)
					{
						//Enemy
						Enemy enemy = new Enemy(xx * tileSize, yy * tileSize, tileSize, tileSize, Entity.ENEMY);
						Game.entities.add(enemy);
						Game.enemies.add(enemy);
					}
					else if(pixels[pixelAtual] == 0xFF28FF2C)
					{
						//player
						Game.player.setX(xx * tileSize);
						Game.player.setY(yy * tileSize);
					}
					else if(pixels[pixelAtual] == 0xFFFF5016)
					{
						//gun
						Game.entities.add(new Gun(xx * tileSize, yy * tileSize, tileSize, tileSize, Entity.GUN));
					}
					else if(pixels[pixelAtual] == 0xFFFFEC26)
					{
						//trigo
						Game.entities.add(new Trigo(xx * tileSize, yy * tileSize, tileSize, tileSize, Entity.TRIGO));
					}
					else if(pixels[pixelAtual] == 0xFFFF00DC)
					{
						//Bullet
						Game.entities.add(new Bullet(xx * tileSize, yy * tileSize, tileSize, tileSize, Entity.BULLET));
					}
					
					//Mundo Aberto
					
					//plantas
					
					else if(pixels[pixelAtual] == 0xFF03a9f4)
					{
						Game.worldTile.add(new Plant(xx * tileSize, yy * tileSize, Plant.Arvore1));
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, Tile.TILE_GRASS_WALL);
					}
					else if(pixels[pixelAtual] == 0xFFf44336)
					{
						Game.worldTile.add(new Plant(xx * tileSize, yy * tileSize, Plant.Arvore2));
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, Tile.TILE_GRASS_WALL);
					}
					
					else if(pixels[pixelAtual] == 0xFF7bc043)
					{
						Game.worldTile.add(new Plant(xx * tileSize, yy * tileSize, Plant.Planta1));
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, Tile.TILE_GRASS_WALL);
					}
					
					else if(pixels[pixelAtual] == 0xFF0392cf)
					{
						Game.worldTile.add(new Plant(xx * tileSize, yy * tileSize, Plant.Planta2));
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, Tile.TILE_GRASS_WALL);
					}
					else if(pixels[pixelAtual] == 0xFFffc425)
					{
						Game.worldTile.add(new WorldTile(xx * tileSize, yy * tileSize, WorldTile.Flor1));
					}
					else if(pixels[pixelAtual] == 0xFFffff00)
					{
						Game.worldTile.add(new WorldTile(xx * tileSize, yy * tileSize, WorldTile.Flor2));
					}
					
					else if(pixels[pixelAtual] == 0xFF00b159)
					{
						Game.worldTile.add(new WorldTile(xx * tileSize, yy * tileSize, WorldTile.Flor3));
					}
					else if(pixels[pixelAtual] == 0xFF00c853)
					{
						Game.worldTile.add(new WorldTile(xx * tileSize, yy * tileSize, WorldTile.Flor4));
					}
					
					else if(pixels[pixelAtual] == 0xFF004d40)
					{
						Game.worldTile.add(new WorldTile(xx * tileSize, yy * tileSize, WorldTile.Flor5));
					}
					
					else if(pixels[pixelAtual] == 0xFF880e4f)
					{
						Game.worldTile.add(new WorldTile(xx * tileSize, yy * tileSize, WorldTile.Flor6));
					}
					else if(pixels[pixelAtual] == 0xFFf37836)
					{
						Game.worldTile.add( new WorldTile(xx * tileSize, yy * tileSize, WorldTile.Flor7));
					}
					else if(pixels[pixelAtual] == 0xFFf50056)
					{
						Game.worldTile.add(new WorldTile(xx * tileSize, yy * tileSize, WorldTile.Flor8));
					}
					else if(pixels[pixelAtual] == 0xFFfafafa)
					{
						Game.worldTile.add(new WorldTile(xx * tileSize, yy * tileSize, WorldTile.Flor9));
					}
					else if(pixels[pixelAtual] == 0xFFfdf598)
					{
						Game.worldTile.add( new WorldTile(xx * tileSize, yy * tileSize, WorldTile.Flor10));
					}
					else if(pixels[pixelAtual] == 0xFFff8965)
					{
						Game.worldTile.add(new WorldTile(xx * tileSize, yy * tileSize, WorldTile.Flor11));
					}
					
					//Construções
					
					else if(pixels[pixelAtual] == 0xFF0000ff)
					{
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, WorldTile.Casa1);
					}
					
					else if(pixels[pixelAtual] == 0xFF00bcd4)
					{
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, WorldTile.Casa2);
					}
					
					else if(pixels[pixelAtual] == 0xFF00ff00)
					{
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, WorldTile.Casa3);
					}
					else if(pixels[pixelAtual] == 0xFF00ffff)
					{
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, WorldTile.Casa4);
					}
					
					else if(pixels[pixelAtual] == 0xFF008080)
					{
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, WorldTile.Casa5);
					}
					
					else if(pixels[pixelAtual] == 0xFF9c27b0)
					{
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, WorldTile.Casa6);
					}
					
					else if(pixels[pixelAtual] == 0xFF78909c)
					{
						tiles[xx + (yy* WIDTH)] = new WorldTile(xx * tileSize, yy * tileSize, WorldTile.TILE_FLOOR2);
					}
					else if(pixels[pixelAtual] == 0xFF795548)
					{
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, WorldTile.Casa8);
					}
					else if(pixels[pixelAtual] == 0xFFdd2c00)
					{
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, WorldTile.Casa9);
					}
					else if(pixels[pixelAtual] == 0xFFE91E63)
					{
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, WorldTile.Casa10);
					}
					
					else if(pixels[pixelAtual] == 0xFFff9800)
					{
						tiles[xx + (yy* WIDTH)] = new WallTile(xx * tileSize, yy * tileSize, WorldTile.Casa11);
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xNext, int yNext)
	{
		int x1 = xNext / tileSize;
		int y1 = yNext / tileSize;
		
		int x2 = (xNext + tileSize -1) / tileSize;
		int y2 = yNext / tileSize;
		
		int x3 = xNext / tileSize;
		int y3 = (yNext + tileSize -1) / tileSize;
		
		int x4 = (xNext + tileSize -1) / tileSize;
		int y4 = (yNext + tileSize -1) / tileSize;
		
		//verifica se é uma parede
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				 (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				 (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	
	public void render(Graphics g)
	{
		//renderiza apenas oq é exibido em tela
		
		int xstart = Camera.x/16;
		int ystart = Camera.y/16;
		
		int xfinal = xstart + (Game.WIDTH / 16);
		int yfinal = ystart + (Game.HEIGHT / 16);
		
		for(int xx = xstart; xx <= xfinal; xx++)
		{
			for(int yy = ystart; yy <= yfinal; yy++)
			{
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				
				Tile tile = tiles[ xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}
}
