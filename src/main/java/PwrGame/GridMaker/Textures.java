package PwrGame.GridMaker;

import javax.swing.*;
import java.awt.*;

public class Textures
{
	//oh god that total wasted ammount of memory where texture count == 0...
	private Image[] grass;
	private Image[] corpse;
	private Image sand;
	private Image water;
	private Image dirt;

	public Textures()
	{
		//TODO: semi-auto search for textures
		//water
		try
		{
			water = new ImageIcon(getClass().getClassLoader().getResource("terrain/water.png")).getImage();
		}
		catch (Exception e)
		{
			System.out.println("Failed to load water texture");
		}

		//sand
		try
		{
			sand = new ImageIcon(getClass().getClassLoader().getResource("terrain/sand.png")).getImage();
		}
		catch (Exception e)
		{
			System.out.println("Failed to load sand texture");
		}

		//dirt
		try
		{
			dirt = new ImageIcon(getClass().getClassLoader().getResource("terrain/grass0.png")).getImage();
		}
		catch (Exception e)
		{
			System.out.println("Failed to load dirt texture");
		}

		//grass
		try
		{
			grass = new Image[]{
					new ImageIcon(getClass().getClassLoader().getResource("terrain/grass1.png")).getImage(),
					new ImageIcon(getClass().getClassLoader().getResource("terrain/grass2.png")).getImage(),
					new ImageIcon(getClass().getClassLoader().getResource("terrain/grass3.png")).getImage(),
					};
		}
		catch (Exception e)
		{
			System.out.println("Failed to load grass texture(s)");
		}

		//corpse
		try
		{
			corpse = new Image[]{
					null
//						new ImageIcon(getClass().getClassLoader().getResource("terrain/grass1.png")).getImage(),
//						new ImageIcon(getClass().getClassLoader().getResource("terrain/grass1.png")).getImage(),
//						new ImageIcon(getClass().getClassLoader().getResource("terrain/grass2.png")).getImage(),
//						new ImageIcon(getClass().getClassLoader().getResource("terrain/grass3.png")).getImage(),
			};
		}
		catch (Exception e)
		{
			System.out.println("Failed to load grass texture(s)");
		}
	}

	public Image getDirt()
	{
		return dirt;
	}

	public Image getSand()
	{
		return sand;
	}

	public Image getWater()
	{
		return water;
	}

	public Image[] getCorpse()
	{
		return corpse;
	}

	public Image[] getGrass()
	{
		return grass;
	}
}