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

	public Position modifyX(int dx)
	{
		posX+=dx;
		return new Position(this);
	}
	public Position modifyY(int dy)
	{
		posY+=dy;
		return new Position(this);
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

	public boolean equalsLeft(Position pos)
    {
        return ((pos.getY()==posY)&&(pos.getX()-40==posX));
    }
    public boolean equalsRight(Position pos)
    {
        return ((pos.getY()==posY)&&(pos.getX()+40==posX));
    }
    public boolean equalsUp(Position pos)
    {
        return ((pos.getY()-40==posY)&&(pos.getX()==posX));
    }
    public boolean equalsDown(Position pos)
    {
        return ((pos.getY()+40==posY)&&(pos.getX()==posX));
    }

	@Override
	public boolean equals(Object obj)
	{
		if (! (obj instanceof Position ))
			return false;
		return ((((Position) obj).getX()==posX)&&(((Position) obj).getY()==posY));
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posX;
		result = prime * result + posY;
		return result;
	}
	public int distanceTo(Position pos)
	{
		return (int) Math.sqrt(Math.pow(Math.abs(pos.getY()-posY), 2)+Math.pow(Math.abs(pos.getX()-posX), 2));
	}
}