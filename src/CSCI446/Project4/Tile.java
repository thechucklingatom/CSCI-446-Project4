package CSCI446.Project4;

/**
 * Created by thechucklingatom on 12/7/16.
 * @author Robert Putnam
 */
public class Tile {

	private double reward = 0;
	private double currentUtility = 0;
	private double previousUtility = 0;

	public enum TileType{
		WALL,
		SAFE,
		START,
		FINISH
	}

	TileType type;

	public Tile(TileType type){
		this.type = type;
	}
}
