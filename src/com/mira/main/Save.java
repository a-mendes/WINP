package com.mira.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save 
{
	
	public boolean saveExists;
	public static void saveGame(String[] val1, int[] val2, int encode)
	{
		BufferedWriter writer = null;
		
		try 
		{
			writer = new BufferedWriter(new FileWriter("save.txt"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		for(int i = 0; i < val1.length; i++)
		{
			String current = val1[i];
			current += ":";
			char[] value = Integer.toString(val2[i]).toCharArray();
			
			for(int j = 0; j < value.length; i++)
			{
				value[j] += encode;
				current += value[j];
			}
			
			try
			{
				writer.write(current);
				if(i < val1.length -1)
					writer.newLine();
			} catch(IOException e){}
			
			try 
			{
				writer.flush();
				writer.close();
			} catch(IOException e) {}
		}
	}
	
	public static String loadGame(int encode)
	{
		String line = "";
		File file = new File("save.txt");
		
		if(file.exists())
		{
			try
			{
				String singleLine = null;
				BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
				try
				{
					while((singleLine = reader.readLine()) != null)
					{
						String[] trans = singleLine.split(":");
						char[] val = trans[1].toCharArray();
						trans[1] ="";
						
						for(int i = 0; i < val.length; i++)
						{
							val[i] -= encode;
							trans[1] +=val[i];
						}
						
						line += trans[0];
						line += ":";
						line += trans[1];
						line += "/";
					}
				} catch(IOException e) {}
			} catch(FileNotFoundException e) {}
		}
		return line;
	}
	
	public static void applySave(String str)
	{
		String[] spl = str.split("/");
		for(int i = 0; i < spl.length; i++)
		{
			//String[] spl2 = spl[i].split(":");
			
			// case level min 9:16
		}
	}
}
