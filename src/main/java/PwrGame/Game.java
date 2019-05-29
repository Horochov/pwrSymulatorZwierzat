package PwrGame;

import PwrGame.Animals.Animal;
import PwrGame.Animals.Hare;
import PwrGame.Animals.Wolf;
import PwrGame.Terrain.*;
import PwrGame.Terrain.OpenSimplexNoise.OpenSimplexNoise;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
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

	//resources
	private Vector<Animal> animals;
	private Vector<Tile> tiles;


	public Game(byte gridWidth, byte gridHeight, byte gridSize, int wolfCount, int hareCount )
	{
		animals = new Vector<>(255);
		tiles=new Vector<>(255);
		this.gridWidth=gridWidth;
		this.gridHeight=gridHeight;
		this.gridSize=gridSize;
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
		Random r = new Random();
		long seed=r.nextLong();
		OpenSimplexNoise noise = new OpenSimplexNoise(seed);
		System.out.println("Map seed: "+seed);
		for (int i = 0; i < gridWidth; i++)
			for (int j = 0; j < gridHeight; j++)
			{
				double rand = Math.abs(noise.eval((double) i / divider, (double) j / divider));

				if (rand > 0.6)
					tiles.add(new Water(new Position(i * gridSize, j * gridSize), gridSize)
					);
				else if (rand > 0.5)
					tiles.add(new Sand(new Position(i * gridSize, j * gridSize), gridSize)
					);
				else
				{
					tiles.add(new Grass(new Position(i * gridSize, j * gridSize), gridSize));
				}
			}
	}

	private Position randAccessibleTile()
	{
		Random r = new Random();
		Position pos = new Position(r.nextInt(gridWidth)*gridSize,r.nextInt(gridHeight)*gridSize);
		for (int t = 0; t < 20; t++)
		{
			for (int j = 0; j < animals.size(); j++)
			{
				if (pos.equals(animals.elementAt(j).getPosition()))
				{
					//generate new pos and start searching again
					//TODO: handle animal overflow (more animals than grid allows)
					pos = new Position(r.nextInt(gridWidth) * gridSize, r.nextInt(gridHeight) * gridSize);
					j = 0;
				}
			}
			for (int j = 0; j < tiles.size(); j++)
			{
				if (pos.equals(tiles.elementAt(j).getPosition()))
				{
					if(tiles.elementAt(j).isAccessible())
						return pos;
					//generate new pos and start searching again
					pos = new Position(r.nextInt(gridWidth) * gridSize, r.nextInt(gridHeight) * gridSize);
					j = 0;
				}
			}
		}
		System.out.println("Search for accessible tile: exceeded 10 tries");
		return pos;
	}
	private void prepareAnimals(int wolfCnt, int hareCnt)
	{
		//#Todo: proper animal placement (groups?)
		try
		{
			Random r = new Random();

			for (int i = 0; i < wolfCnt; i++)
			{
				animals.add(
						new Wolf(randAccessibleTile(), gridSize, 200, r.nextInt(96), 5, 50, 1000, 700, 1000, 200, 200, 20)
				);
			}
			for (int i = 0; i < hareCnt; i++)
			{
				//check if another animal isn't there
				animals.add(
						//TODO: update constructor parameters
						new Hare(randAccessibleTile(),gridSize, 100, r.nextInt(84), 3, 0, 1000, 700, 1000, 50, 50, 10)
				);
			}
		}
		catch (Exception e)
		{
			System.out.println("Terrain texture set error: "+e.getCause());
			System.exit(4);
		}
	}

	private void animal2corpse()
	{
		for (int i = 0; i <animals.size() ; i++)
		{
			//if animal is dead
			if(!animals.elementAt(i).isAlive())
			{
				Position position = animals.elementAt(i).getPosition();
				//get tile index at animal position
				int j;
				Vector<Tile> til = TerrainHandler.limitViewSquare(tiles, position, gridSize-1); //should round value to 1 tile

				for (j = 0; j < tiles.size(); j++)
				{
					if (til.elementAt(0).getPosition().equals(tiles.elementAt(j).getPosition()))
						break;
				}
				//add corpse
				if(animals.elementAt(i) instanceof Hare)
					tiles.add(
							new HareCorpse(tiles.elementAt(j))
					);
				else
					tiles.add(
							new WolfCorpse(tiles.elementAt(j))
					);

				animals.remove(i);
				tiles.remove(j);

			}
		}
	}


	public void process()
	{
		animal2corpse();
		tiles.forEach(tile -> tile.process());
		animals.forEach(animal -> animal.process(tiles,animals));
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
