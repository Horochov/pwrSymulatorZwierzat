package PwrGame.Terrain;

import PwrGame.Position;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Grass extends AnimatedTile
{
	public Grass(Position position, byte size)
	{
		this.position=position;
		this.size=size;

		try
		{
			textures = new Image[]{
					new ImageIcon(getClass().getClassLoader().getResource("terrain/grass0.png")).getImage(),
					new ImageIcon(getClass().getClassLoader().getResource("terrain/grass1.png")).getImage(),
					new ImageIcon(getClass().getClassLoader().getResource("terrain/grass2.png")).getImage(),
					new ImageIcon(getClass().getClassLoader().getResource("terrain/grass3.png")).getImage(),
					};
		}
		catch (Exception e)
		{
			System.out.println("Failed to load grass texture(s)");
			System.exit(3);
		}

		accessible=true;

		Random r = new Random();
		ticksPerGrowth=3000+r.nextInt(3000);
		tickCounter=r.nextInt(3000);
		addPerTick=1;
		maxCount= (byte) (textures.length-1);
		count= (byte) r.nextInt(maxCount);
	}

}
