package Terrain;

import OpenSimplexNoise.OpenSimplexNoise;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GridMaker
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

	public GridMaker(int width, int height, int size)
	{
		grid = new Grid(width,height,size);
		textures = new Textures();
		seed=0;
		noiseOffset = new Offset();
	}
	public GridMaker(int width, int height, int size, long seed)
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





	public GridMaker setSeed(long seed)
	{
		this.seed = seed;
		return this;
	}

	public GridMaker setRandomSeed()
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
		grid = new Grid(grid.getWidth(),grid.getHeight(),grid.getSize());
//		System.out.println("Map seed: "+seed);
		OpenSimplexNoise noise = new OpenSimplexNoise(seed);
		Random r = new Random();
		for (int i = 0; i < grid.getWidth(); i++)
			for (int j = 0; j < grid.getHeight(); j++)
				try{
					double rand = Math.abs(noise.eval((double)(i+noiseOffset.getX())/divider,(double)(j+noiseOffset.getY())/divider)) ;
					if(rand>0.6)
						grid.tile(i,j).setTexture(textures.getWater());
					else if(rand>0.5)
						grid.tile(i,j).setTexture(textures.getSand());
					else
					{
						grid.tile(i, j).setTexture(textures.getDirt());
						int grassCnt=r.nextInt(2000)-1000;
						if(grassCnt>0)
							grid.tile(i,j).setGrass(new Resource(textures.getGrass(),0,1000,1, grassCnt));

					}
				}
				catch (Exception e)
				{
					System.out.println("GridMaker texture set error: "+e.getCause());
				}

		return grid;
	}

	public Grid noiseBased(int divider)
	{
		return noiseBased(divider,seed);
	}




	class Textures
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

}
