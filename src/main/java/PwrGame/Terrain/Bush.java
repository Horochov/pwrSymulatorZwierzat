package PwrGame.Terrain;

import PwrGame.Position;

import javax.swing.*;
import java.awt.*;

public class Bush extends Tile
{
	private Image textureBack;
	public Bush(Position position, byte size)
	{
		this.position=position;
		this.size=size;

		try
		{
			textureBack = new  ImageIcon(getClass().getClassLoader().getResource("terrain/grass0.png")).getImage();
			texture = new  ImageIcon(getClass().getClassLoader().getResource("terrain/bush.png")).getImage();
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
