package Terrain;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tile
{
	private Boolean accessible;
	private Image texture;
	Resource grass;
	Resource corpse;
//	Animal animal;

	public Tile(Image texture, Boolean isAccessible, Resource grass, Resource corpse)
	{
		this.texture=texture;
		this.accessible=isAccessible;
		this.grass=grass;
		this.corpse=corpse;
	}

	public Tile(Image texture, Boolean isAccessible)
	{

		this.texture=texture;
		this.accessible=isAccessible;
		this.grass=null;
		this.corpse=null;
	}

	public Tile(Image texture)
	{
		this.texture=texture;
		this.accessible=true;
		this.grass=null;
		this.corpse=null;
	}

	public Tile()
	{
		this.texture=null;
		this.accessible=true;
		this.grass=null;
		this.corpse=null;
	}

	public void setAccessible(Boolean accessible)
	{
		this.accessible = accessible;
	}

	public Boolean isAccessible()
	{
		return accessible;
	}

	public void setCorpse(Resource corpse)
	{
		this.corpse = corpse;
	}

	public void setGrass(Resource grass)
	{
		this.grass = grass;
	}

	public Resource getCorpse()
	{
		return corpse;
	}

	public Resource getGrass()
	{
		return grass;
	}

	public void process()
	{
		try
		{
			corpse.process();
		}
		catch (NullPointerException e)
		{

		}
		try
		{
			grass.process();
		}
		catch (NullPointerException e)
		{

		}


	}

	public Image getTexture()
	{
		BufferedImage rsrc= new BufferedImage(40,40, BufferedImage.TYPE_INT_ARGB);
		Graphics g=rsrc.getGraphics();
		g.drawImage(this.texture,0,0,40,40,null);
		boolean drawn=false;
		if(corpse!=null)
		{
			if (corpse.isAnyResource())
			{
				g.drawImage(corpse.getTexture(), 40, 40, null);
				drawn = true;
			}
		}
		else if((!drawn)&&grass!=null)
		{
			if (grass.isAnyResource())
			{
				g.drawImage(grass.getTexture(),0,0, 40, 40, null);
				drawn = true;
			}
		}
//		if(animal!=null)
//			g.drawImage(animal.getTexture(),40,40,null);

		return rsrc;
	}

	public void setTexture(Image texture)
	{
		this.texture = texture;
	}

}
