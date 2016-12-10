package CSCI446.Project4;

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
}
