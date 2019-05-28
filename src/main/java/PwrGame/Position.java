package PwrGame;

public class Position
{
	private int posX, posY;

	public Position(int x, int y)
	{
		posX=x;
		posY=y;
	}
	public Position()
	{
		posX=0;
		posY=0;
	}
	public Position(Position pos)
	{
		posX=pos.getX();
		posY=pos.getY();
	}

	public int modifyX(int dx)
	{
		return posX+=dx;
	}
	public int modifyY(int dy)
	{
		return posY+=dy;
	}

	public Position modify(int dx, int dy)
	{
		posX+=dx;
		posY+=dy;
		return new Position(this);
	}

	public void setX(int posX)
	{
		this.posX = posX;
	}

	public void setY(int posY)
	{
		this.posY = posY;
	}
	public void set(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
	}


	public void clear()
	{
		posX = 0;
		posY = 0;
	}

	public int getX()
	{
		return posX;
	}

	public int getY()
	{
		return posY;
	}

	public Position getCopy()
	{
		return new Position(this);
	}

	public boolean equals(Position pos)
	{
		return ((pos.getX()==posX)&&(pos.getY()==posY));
	}

	@Override
	public boolean equals(Object obj)
	{
		if (! (obj instanceof Position ))
			return false;
		return (equals((Position) obj));
	}
}