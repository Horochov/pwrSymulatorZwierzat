package Terrain;

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

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count>maxCount?maxCount:count;
	}

	public int getMaxCount()
	{
		return maxCount;
	}

	public void setMaxCount(int maxCount)
	{
		this.maxCount = maxCount;
		if(count>maxCount)
			count=maxCount;
	}

	public int getMinCount()
	{
		return minCount;
	}

	public void setMinCount(int minCount)
	{
		this.minCount = minCount;
		if(count<minCount)
			count=minCount;

	}

	public int getRatio()
	{
		return ratio;
	}

	public void setRatio(int ratio)
	{
		this.ratio = ratio;
	}

	public void setTextures(Image[] textures)
	{
		this.textures = textures;
	}

	public Image getTexture()
	{
		if(textures.length<=0)
		{
			System.out.println("Resource: no textures");
			return null;
		}
		return textures[(textures.length)*(count-1)/maxCount];
	}

	public void process()
	{
		setCount(getCount()+getRatio());
	}

	public boolean isAnyResource()
	{
		return (count-minCount)>0;
	}
}
