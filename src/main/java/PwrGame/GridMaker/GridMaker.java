package PwrGame.GridMaker;

import OpenSimplexNoise.OpenSimplexNoise;
import PwrGame.Grid;
import PwrGame.Resource;

import java.util.Random;

public class GridMaker
{
	public Textures textures;
	private Grid grid;
	private long seed;
	public Offset noiseOffset;


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
							grid.tile(i,j).setGrass(new Resource(textures.getGrass(), 0, 1000, 1, grassCnt));

						//#Todo: proper animal placement
						if(r.nextInt(100)>90)
						{
							int a = r.nextInt(84);
							int x = i;
							int y = j;
							grid.tile(i, j).setAnimal(new Hare(grid, 10, a, 5, 0, 100, x, y));
						}
						if(r.nextInt(100)>90)
						{
							int a = r.nextInt(96);
							int x = i;
							int y = j;
							grid.tile(i, j).setAnimal(new Wolf(grid, 30, a, 3, 2, 100, x, y));
						}
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






}
