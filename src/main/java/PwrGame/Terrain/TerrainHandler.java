package PwrGame.Terrain;

import PwrGame.Position;

import java.util.Vector;

public class TerrainHandler
{
	public static Vector<Tile> limitViewSquare(Vector<Tile> tiles, Position position, int distance)
	{
		Vector<Tile> vect = (Vector) tiles.clone();
		vect.removeIf(
				tile -> {
					Position pos = tile.getPosition();
					return (Math.abs(pos.getX()-position.getX())>distance)||
							(Math.abs(pos.getY()-position.getY())>distance);
				}
		);
//		vect.trimToSize();
		return vect;
	}
}
