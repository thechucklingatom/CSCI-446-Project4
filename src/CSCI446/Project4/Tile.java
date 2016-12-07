package CSCI446.Project4;

/**
 * Created by thechucklingatom on 12/7/16.
 * @author Robert Putnam
 */
public class Tile {
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
