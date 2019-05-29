package PwrGame.Terrain;

import PwrGame.Animals.Animal;
import PwrGame.Position;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class HareCorpse
		extends AnimatedTile
{
	public HareCorpse(Tile backgroundTile)
	{
		this.position=backgroundTile.getPosition();
		this.size=backgroundTile.size;

		if (backgroundTile instanceof Grass)
			texture= new ImageIcon(getClass().getClassLoader().getResource("terrain/grass0.png")).getImage();
		else
			texture= new ImageIcon(getClass().getClassLoader().getResource("terrain/sand.png")).getImage();

		try
		{
			textures  = new Image[]{
					new ImageIcon(getClass().getClassLoader().getResource("terrain/hare4.png")).getImage(),
					new ImageIcon(getClass().getClassLoader().getResource("terrain/hare3.png")).getImage(),
					new ImageIcon(getClass().getClassLoader().getResource("terrain/hare2.png")).getImage(),
					new ImageIcon(getClass().getClassLoader().getResource("terrain/hare1.png")).getImage(),
					};
		}
		catch (Exception e)
		{
			System.out.println("Failed to load dead hare texture(s)");
			System.exit(3);
		}

		accessible=false;

		ticksPerGrowth=200;
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
