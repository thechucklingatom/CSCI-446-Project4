package CSCI446.Project4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by thechucklingatom on 12/7/16.
 */
public class World {
	boolean crashToStart = false;
	private Random rng = new Random();
	private int tmpX, tmpY;
	private int startX, startY;
	private Tile startTile;
	private int xLocation, yLocation;
	public Tile[][] theWorld;
	public List<Tile> finishTiles = new ArrayList<>();
	public List<Tile> safeTiles = new ArrayList<>();
	public List<Tile> startTiles = new ArrayList<>();
	public Velocity curVel = new Velocity(0, 0);

	public World() {

	}

	public World(Tile[][] theWorld) {
		this.theWorld = theWorld;
		for (Tile[] t1 : theWorld) {
			for (Tile t2 : t1) {
				if (t2.type == Tile.TileType.FINISH) {
					finishTiles.add(t2);
				} else if (t2.type == Tile.TileType.SAFE) {
					safeTiles.add(t2);
				} else if (t2.type == Tile.TileType.START) {
					startTiles.add(t2);
				}
			}
		}

		for (Tile[] t1 : theWorld) {
			for (Tile t2 : t1) {
				if (t2.type == Tile.TileType.WALL) {
					t2.setReward(-1);
				} else if (t2.type == Tile.TileType.FINISH) {
					t2.setReward(255);
				} else {
					setReward(t2);
				}
			}
		}

		startTile = startTiles.get(rng.nextInt(startTiles.size()));
		startX = startTile.getxLocation();
		startY = startTile.getyLocation();
	}

	public void setReward(Tile s) {
		List<Tile> checked = new ArrayList<>();
		s.setReward(255 - findPath(s.getxLocation(), s.getyLocation(), checked));
	}

	public int findPath(int x, int y, List<Tile> checked) {
		if (x < 0 || y < 0 || x >= theWorld[0].length || y >= theWorld.length) {
			return Integer.MAX_VALUE - 2; // make sure we are still in the world
		} else if (checked.contains(theWorld[y][x])) {
			return Integer.MAX_VALUE - 2; // make sure we haven't been here before
		} else if (theWorld[y][x].type == Tile.TileType.WALL) {
			return Integer.MAX_VALUE - 2; // make sure we can travel through this Tile
		} else if (theWorld[y][x].type == Tile.TileType.FINISH) {
			return 0; // we found our base case, begin returning
		} else {
			checked.add(theWorld[y][x]);
			int south = Math.abs(findPath(x, y + 1, checked) + 1);
			int north = Math.abs(findPath(x, y - 1, checked) + 1);
			int east = Math.abs(findPath(x + 1, y, checked) + 1);
			int west = Math.abs(findPath(x - 1, y, checked) + 1);

			return Math.min(Math.min(north, south), Math.min(east, west));
		}
	}

	public void updateTiles(Tile[][] theWorld) {
		this.theWorld = theWorld;
	}

	public Tile move(Action a) {
		double prob = rng.nextDouble();
		if (prob <= 0.8) {
			switch (a.getActionInt()) {
				case 0: // we are moving north
					curVel.setyVelocity(-1.f);
					break;
				case 1: //we are moving
					curVel.setxVelocity(1.f);
					curVel.setyVelocity(-1.f);
					break;
				case 2: // we are moving east
					curVel.setxVelocity(1.f);
					break;
				case 3:
					curVel.setxVelocity(1.f);
					curVel.setyVelocity(1.f);
					break;
				case 4:
					curVel.setyVelocity(1.f);
					break;
				case 5:
					curVel.setxVelocity(-1.f);
					curVel.setyVelocity(1.f);
					break;
				case 6:
					curVel.setxVelocity(-1.f);
					break;
				case 7:
					curVel.setxVelocity(-1.f);
					curVel.setyVelocity(-1.f);
					break;
				default:
					break; // don't modify velocity on default
			}
		} else { // acceleration not applied randomly }
		}

		yLocation += (int) curVel.getyVelocity();
		xLocation += (int) curVel.getxVelocity();
		Tile dest = theWorld[xLocation][yLocation];

		if (dest.type == Tile.TileType.WALL && !crashToStart) {
			dest = closestTile(dest);
			xLocation = dest.getxLocation();
			yLocation = dest.getyLocation();
			curVel.reset();
		} else if (dest.type == Tile.TileType.WALL) {
			xLocation = startX;
			yLocation = startY;
			curVel.reset();
		}
		return theWorld[xLocation][yLocation];
	}

