package PwrGame.Terrain;

import PwrGame.Position;

import java.awt.*;

public abstract class Tile
{
	/*
	 *  Contains background image and resource information
	 */
	public enum ResourceType
	{
		none,
		water,
		grass,
		corpse,
	}


	protected Boolean accessible;
	protected  Boolean occupied;
	protected Position position;
	protected byte size;
	protected Image texture;

	public Boolean isAccessible()
	{
		//false for rocks, trees, water
		return accessible;
	}

	public void process()
	{

	}

	public void display(Graphics g)
	{
		g.drawImage(texture,position.getX(),position.getY(),size,size,null);
	}

	public byte getResourceCount()
	{
		return 0;
	}

	public boolean consumeResource()
	{
		return false;
	}

	public Position getPosition()
	{
		return new Position(position);
	}

	public void setOccupied(Boolean occupied)
	{
		this.occupied = occupied;
	}

	public Boolean isOccupied()
	{
		return occupied;
	}
}
