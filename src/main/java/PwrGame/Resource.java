package PwrGame;

import java.awt.*;
import java.util.Random;

public class Resource
{
	int count;
	int maxCount;   //TODO: Resource subclass with static min/max and textures
	int minCount;
	int ratio;
	Image[] textures;

	public Resource(Image[] textures, int minCount, int maxCount, int ratio, int count)
	{
		this.textures=textures;
		this.count=count;
		this.maxCount=maxCount;
		this.minCount=minCount;
		this.ratio=ratio;
	}

	public Resource(Image[] textures, int minCount, int maxCount, int ratio)
	{
		this.textures=textures;
		this.maxCount=maxCount;
		this.minCount=minCount;
		this.ratio=ratio;
		Random r = new Random();

		this.count=r.nextInt(maxCount-minCount+1)+minCount;
	}

	protected int getCount()
	{
		return count;
	}

	protected void setCount(int count)
	{
		this.count = count>maxCount?maxCount:count;
	}

	protected int getMaxCount()
	{
		return maxCount;
	}

	protected void setMaxCount(int maxCount)
	{
		this.maxCount = maxCount;
		if(count>maxCount)
			count=maxCount;
	}

	protected int getMinCount()
	{
		return minCount;
	}

	protected void setMinCount(int minCount)
	{
		this.minCount = minCount;
		if(count<minCount)
			count=minCount;

	}

	protected int getRatio()
	{
		return ratio;
	}

	protected void setRatio(int ratio)
	{
		this.ratio = ratio;
	}

	protected void setTextures(Image[] textures)
	{
		this.textures = textures;
	}

	protected Image getTexture()
	{
		if(textures.length<=0)
		{
			System.out.println("Resource: no textures");
			return null;
		}
		return textures[(textures.length)*(count-1)/maxCount];
	}

	protected void process()
	{
		setCount(getCount()+getRatio());
	}

	protected boolean isAnyResource()
	{
		return (count-minCount)>0;
	}
}