	public Tile closestTile(Tile dest) {
		Tile closestT = dest;
		double minDist = Double.MAX_VALUE;

		for (Tile t : safeTiles) {
			if (Tile.distanceTo(dest, t) < minDist) {
				closestT = t;
				minDist = Tile.distanceTo(dest, t);
			}
		}
		return closestT;
	}

	public Tile pseudoMove(Action a) {
		// performs a normal move with output without actually moving the agent
		double prob = rng.nextDouble();
		if (prob <= 0.8) {
			switch (a.getActionInt()) {
				case 0: // we are moving north
					curVel.setyVelocity(-1.f);
					break;
				case 1: //we are moving
					curVel.setxVelocity(1.f);
					curVel.setyVelocity(-1.f);
					break;
				case 2: // we are moving east
					curVel.setxVelocity(1.f);
					break;
				case 3:
					curVel.setxVelocity(1.f);
					curVel.setyVelocity(1.f);
					break;
				case 4:
					curVel.setyVelocity(1.f);
					break;
				case 5:
					curVel.setxVelocity(-1.f);
					curVel.setyVelocity(1.f);
					break;
				case 6:
					curVel.setxVelocity(-1.f);
					break;
				case 7:
					curVel.setxVelocity(-1.f);
					curVel.setyVelocity(-1.f);
					break;
				default:
					break;
			}
		} else { // acceleration not applied randomly }
		}
		int tempY = yLocation + (int) curVel.getyVelocity();
		int tempX = xLocation + (int) curVel.getxVelocity();
		Tile dest = theWorld[tempX][tempY];

		if (dest.type == Tile.TileType.WALL) {
			dest = closestTile(dest);
			tempX = dest.getxLocation();
			tempY = dest.getyLocation();
		}

		return theWorld[tempX][tempY];
	}

	public Tile currentTile() {
		return theWorld[xLocation][yLocation];
	}

	public List<Tile> safeTiles() {
		return safeTiles;
	}

	public double getReward(Tile s) {
		double minDist = Double.MAX_VALUE;
		for (Tile sPrime : finishTiles) {
			if (Tile.distanceTo(s, sPrime) < minDist) {
				minDist = Tile.distanceTo(s, sPrime);
			}
		}
		return minDist;
	}

	public Tile getTileAtLocation(int x, int y){
		return theWorld[y][x];
	}

	public List<Tile> getTileNeighbors(Tile tile) {
		List<Tile> toReturn = new ArrayList<>();
		int tempX = tile.getxLocation();
		int tempY = tile.getyLocation();
		//top left
		if (tempX != 0 && tempY != 0) {
			toReturn.add(theWorld[tempY - 1][tempX - 1]);
		}
		//above
		if (tempY != 0) {
			toReturn.add(theWorld[tempY - 1][tempX]);
		}
		//top right
		if (tempY != 0 && tempX != theWorld[0].length - 1) {
			toReturn.add(theWorld[tempY - 1][tempX + 1]);
		}
		//right
		if (tempX != theWorld[0].length - 1) {
			toReturn.add(theWorld[tempY][tempX + 1]);
		}
		//bottom right
		if (tempY != theWorld.length - 1 && tempX != theWorld[0].length - 1) {
			toReturn.add(theWorld[tempY + 1][tempX + 1]);
		}
		//bottom
		if (tempY != theWorld.length - 1) {
			toReturn.add(theWorld[tempY + 1][tempX]);
		}
		//bottom left
		if (tempY != theWorld.length - 1 && tempX != 0) {
			toReturn.add(theWorld[tempY + 1][tempX - 1]);
		}
		//left
		if (tempX != 0) {
			toReturn.add(theWorld[tempY][tempX - 1]);
		}

		return toReturn;
	}
}
