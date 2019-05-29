package PwrGame.Terrain;

import javax.swing.*;
import java.awt.*;

public class WolfCorpse	extends Tile
{
	protected Image textureBack;
	public WolfCorpse(Tile backgroundTile)
	{
		this.position=backgroundTile.getPosition();
		this.size=backgroundTile.size;

		if (backgroundTile instanceof Grass)
			textureBack= new ImageIcon(getClass().getClassLoader().getResource("terrain/grass0.png")).getImage();
		else
			textureBack= new ImageIcon(getClass().getClassLoader().getResource("terrain/sand.png")).getImage();

		try
		{
			texture  = new ImageIcon(getClass().getClassLoader().getResource("terrain/wolf1a.png")).getImage();
		}
		catch (Exception e)
		{
			System.out.println("Failed to load dead wolf texture(s)");
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
