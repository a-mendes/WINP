package com.mira.main;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound 
{
	private AudioClip audioClip;
	public static Sound menuMusic = new Sound("/musica.wav");
	public static Sound hitEnemyMusic = new Sound("/.HitNoInimigowav");
	public static Sound hitPlayerMusic = new Sound("/HitNoPersonagem.wav");
	public static Sound gameMusic = new Sound("/musica.wav");
	public static Sound bulletMusic = new Sound("/Atirar.wav");
	public static Sound getEntityMusic = new Sound("/Pegar.wav");
	
	private Sound(String name)
	{
		try
		{
			audioClip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch( Throwable e) {}
		
	}
	
	public void play()
	{
		try
		{
			new Thread()
			{
				public void run()
				{
					audioClip.play();
				}
			}.start();
		} catch(Throwable e){}
	}
	

	public void loop()
	{
		try
		{
			new Thread()
			{
				public void run()
				{
					audioClip.loop();
				}
			}.start();
		} catch(Throwable e){}
	}
}
