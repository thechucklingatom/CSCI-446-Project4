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

	@Override
	public String toString() {
		return "Tile:\n\tType:" + this.type.toString() + "\n\tReward:" + reward + "\n\tcurUtil:" + currentUtility + "\n\tprvUtil:" + previousUtility;
	}

	public void setUtility(double currentUtility) {
		previousUtility = currentUtility;
		this.currentUtility = currentUtility;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	public double getReward() { return reward; }
	public double getCurrentUtility() { return currentUtility; }
	public double getPreviousUtility() { return previousUtility;}
}
