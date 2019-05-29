package PwrGame.Terrain;

import PwrGame.Position;

import javax.swing.*;
import java.awt.*;

public class Sand
		extends Tile
{
	public Sand(Position position, byte size)
	{
		this.position=position;
		this.size=size;

		try
		{
			texture = new  ImageIcon(getClass().getClassLoader().getResource("terrain/sand.png")).getImage();
		}
		catch (Exception e)
		{
			System.out.println("Failed to load sand texture");
			System.exit(3);
		}

		accessible=true;
	}
}
