package CSCI446.Project4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thechucklingatom on 12/7/16.
 * @author Robert Putnam
 */
public class Tile {

	private double reward = 0;
	private double currentUtility = 0;
	private double previousUtility = 0;
	List<Action> actions;

	public enum TileType{
		WALL,
		SAFE,
		START,
		FINISH
	}

	TileType type;

	public Tile(TileType type){
		this.type = type;
		if(type == TileType.WALL){
			reward = -1;
		}else{
			reward = 1;
		}
		fillActions();
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
	private void fillActions(){
		actions = new ArrayList<Action>();
		if(type == TileType.SAFE || type == TileType.START || type == TileType.FINISH){
			actions.add(new Action(Action.DIRECTION.NORTH));
			actions.add(new Action(Action.DIRECTION.NORTHEAST));
			actions.add(new Action(Action.DIRECTION.EAST));
			actions.add(new Action(Action.DIRECTION.SOUTHEAST));
			actions.add(new Action(Action.DIRECTION.SOUTH));
			actions.add(new Action(Action.DIRECTION.SOUTHWEST));
			actions.add(new Action(Action.DIRECTION.WEST));
			actions.add(new Action(Action.DIRECTION.NORTHWEST));
		}
	}
}
