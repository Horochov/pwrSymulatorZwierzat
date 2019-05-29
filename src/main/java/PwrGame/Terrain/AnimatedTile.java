package PwrGame.Terrain;


import java.awt.*;

public abstract class AnimatedTile extends Tile
{
	protected byte minCount;
	protected byte maxCount;
	protected byte count;

	protected int ticksPerGrowth;
	protected int tickCounter = 0;
	protected byte addPerTick;

	protected Image textures[];

	public void process()
	{
		//if value is not intended to change (!addPerTick), then skip
		//wait ticksPerGrowth times until processing.
		//change count and apply boundaries
		if (addPerTick != 0)
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

	public byte getResourceCount()
	{
		return count;
	}

	public boolean consumeResource()
	{
		//returns true if operation succeeded
		if (count > minCount)
		{
			count--;
			return true;
		}
		return false;
	}

	public void display(Graphics g)
	{
		//select image to display and do it
		g.drawImage(textures[count], position.getX(), position.getY(), size, size, null);
	}

}