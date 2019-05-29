package PwrGame.Terrain;

import PwrGame.Position;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Water extends Tile
{
	public Water(Position position, byte size)
	{
		this.position=position;
		this.size=size;

		try
		{
			texture = new  ImageIcon(getClass().getClassLoader().getResource("terrain/backg/water.png")).getImage();
		}
		catch (Exception e)
		{
			System.out.println("Failed to load water texture");
			System.exit(3);
		}

		accessible=false;
	}

	@Override
	public byte getResourceCount()
	{
		return 1;
	}

	@Override
	public boolean consumeResource()
	{
		return true;
	}
}
