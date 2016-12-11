package CSCI446.Project4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by thechucklingatom on 12/7/16.
 */
public class World {
	private int xLocation, yLocation;
	public Tile[][] theWorld;

	public World(Tile[][] theWorld){
		this.theWorld = theWorld;
	}

	public Tile currentTile(){
		return theWorld[xLocation][yLocation];
	}

	public List<Tile> safeTiles(){
		List<Tile> worldList = new ArrayList<>();
		for(Tile[] tiles : theWorld){
			worldList.addAll(Arrays.asList(tiles));
		}

		return worldList
				.stream()
				.filter(e -> e.type != Tile.TileType.WALL)
				.collect(Collectors.toList());
	}

	public List<Tile> getTileNeighbors(Tile tile){
		List<Tile> toReturn = new ArrayList<>();
		int tempX = tile.getxLocation();
		int tempY = tile.getyLocation();
		//top left
		if(tempX != 0 && tempY != 0){
			toReturn.add(theWorld[tempY - 1][tempX - 1]);
		}
		//above
		if(tempY != 0){
			toReturn.add(theWorld[tempY - 1][tempX]);
		}
		//top right
		if(tempY != 0 && tempX != theWorld[0].length - 1){
			toReturn.add(theWorld[tempY - 1][tempX + 1]);
		}
		//right
		if(tempX != theWorld[0].length - 1){
			toReturn.add(theWorld[tempY][tempX + 1]);
		}
		//bottom right
		if(tempY != theWorld.length  - 1 && tempX != theWorld[0].length - 1){
			toReturn.add(theWorld[tempY + 1][tempX + 1]);
		}
		//bottom
		if(tempY != theWorld.length - 1){
			toReturn.add(theWorld[tempY + 1][tempX]);
		}
		//bottom left
		if(tempY != theWorld.length - 1 && tempX != 0){
			toReturn.add(theWorld[tempY + 1][tempX - 1]);
		}
		//left
		if(tempX != 0){
			toReturn.add(theWorld[tempY][tempX - 1]);
		}

		return toReturn;
	}
}
