package Terrain;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Grid
{
	private Tile grid[][];
	private int size,
			width,
			height;

//	Image img=   new ImageIcon(getClass().getClassLoader().getResource("terrain/water.png")).getImage();

	public Grid(int gridWidth, int gridHeight, int size)
	{
		grid= new Tile[gridWidth][gridHeight];
		for (int i = 0; i < gridWidth; i++)
		{
			for (int j = 0; j < gridHeight; j++)
			{
				grid[i][j]= new Tile();
			}
		}
		if(grid[1][1]==null)
			System.out.println("nullTile");

		this.size=size;
		this.width=gridWidth;
		this.height=gridHeight;

	}



	public Tile tile(int x, int y)
	{
		if(x<0||y<0||x>width||y>height)
		{
			System.out.println("grid.tile() out of bound");
			return null;
		}
		return grid[x][y];
	}

	public void draw(Graphics g)
	{
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				g.drawImage(tile(i,j).getTexture(),size*i,size*j,null);
			}
		}
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public int getSize()
	{
		return size;
	}
}
