package Terrain;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Tile
{
	Boolean accessible;
	public Image[] texture;
	Resource grass;
	Resource corpse;

	public Tile(Image[] textures, Boolean isAccessible, Resource grass, Resource corpse)
	{
		this.texture=textures;
		this.accessible=isAccessible;
		this.grass=grass;
		this.corpse=corpse;
	}

	public Tile(Image[] textures, Boolean isAccessible)
	{

		this.texture=textures;
		this.accessible=isAccessible;
		this.grass=null;
		this.corpse=null;
	}

	public Tile(Image[] textures)
	{
		this.texture=textures;
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

	public void process()
	{

	}

	public Image getTexture()
	{
		//TODO change images depending on state
		Random r=new Random();
		return texture[r.nextInt(texture.length)];
	}

	public void setTextures(Image[] texture)
	{
		this.texture = texture;
	}

}
