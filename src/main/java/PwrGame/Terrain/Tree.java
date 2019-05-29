package PwrGame.Terrain;

import javax.swing.*;
import java.awt.*;

public class Tree extends Tile
{
	private Image textureBack;
	public Tree(Tile backgroundTile, byte size, Boolean isBottom)
	{
		this.position=backgroundTile.getPosition();
		this.size=size;

		try
		{
			if (backgroundTile instanceof Grass||isBottom)
				textureBack= new ImageIcon(getClass().getClassLoader().getResource("terrain/grass0.png")).getImage();
			else if(backgroundTile instanceof Sand)
				textureBack= new ImageIcon(getClass().getClassLoader().getResource("terrain/sand.png")).getImage();
			else
				textureBack= new ImageIcon(getClass().getClassLoader().getResource("terrain/water.png")).getImage();

			texture = new  ImageIcon(getClass().getClassLoader().getResource("terrain/tree"+(isBottom?"Bottom":"Top")+".png")).getImage();
		}
		catch (Exception e)
		{
			System.out.println("Failed to load tree texture");
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
