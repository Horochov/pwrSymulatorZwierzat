package PwrGame.GridMaker;

public class Offset
{
	private int offsetX, offsetY;

	public Offset(int x, int y)
	{
		offsetX=x;
		offsetY=y;
	}
	public Offset()
	{
		offsetX=0;
		offsetY=0;
	}

	public int modifyX(int dx)
	{
		return offsetX+=dx;
	}
	public int modifyY(int dy)
	{
		return offsetY+=dy;
	}

	public void modify(int dx, int dy)
	{
		offsetX+=dx;
		offsetY+=dy;
	}

	public void setX(int offsetX)
	{
		this.offsetX = offsetX;
	}

	public void setY(int offsetY)
	{
		this.offsetY = offsetY;
	}
	public void set(int offsetX, int offsetY)
	{
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public void clear()
	{
		offsetX = 0;
		offsetY = 0;
	}

	public int getX()
	{
		return offsetX;
	}

	public int getY()
	{
		return offsetY;
	}
}