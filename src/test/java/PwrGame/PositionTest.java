package PwrGame;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PositionTest
{
	Random r = new Random();

	@Test
	public void modifyX()
	{
		int x0=r.nextInt(Integer.MAX_VALUE/2), x=r.nextInt(Integer.MAX_VALUE/2);
		Position position = new Position(x0,r.nextInt());
		position.modifyX(x);
		assertTrue(position.getX()==x0+x);
	}

	@Test
	public void modifyY()
	{
		int y0=r.nextInt(Integer.MAX_VALUE/2), y=r.nextInt(Integer.MAX_VALUE/2);
		Position position = new Position(r.nextInt(),y0);
		position.modifyY(y);
		assertTrue(position.getY()==y0+y);
	}

	@Test
	public void modify()
	{
		int x0=r.nextInt(Integer.MAX_VALUE/2), x=r.nextInt(Integer.MAX_VALUE/2),
				y0=r.nextInt(Integer.MAX_VALUE/2), y=r.nextInt(Integer.MAX_VALUE/2);
		Position position = new Position(x0,y0);
		position.modify(x, y);
		assertTrue(position.getY()==y0+y);
		assertTrue(position.getX()==x0+x);
	}

	@Test
	public void setX()
	{
		int x=r.nextInt();
		Position position = new Position(r.nextInt(),r.nextInt());
		position.setX(x);
		assertTrue(position.getX()==x);
	}

	@Test
	public void setY()
	{
		int y=r.nextInt();
		Position position = new Position(r.nextInt(),r.nextInt());
		position.setY(y);
		assertTrue(position.getY()==y);
	}

	@Test
	public void set()
	{
		int x=r.nextInt(), y=r.nextInt();
		Position position = new Position(x,y);
		Position position2  = new Position(r.nextInt(),r.nextInt());
		position2.set(x,y);

		assertTrue(position.equals(position2));
	}

	@Test
	public void clear()
	{
		int x=r.nextInt(), y=r.nextInt();
		Position position = new Position(x,y);
		position.clear();
		assertTrue(position.getX()==0&&position.getY()==0);
	}

	@Test
	public void getX()
	{
		int x=r.nextInt(), y=r.nextInt();
		Position position = new Position(x,y);
		assertTrue(position.getX()==x);
	}

	@Test
	public void getY()
	{
		int x=r.nextInt(), y=r.nextInt();
		Position position = new Position(x,y);
		assertTrue(position.getY()==y);
	}

	@Test
	public void getCopy()
	{

		int x=r.nextInt(), y=r.nextInt();

		Position position = new Position(x,y);
//		assertTrue(position == position.getCopy()); //should fail, getpos creates copy
		assertTrue(position.equals(position.getCopy())); //should work
	}

	@Test
	public void equals()
	{
		Position a,b,c;
		a = new Position(99,11);
		b = new Position(99,11);
		c = a;
		assertTrue(a.equals(b)&&b.equals(c)&&a.equals(c));
	}
}