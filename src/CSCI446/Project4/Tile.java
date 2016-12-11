package CSCI446.Project4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thechucklingatom on 12/7/16.
 * @author Robert Putnam
 */
public class Tile {

	private World world;
	private double reward = 0;
	private double currentUtility = 0;
	private double previousUtility = 0;
	private int xLocation, yLocation;
	List<Action> actions;

	public enum TileType{
		WALL,
		SAFE,
		START,
		FINISH
	}

	TileType type;

	public Tile(World w,TileType type, int x, int y){
		this.world = w;
		this.type = type;
		xLocation = x;
		yLocation = y;
		if(type == TileType.WALL){
			reward = -1;
		} else if(type == TileType.FINISH) {
			reward = 255;
		} else {
			reward = world.getReward(this);
		}
		fillActions();
	}

	public static double distanceTo(Tile s, Tile t) {
		double dX = Math.pow(t.xLocation - s.xLocation, 2.0);
		double dY = Math.pow(t.yLocation - s.yLocation, 2.0);
		return Math.pow(dX + dY, 0.5);
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

	public int getxLocation() {
		return xLocation;
	}

	public int getyLocation() {
		return yLocation;
	}
}
