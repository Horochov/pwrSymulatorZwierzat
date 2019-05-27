package PwrGame.Terrain;

import PwrGame.Position;

import java.awt.*;

public class Tile
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


	private Boolean accessible;
	private Image textures[];
	private Position position;
	private byte size;

	ResourceType resType;
	private byte maxCount;
	static byte minCount;
	private byte count;
	private byte ticksPerGrowth;
	private byte tickCounter=0;
	private byte addPerTick;



	public Tile(Image[] textures, Boolean isAccessible, Position position, byte size)
	{
		this.textures=textures;
		this.position=position;
		this.size=size;
		this.accessible=isAccessible;
		this.resType=ResourceType.none;
		this.ticksPerGrowth=0;
		this.count=0;
		this.maxCount=0;
		this.addPerTick=0;
	}

	public Tile(Image[] textures, Boolean isAccessible, Position position, byte size,
	            ResourceType resourceType, byte count, byte maxCount, byte ticksPerGrowth, byte addCntPerTick)
	{
		this.textures=textures;
		this.position=position;
		this.size=size;
		this.accessible=isAccessible;
		this.resType=resourceType;
		this.ticksPerGrowth=ticksPerGrowth;
		this.count=count;
		this.maxCount=maxCount;
		this.addPerTick=addCntPerTick;
	}

	public void setAccessible(Boolean accessible)
	{
		this.accessible = accessible;
	}

	public Boolean isAccessible()
	{
		//false for rocks, trees, water
		return accessible;
	}

	public void process()
	{
		//if value is not intended to change (!addPerTick), then skip
		//wait ticksPerGrowth times until processing.
		//change count and apply boundaries
		if(addPerTick!=0)
		{
			if (++tickCounter > ticksPerGrowth)
			{
				byte cnt = (byte) (count + addPerTick);   //rly? bytes are promoted to ints during addition? Overflow safe, but still...
				if (cnt < minCount)
					cnt = minCount;
				if (cnt > maxCount)
					cnt = maxCount;
				count = cnt;
				tickCounter = 0;
			}
		}
	}

	public void display(Graphics g)
	{
		//select image to display and do it
		Image current=null;
		if(maxCount!=0)
		{
			int idx = (textures.length - 1) * (count) / maxCount;
			current = textures[idx];
		}
		else
			current = textures[0];
		g.drawImage(current,position.getX(),position.getY(),size,size,null);
	}

	public ResourceType getResourceType()
	{
		return resType;
	}

	public byte getResourceCount()
	{
		return count;
	}

	public boolean consumeResource()
	{
	//returns true if operation succeeded
		if(count>minCount)
		{
			count--;
			return true;
		}
		return false;
	}

	public Position getPosition()
	{
		return position;
	}
}
