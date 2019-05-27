package PwrGame.Terrain;

import javax.swing.*;
import java.awt.*;

public class Textures
{
	/*
	 *  Contains tile textures
	 *  Textures are later referenced in GridMaker
	 *  Probably decreasing memory allocation
	 *  Because the same tile type refers to the same Image[] instance
	 */

	private Image[] grass;
	private Image[] corpse;
	private Image[] sand;
	private Image[] water;
	private Image[] dirt;

	public Textures()
	{
		//TODO: semi-auto search for textures

		//water
		try
		{
			water = new Image[]{
				new ImageIcon(getClass().getClassLoader().getResource("terrain/water.png")).getImage(),
			};
		}
		catch (Exception e)
		{
			System.out.println("Failed to load water texture");
			System.exit(3);
		}

		//sand
		try
		{
			sand = new Image[]{
				new ImageIcon(getClass().getClassLoader().getResource("terrain/sand.png")).getImage(),
			};
		}
		catch (Exception e)
		{
			System.out.println("Failed to load sand texture");
			System.exit(3);
		}

		//dirt
		try
		{
			dirt = new Image[]{
					new ImageIcon(getClass().getClassLoader().getResource("terrain/dirt.png")).getImage()
			};
		}
		catch (Exception e)
		{
			System.out.println("Failed to load dirt texture");
			System.exit(3);
		}

		//grass
		try
		{
			grass = new Image[]{
					new ImageIcon(getClass().getClassLoader().getResource("terrain/grass0.png")).getImage(),
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
			};
		}
		catch (Exception e)
		{
			System.out.println("Failed to load grass texture(s)");
		}
	}

	public Image[] getDirt()
	{
		return dirt;
	}

	public Image[] getSand()
	{
		return sand;
	}

	public Image[] getWater()
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