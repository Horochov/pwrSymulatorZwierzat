package PwrGame;

import PwrGame.Animals.Animal;
import PwrGame.Animals.Hare;
import PwrGame.Animals.Wolf;
import PwrGame.Terrain.*;
import PwrGame.Terrain.OpenSimplexNoise.OpenSimplexNoise;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

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


	public Game(byte gridWidth, byte gridHeight, byte gridSize, int wolfCount, int hareCount,
	            int bushCount, int treeCount, int rockCount)
	{
		animals = new Vector<>();
		tiles=new Vector<>();
		this.gridWidth=gridWidth;
		this.gridHeight=gridHeight;
		this.gridSize=gridSize;
		prepareGraphics();
		prepareTerrain(8);
		placeObstacles(bushCount,treeCount,rockCount);
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
	private void placeObstacles(int bushCount, int treeCount, int rockCount)
	{
		Random r = new Random();
		while (treeCount>0)
		{

			//Find random grass tile, but not topmost one
			int idxDwn = r.nextInt(tiles.size());
			if((!(tiles.elementAt(idxDwn) instanceof Grass))
					||(tiles.elementAt(idxDwn).getPosition().getY()==0))
				continue;

			//search for tree corona tile
			Position topPos = tiles.elementAt(idxDwn).getPosition();
			topPos.modifyY(-40);
			int idxTop;
			for (idxTop = 0; idxTop < tiles.size(); idxTop++)
			{
				if(tiles.elementAt(idxTop).getPosition().equals(topPos))
					break;
			}
			//check if it is "standard" tile
			if(!(tiles.elementAt(idxTop) instanceof Grass||
					tiles.elementAt(idxTop) instanceof Water||
					tiles.elementAt(idxTop) instanceof Sand))
				continue;


			//place tree stump
			tiles.add(new Tree(tiles.elementAt(idxDwn),gridSize,true));
			tiles.add(new Tree(tiles.elementAt(idxTop),gridSize,false));

			//delete greater index first
			tiles.removeElementAt(idxDwn>idxTop?idxDwn:idxTop);
			tiles.removeElementAt(idxDwn<idxTop?idxDwn:idxTop);
			treeCount--;
		}
		while (bushCount>0)
		{

			//Find random grass tile
			int idx = r.nextInt(tiles.size());
			if(!(tiles.elementAt(idx) instanceof Grass))
			{
				continue;
			}

			//save location and delet' it
			Position pos = tiles.elementAt(idx).getPosition();
			tiles.removeElementAt(idx);

			//place Bush. George Bush.
			tiles.add(new Bush(pos,gridSize));

			bushCount--;
		}
		while (rockCount>0)
		{

			//Find random tile
			int idx = r.nextInt(tiles.size());
			if(!(tiles.elementAt(idx) instanceof Grass || tiles.elementAt(idx) instanceof Sand))
				continue;

			//save location and delet' it

			//place Bush. George Bush.
			tiles.add(new Rock(tiles.elementAt(idx),gridSize));

			tiles.removeElementAt(idx);

			rockCount--;
		}

	}

	private Position unoccupiedTile()
	{
		Random r = new Random();

		Set<Position> ocuppiedPos = new HashSet<Position>();
		tiles.forEach(tile -> {
			if(!tile.isAccessible())
				ocuppiedPos.add(tile.getPosition());
		});
		animals.forEach(animal -> {
			ocuppiedPos.add(animal.getPosition());
		});

		if(ocuppiedPos.size()>=gridWidth*gridHeight)
		{
			System.out.println("Error: cannot find unoccupied tile!");
			return null;
		}

		while(true)
		{
			Position pos = new Position(r.nextInt(gridWidth) * gridSize, r.nextInt(gridHeight) * gridSize);
			if (ocuppiedPos.contains(pos))
				continue;
			return pos;
		}
	}

	private void prepareAnimals(int wolfCnt, int hareCnt)
	{
		//#Todo: proper animal placement (groups?)
		try
		{
			Random r = new Random();

			for (int i = 0; i < wolfCnt; i++)
			{
				Position pos=unoccupiedTile();
				if(pos==null)
				{
					System.out.println("Warning: Tried to place too many animals, ammount has been limited.");
					return;
				}
				animals.add(
						new Wolf(pos, gridSize, 200, r.nextInt(8640), 5, 50, 1000, 700, 1000, 200, 200, 20)
				);
			}
			for (int i = 0; i < hareCnt; i++)
			{
				Position pos=unoccupiedTile();
				if(pos==null)
				{
					System.out.println("Warning: Tried to place too many animals, ammount has been limited.");
					return;
				}
				animals.add(
						//TODO: update constructor parameters
						new Hare(pos,gridSize, 100, r.nextInt(7560), 3, 0, 1000, 700, 1000, 50, 50, 10)
				);
			}
		}
		catch (Exception e)
		{
			System.out.println("Animal placement error: "+e.getCause());
			System.exit(6);
		}
	}

	private void animal2corpse()
	{
		/*
		 *  Search for dead animal and replace it with corpse.
		 *  Will delete animal and tile, then create tile <animal>Corpse
		 */
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
//		animals.forEach(animal -> animal.process(tiles,animals));
		for (int i = 0; i < animals.size(); i++) {
			animals.elementAt(i).process(tiles,animals);
		}
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

	public int getHareCount()
	{
		int i=0;
		for(Animal animal: animals)
			if(animal instanceof Hare)
				i++;
		return i;
	}
	public int getWolfCount()
	{
		int i=0;
		for(Animal animal: animals)
			if(animal instanceof Wolf)
				i++;
		return i;
	}
	public int getAnimalCount()
	{
		return animals.size();
	}

}
