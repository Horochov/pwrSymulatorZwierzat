package PwrGame.Terrain;

import PwrGame.Position;

import javax.swing.*;
import java.awt.*;

public class Rock extends Tile
{
	private Image textureBack;
	public Rock(Tile backgroundTile, byte size)
	{
		this.position=backgroundTile.getPosition();
		this.size=size;

		try
		{
			if (backgroundTile instanceof Grass)
				textureBack= new ImageIcon(getClass().getClassLoader().getResource("terrain/backg/grass0.png")).getImage();
			else
				textureBack= new ImageIcon(getClass().getClassLoader().getResource("terrain/backg/sand.png")).getImage();

			texture = new  ImageIcon(getClass().getClassLoader().getResource("terrain/obstacle/rock.png")).getImage();
		}
		catch (Exception e)
		{
			System.out.println("Failed to load bush texture");
			System.exit(3);
		}

		accessible=false;
	}

	@Override
	public void display(Graphics g)
	{
		g.drawImage(textureBack,position.getX(),position.getY(),size,size,null);
		g.drawImage(texture, position.getX(), position.getY(), size, size, null);
	}
}
