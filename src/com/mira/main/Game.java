package com.mira.main;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.mira.entidades.BulletShoot;
import com.mira.entidades.Enemy;
import com.mira.entidades.Entity;
import com.mira.entidades.Player;
import com.mira.graficos.Spritesheet;
import com.mira.graficos.UI;
import com.mira.mundo.WallTile;
import com.mira.mundo.World;
import com.mira.mundo.WorldTile;

public class Game extends Canvas implements Runnable, KeyListener
{
	private static final long serialVersionUID = 1L;
	
	public static boolean isRunning = false;
	private int SCALE = 3;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	
	private Thread thread;
	private JFrame jframe;
	private BufferedImage bImage;
	private Menu menu;
	private PauseMenu pause;
	private WinMenu win;
	private GameOverMenu gameover;
	private UI ui;
	
	public static Spritesheet spritesheet;
	public static World world;
	public static Player player;
	public static Random random;
	public static String gameState = "menu";
	public static BufferedWriter buffWriter;
	public static BufferedReader buffReader;
	public static File save = new File("GameState.txt");
	
	public static List<Entity> entities;
	public static List<Enemy> enemies;
	public static List<BulletShoot> bullets;
	public static List<WallTile> wallTile;
	public static List<WorldTile> worldTile;
	
	public Game()
	{
		addKeyListener(this);
				
		// config de janela
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		bImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		jframe = new JFrame("War in Pixel - WINP");
		jframe.add(this);
		jframe.setResizable(false);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		jframe.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
		
		//configs de sprite 
		spritesheet = new Spritesheet("/sprite.png");
		
		pause = new PauseMenu();
		gameover = new GameOverMenu();
		ui = new UI();
		menu = new Menu("/menu.png");
		win = new WinMenu();
		
		//newGame();
		
		bullets = new ArrayList<BulletShoot>();
		wallTile = new ArrayList<WallTile>();
		worldTile = new ArrayList<WorldTile>();
		random = new Random();
		
		Sound.menuMusic.loop();
	}
	
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.startGame();
	}
	
	public void tick()
	{
		if(gameState.equals("jogo"))
		{
			if(enemies.size() == 0)
			{
				gameState = "win";
			} else 
			{
			
				for(int i = 0; i < entities.size(); i++)
				{
					entities.get(i).tick();
				}
	
				for(int i = 0; i < bullets.size(); i++)
				{
					bullets.get(i).tick();
				}
			}
		}
		
		else if(gameState.equals("gameOver"))
		{
			gameover.tick();
		}
		
		else if(gameState.equals("win"))
		{
			win.tick();
		}
		
		else if(gameState.equals("menu"))
		{
			menu.tick();
		}
		
		else if(gameState.equals("pause"))
		{
			pause.tick();
		}
		else if(gameState.equals("mundo"))
		{
			for(int i = 0; i < entities.size(); i++)
			{
				entities.get(i).tick();
			}
			
			if(player.getX() > 330 && player.getX() < 340 && player.getY() == 32)
			{
				newGame("/map1.png");
				Game.gameState = "jogo";
			}
				
		}
	}

	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bImage.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gameState.equals("menu"))
		{
			menu.render(g);
		}
		
		else if(gameState.equals("pause"))
		{
			world.render(g);
			for(int i = 0; i < entities.size(); i++)
			{
				Entity e = entities.get(i);
				e.render(g);
			}
			
			pause.render(g);
		}
		
		else if(gameState.equals("win"))
		{
			world.render(g);
			for(int i = 0; i < entities.size(); i++)
			{
				Entity e = entities.get(i);
				e.render(g);
			}
			
			win.render(g);
		}
		
		else if(gameState.equals("gameOver"))
		{
			//renderizando mundo
			world.render(g);
			// Rodando entidades
			for(int i = 0; i < entities.size(); i++)
			{
				Entity e = entities.get(i);
				e.render(g);
			}
			
			gameover.render(g);
		}
		
		else if(gameState.equals("jogo"))
		{
			//renderizando mundo
			world.render(g);
			// Rodando entidades
			for(int i = 0; i < bullets.size(); i++)
			{
				bullets.get(i).render(g);
			}
			for(int i = 0; i < entities.size(); i++)
			{
				Entity e = entities.get(i);
				e.render(g);
			}
			
			ui.render(g);
		}
		
		else if(gameState.equals("mundo"))
		{
			world.render(g);
			
			for(int i = 0; i < worldTile.size(); i++)
			{
				worldTile.get(i).render(g);
			}
			
			for(int i = 0; i < entities.size(); i++)
			{
				Entity e = entities.get(i);
				e.render(g);
			}
		}
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(bImage, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null); //Configura a layer do fundo
		
		bs.show(); //flip
		
	}
	
	private synchronized void startGame() 
	{
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stopGame() 
	{
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override

	public void run() 
	{
		requestFocus();
		
		long lastTime = System.nanoTime();
		long now = 0;
		double ns = 1000000000 / 60;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunning)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1)
			{
				tick();
				render();
				
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000)
			{
				System.out.println("FPS: "+frames);
				frames = 0;
				timer += 1000;
			}
		}
		
		stopGame();
	}

	//Config de teclado
	@Override
	public void keyPressed(KeyEvent e) {
		
		if((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) && (gameState.equals("jogo") || gameState.equals("mundo")))
		{
			player.right = true;
			player.leftSprite = false;
			player.rightSprite = true;
		}else if((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) && (gameState.equals("jogo") || gameState.equals("mundo")))
		{
			player.left = true;
			player.rightSprite = false;
			player.leftSprite = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
		{
			if(gameState.equals("menu"))
			{
				menu.down = true;
			}
			
			else if(gameState.equals("pause"))
			{
				pause.down = true;
			}
			
			else if(gameState.equals("win"))
			{
				win.down = true;
			}
			
			else if(gameState.equals("gameOver"))
			{
				gameover.down = true;
			}
			
			else if(gameState.equals("jogo") || gameState.equals("mundo"))
			{
				player.down = true;
			}
			
		}else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
		{
			if(gameState.equals("menu"))
			{
				menu.up = true;
			}
			
			else if(gameState.equals("pause"))
			{
				pause.up = true;
			}
			
			else if(gameState.equals("win"))
			{
				win.up = true;
			}
			
			else if(gameState.equals("gameOver"))
			{
				gameover.up = true;
			}
			
			else if(gameState.equals("jogo") || gameState.equals("mundo"))
			{
				player.up = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			player.speed = 2;
		
		if(e.getKeyCode() == KeyEvent.VK_P)
			gameState = "pause";
		
		if(e.getKeyCode() == KeyEvent.VK_CONTROL)
		{
			player.isShooting = true;
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
		{
			if(gameState.equals("jogo") || gameState.equals("mundo"))
			player.right = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
		{
			if(gameState.equals("jogo") || gameState.equals("mundo"))
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
		{
			if(gameState.equals("jogo") || gameState.equals("mundo"))
			player.down = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
		{
			if(gameState.equals("jogo") || gameState.equals("mundo"))
			player.up = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if(gameState.equals("menu"))
			{
				if(Menu.optSelected == 0)
				{
					try {
						Game.buffReader = new BufferedReader(new FileReader("GameState.txt"));
						try {
							newGame("/map1.png");
							gameState = Game.buffReader.readLine();
						} catch (IOException e2) {
							e2.printStackTrace();
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
				else if(Menu.optSelected == 1)
				{
					newGame("/world.png");
					gameState = "mundo";
					
					//newGame("/map1.png");
					//gameState= "jogo";
				}
				else if(Menu.optSelected == 2)
					System.exit(0);
			}
			
			else if(gameState.equals("pause"))
			{
				if(pause.optSelected == 0)
					gameState = "jogo";
				else if(pause.optSelected == 1)
				{
					try {
						buffWriter = new BufferedWriter(new FileWriter("GameState.txt"));
						buffWriter.write("jogo");
						buffWriter.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
					
				else if(pause.optSelected == 2)
					System.exit(0);
			}
			
			else if(gameState.equals("win"))
			{
				if(win.optSelected == 0)
					gameState = "menu";
				else if(win.optSelected == 1)
				{
					newGame("/map1.png");
					gameState = "jogo";
				}
				else if(win.optSelected == 2)
					System.exit(0);
			}
			
			else if(gameState.equals("gameOver"))
			{
				if(gameover.optSelected == 0)
					gameState = "menu";
				else if(gameover.optSelected == 1)
				{
					newGame("/map1.png");
					gameState = "jogo";
				}
				else if(gameover.optSelected == 2)
					System.exit(0);
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			player.speed = 1.3;
	}


	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}
	
	private void newGame(String path)
	{
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		//Cria o jogador   posições em tela                   posições na sprite 
		player = new Player( 0, 0, 16, 16, spritesheet.getSprite(48, 0, 16, 16));
		entities.add(player);//add jogador na lista de entidades
		world = new World(path);
		
		return;
	}

}
