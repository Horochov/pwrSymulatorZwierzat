package PwrGame.Terrain;

import javax.swing.*;
import java.awt.*;

public class WolfCorpse	extends AnimatedTile
{
	public WolfCorpse(Tile backgroundTile)
	{
		this.position=backgroundTile.getPosition();
		this.size=backgroundTile.size;

		if (backgroundTile instanceof Grass)
			texture= new ImageIcon(getClass().getClassLoader().getResource("terrain/backg/grass0.png")).getImage();
		else
			texture= new ImageIcon(getClass().getClassLoader().getResource("terrain/backg/sand.png")).getImage();

		try
		{
			textures  = new Image[]{
					new ImageIcon(getClass().getClassLoader().getResource("terrain/corpse/wolf2.png")).getImage(),
					new ImageIcon(getClass().getClassLoader().getResource("terrain/corpse/wolf1a.png")).getImage(),
			};
		}
		catch (Exception e)
		{
			System.out.println("Failed to load dead wolf texture(s)");
			System.exit(3);
		}

		accessible=false;

		ticksPerGrowth=800;
		addPerTick=-1;
		maxCount= (byte) (textures.length-1);
		count= maxCount;

	}

	@Override
	public void display(Graphics g)
	{
		g.drawImage(texture,position.getX(),position.getY(),size,size,null);
		g.drawImage(textures[count], position.getX(), position.getY(), size, size, null);
	}
}
