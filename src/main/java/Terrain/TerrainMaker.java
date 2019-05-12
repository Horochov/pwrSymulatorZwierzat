package Terrain;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TerrainMaker
{
	public Textures textures;
	private Grid grid;

	public TerrainMaker(int width, int height, int size)
	{
		grid = new Grid(width,height,size);
		textures = new Textures();
	}

	public Grid generateRandomTerrain()
	{
		Image[][] lookup=
				{
						textures.sand,
						textures.water,
						textures.grass
				};
		Random r = new Random();

		for (int i = 0; i < grid.getWidth(); i++)
		{
			for (int j = 0; j < grid.getHeight(); j++)
			{

				try{
					grid.tile(i,j).setTextures(lookup[r.nextInt(3)]);
				}
				catch (Exception e)
				{
//					e.getStackTrace();
					System.out.println("TerrainMaker.random texture set error");
				}
			}
		}
		return grid;
	}

//	public Grid generateTestTerrain()
//	{
//		for (int i = 0; i < 4; i++)
//		{
//			grid.tile(i,i).texture=textures.sand;
//		}
//		return null;
//	}

	class Textures
	{
		//oh god that total wasted ammount of memory where texture count == 0...
		public Image[] grass;
		public Image[] sand;
		public Image[] water;

		public Textures()
		{
			//TODO: semi-auto search for textures
			//water
			try
			{
				water = new Image[]{new ImageIcon(getClass().getClassLoader().getResource("terrain/water.png")).getImage()};
			}
			catch (Exception e)
			{
				System.out.println("Failed to load water texture(s)");
			}

			//sand
			try
			{
				sand = new Image[]{new ImageIcon(getClass().getClassLoader().getResource("terrain/sand.png")).getImage()};
			}
			catch (Exception e)
			{
				System.out.println("Failed to load sand texture(s)");
			}

			//sand
			try
			{
				grass = new Image[]{
						new ImageIcon(getClass().getClassLoader().getResource("terrain/grass1.png")).getImage(),
						new ImageIcon(getClass().getClassLoader().getResource("terrain/grass2.png")).getImage(),
						new ImageIcon(getClass().getClassLoader().getResource("terrain/grass3.png")).getImage()
				};
			}
			catch (Exception e)
			{
				System.out.println("Failed to load sand texture(s)");
			}
		}

	}
}
