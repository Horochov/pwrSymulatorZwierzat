package Terrain;

import OpenSimplexNoise.OpenSimplexNoise;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TerrainMaker
{
	public Textures textures;
	private Grid grid;
	private long seed;
	public Offset noiseOffset;
	public class Offset
	{
		int offsetX, offsetY;

		public Offset(int x, int y)
		{
			offsetX=x;
			offsetY=y;
		}
		public Offset()
		{
			offsetX=0;
			offsetY=0;
		}

		public int modifyX(int dx)
		{
			return offsetX+=dx;
		}
		public int modifyY(int dy)
		{
			return offsetY+=dy;
		}

		public void modify(int dx, int dy)
		{
			offsetX+=dx;
			offsetY+=dy;
		}

		public void setX(int offsetX)
		{
			this.offsetX = offsetX;
		}

		public void setY(int offsetY)
		{
			this.offsetY = offsetY;
		}
		public void set(int offsetX, int offsetY)
		{
			this.offsetX = offsetX;
			this.offsetY = offsetY;
		}

		public void clear()
		{
			offsetX = 0;
			offsetY = 0;
		}

		public int getX()
		{
			return offsetX;
		}

		public int getY()
		{
			return offsetY;
		}
	}

	public TerrainMaker(int width, int height, int size)
	{
		grid = new Grid(width,height,size);
		textures = new Textures();
		seed=0;
		noiseOffset = new Offset();
	}
	public TerrainMaker(int width, int height, int size, long seed)
	{
		grid = new Grid(width,height,size);
		textures = new Textures();
		this.seed=seed;
		noiseOffset = new Offset();
	}

	public Grid getGrid()
	{
		return grid;
	}





	public TerrainMaker setSeed(long seed)
	{
		this.seed = seed;
		return this;
	}

	public TerrainMaker setRandomSeed()
	{
		Random r = new Random();
		seed = r.nextLong();
		this.seed = seed;
		return  this;
	}

	public long getSeed()
	{
		return seed;
	}

	public Grid noiseBased(int divider, long seed)
	{
//		System.out.println("Map seed: "+seed);
		OpenSimplexNoise noise = new OpenSimplexNoise(seed);

		for (int i = 0; i < grid.getWidth(); i++)
			for (int j = 0; j < grid.getHeight(); j++)
				try{
					double rand = Math.abs(noise.eval((double)(i+noiseOffset.getX())/divider,(double)(j+noiseOffset.getY())/divider)) ;
					if(rand>0.6)
						grid.tile(i,j).setTextures(textures.water);
					else if(rand>0.5)
						grid.tile(i,j).setTextures(textures.sand);
					else
						grid.tile(i,j).setTextures(textures.grass);
				}
				catch (Exception e)
				{
					System.out.println("TerrainMaker texture set error: "+e.getCause());
				}

		return grid;
	}

	public Grid noiseBased(int divider)
	{
		return noiseBased(divider,seed);
	}




	public Grid randomBased()
	{
		Image[][] lookup=
				{
						textures.sand,
						textures.water,
						textures.grass
				};
		Random r=new Random();
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
					System.out.println("TerrainMaker texture set error");
				}
			}
		}
		return grid;
	}

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
				water = new Image[]{
						new ImageIcon(getClass().getClassLoader().getResource("terrain/water.png")).getImage(),
				};
			}
			catch (Exception e)
			{
				System.out.println("Failed to load water texture(s)");
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
				System.out.println("Failed to load sand texture(s)");
			}

			//sand
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
				System.out.println("Failed to load sand texture(s)");
			}
		}

	}
}
