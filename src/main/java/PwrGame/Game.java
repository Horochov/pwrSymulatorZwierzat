package PwrGame;

import PwrGame.Animals.Animal;
import PwrGame.Animals.Hare;
import PwrGame.Animals.Wolf;
import PwrGame.Terrain.OpenSimplexNoise.OpenSimplexNoise;
import PwrGame.Terrain.Textures;
import PwrGame.Terrain.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.Vector;

public class Game
{
	/*
	 *  Contains Animal and Tile lists
	 *  Calls process() on them
	 *  Manages entire game
	 *  Responsible for drawing graphics on JPanel
	 */

	//graphics
	private JPanel panel;
	private BufferedImage img;
	private Graphics imgG;
	private byte gridHeight;   //vertical (y) element  count
	private byte gridWidth;    //horizontal (x) element  count
	private byte gridSize;     //grid element size
//	private int tps;           //ticks per second

	//resources
	private Vector<Animal> animals;
	private Vector<Tile> tiles;


	public Game(byte gridWidth, byte gridHeight, byte gridSize, byte wolfCount, byte hareCount )
	{
		animals = new Vector<>(255);
		tiles=new Vector<>(255);
		this.gridWidth=gridWidth;
		this.gridHeight=gridHeight;
		this.gridSize=gridSize;
//		this.tps=ticksPerSecond;
		prepareGraphics();
		prepareTerrain(8);
		prepareAnimals(wolfCount,hareCount);
	}

	private void prepareGraphics()
	{
		//Creates image to draw on, JPanel to display via Main
		img=new BufferedImage(gridSize*gridWidth,gridSize*gridHeight, BufferedImage.TYPE_INT_ARGB);
		imgG=img.getGraphics();

		panel = new JPanel()
		{
			//Woah, lovely way to override!
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Creating a copy of the Graphics
				// so any reconfiguration we do on
				// it doesn't interfere with what
				// Swing is doing.
				Graphics2D g2 = (Graphics2D) g.create();
				float scale=Math.min((float)getParent().getWidth()/gridWidth,(float)getParent().getHeight()/gridHeight)/gridSize;
				int w= (int) (img.getWidth()*scale),
						h= (int) (img.getHeight()*scale);
				g2.drawImage(img, 0, 0, w,h, null);
				g2.dispose();
			}
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(img.getWidth(), img.getHeight());
			}

		};
	}

	private void prepareTerrain(int divider)
	{
		Textures textures= new Textures();
		Random r = new Random();
		long seed=r.nextLong();
		OpenSimplexNoise noise = new OpenSimplexNoise(seed);
		System.out.println("Map seed: "+seed);
		for (int i = 0; i < gridWidth; i++)
			for (int j = 0; j < gridHeight; j++)
			{
				double rand = Math.abs(noise.eval((double) i / divider, (double) j / divider));

				if (rand > 0.6)
					tiles.add(new Tile(textures.getWater(), false, new Position(i * gridSize, j * gridSize), gridSize,
					                   Tile.ResourceType.water, (byte) 1, (byte) 1, (byte) 1, (byte) 1)
					          //Y'know, casting literal to byte... Just Java things.
					);
				else if (rand > 0.5)
					tiles.add(new Tile(textures.getSand(), true, new Position(i * gridSize, j * gridSize), gridSize)

					);
				else
				{
					tiles.add(new Tile(textures.getGrass(), true, new Position(i * gridSize, j * gridSize), gridSize,
					                   Tile.ResourceType.grass, (byte)r.nextInt(3), (byte)textures.getGrass().length, (byte) 100, (byte) 1));
				}
			}
	}
	private void prepareAnimals(byte wolfCnt, byte hareCnt)
	{
		//#Todo: proper animal placement (groups?)
		try
		{
			Random r = new Random();

			for (int i = 0; i < wolfCnt; i++)
			{
				//check if another animal isn't there
				Position pos = new Position(r.nextInt(gridWidth)*gridSize,r.nextInt(gridHeight)*gridSize);
				for (int j = 0; j < animals.size(); j++)
				{
					if(pos.equals(animals.elementAt(j).getPosition()))
					{
						//generate new pos and start searching again
						//TODO: handle animal overflow (more animals than grid allows)
						pos = new Position(r.nextInt(gridWidth)*gridSize,r.nextInt(gridHeight)*gridSize);
						j=0;
					}
				}
				animals.add(
						//TODO: update constructor parameters
						new Wolf(pos, gridSize, 10, r.nextInt(84), 5, 0, 100)
				);
			}
			for (int i = 0; i < hareCnt; i++)
			{
				//check if another animal isn't there
				Position pos = new Position(r.nextInt(gridWidth)*gridSize,r.nextInt(gridHeight)*gridSize);
				for (int j = 0; j < animals.size(); j++)
				{
					if(pos.equals(animals.elementAt(j).getPosition()))
					{
						//generate new pos and start searching again
						//TODO: handle animal overflow (more animals than grid allows)
						pos = new Position(r.nextInt(gridWidth)*gridSize,r.nextInt(gridHeight)*gridSize);
						j=0;
					}
				}
				animals.add(
						//TODO: update constructor parameters
						new Hare(pos,gridSize, 30, r.nextInt(96), 3, 2, 100)
				);
			}
		}
		catch (Exception e)
		{
			System.out.println("Terrain texture set error: "+e.getCause());
			System.exit(4);
		}
	}

	public void process()
	{
		tiles.forEach(tile -> tile.process());
		animals.forEach(animal -> animal.process());
	}

	public void display()
	{
		tiles.forEach(tile -> tile.display(imgG));
		animals.forEach(animal -> animal.display(imgG));
	}

	public JPanel getPanel()
	{
		return panel;
	}
}
