package CSCI446.Project4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robert Putnam
 */
public class ValueIteration {

	/*
	function VALUE-ITERATION(mdp, e) returns a utility function
		Inputs: mdp, a Markov Decision Process with states S, actions A(s), transition model
			P(s’ | s,a), rewards R(s), and discount y
			e, the maximum error allowed in the utility of any state
		Local Variables: U, U’, vectors of utilities for states in S, initially zero
			delta , the maximum change in the utility of any state in an iteration

	repeat
		U←U’ ; delta←0
		for each state s in S do
			U’[s]←R(s) + y argmax(P(s’ | s,a)U[s’])
			if U’[s] - U[s] > delta then delta ←U’[s] - U[s]
	until delta < e(1 - y)/y
	return U

	*/

	private double epsilon = .01;
	private double maxChange = 0;
	private final double discount = .6;
	private double reward = -1;
	private double pOfSucces = 1;
	private double pOfFail = 0;
	private World world;
	private List<State> states;
	private List<Double> utilities;
	private List<Action> maxActions;
	private List<StateAction> policy;
	private List<Tile> walls;
	private int curIndex;

	public ValueIteration(World world) {
		this.world = world;
		states = new ArrayList<>();
		generateS();
	}

	//find the probability that an action is not applied
	public void findP(){
		int numFail = 0;
		int numTotal = 0;
		Tile curTile = world.currentTile();
		for(int i = 0; i < 1000; i++) {
			numTotal++;
			Tile tempTile = world.pseudoMove(new Action(1));
			if (curTile == tempTile) {
				numFail++;
			}
		}
		pOfFail = numFail / numTotal;
		pOfSucces = (numTotal - numFail) / numTotal;
	}

	//The function that will calculate all the utilities for the each block
	public void calculateUtilities(){
		//this is the main iterator that will terminate when the largest change of
		//utility is below a threshold determined by the discount and epsilon
		while(!(maxChange < epsilon * (1 - discount) / discount)){
			maxChange = 0;
			double oldUtility = 0;
			for(State s : states){
				double newUtility = reward + (discount * maxUtilAction(s));
				utilities.set(curIndex, newUtility);
				if(newUtility - oldUtility > maxChange){
					maxChange = newUtility - oldUtility;
				}
			}
		}//while
	}

	//a lot of calculations and calls needed for this, so separated into new method
	public double maxUtilAction(State s){
		double failU;
		double maxUtil;
		Action maxA = createAction(0,0);
		Velocity curVel = s.getVelocity();
		Tile curTile = s.getTile();
		int curX = curTile.getxLocation();
		int curY = curTile.getyLocation();
		double curVelX = curVel.getxVelocity();
		double curVelY = curVel.getyVelocity();
		//calculate the utility if no action is applied (failure)
		int curStateInd = findState(curX, curY, curVelX, curVelY);
		int nxtStateInd = findState(curX + curVelX, curY + curVelY, curVelX, curVelY);
		curIndex = curStateInd;
		failU = utilities.get(nxtStateInd);
		maxUtil = failU;
		//*THIS IS FOR CRASH SCENARIO NEAREST TILE*/
		for(int i = -1; i < 2; i++){ //iterate through the possible actions
			for(int j = -1; j < 2; j++){
				if(i == 0 && j == 0){continue;}
				//find the tile that corresponds to that stateaction pair
				double sucU, totalU;
				Action curA = createAction(i, j);
				double tempVelX = curVelX + i;
				double tempVelY = curVelY + j;
				nxtStateInd = findState(curX + tempVelX, curY + tempVelY, curVelX + i, curVelY + j);
				sucU = utilities.get(nxtStateInd);
				totalU = (pOfSucces*sucU) + (pOfFail*failU);
				if(totalU > maxUtil){
					maxUtil = totalU;
					maxA = curA;
				}
			}
		} //at this point, we have the utilities of all possible actions
		maxActions.set(curStateInd, maxA);
		return maxUtil;
	}

	//returns index of wanted state values in states/utilities
	//the inputs are the TARGET STATE!
	public int findState(double posX, double posY, double velX, double velY){
		Tile tarTile = world.getTileAtLocation((int)posX, (int)posY);
		/*THIS ONLY APPLIES TO CRASH SCENARIO !*/
		//must check to see if we would've landed on a wall
		if(tarTile.type == Tile.TileType.WALL){
			tarTile = world.closestTile(tarTile);
			velX = 0;
			velY = 0;
		}

		//iterate through states until we find the state with inputed values
		for(int i = 0; i < states.size(); i++){
			State s = states.get(i);
			Velocity curVel = s.getVelocity();
			double curVelX = curVel.getxVelocity();
			double curVelY = curVel.getyVelocity();
			Tile curTile = s.getTile();
			//now compare
			if(tarTile == curTile && curVelX == velX && curVelY == velY){
				return i;
			}
		} //if we reach here that means we're looking at a finish line
		return -1;
	}

	public void generateS(){
		for(Tile[] tiles : world.theWorld){
			for(Tile tile : tiles){
				if(tile.type == Tile.TileType.WALL){
					continue;
				}
				for(int i = -5; i < 6; i++) { //iterate through the possible xVel
					for(int j = -5; j < 6; j++) { //possible yVel
						states.add(new State(tile, new Velocity(i, j))); //this means that we have every possible state
						//create placeholders in our lists so that we can index to them later
						utilities.add(0.0);
						maxActions.add(new Action(0));
					}
				}
			}//inner for
		}//outer for
	}

	public void findPolicy(){
		for(int i = 0; i < states.size(); i++){
			policy.add(new StateAction(states.get(i), maxActions.get(i)));
		}
	}

	public Action createAction(int inX, int inY){
		Action action;
		switch(inX){
			case -1:
				if(inY == -1){
					action = new Action(5);
				} else if(inY == 0){
					action = new Action(6);
				} else{
					action = new Action(7);
				}
				break;
			case 0:
				if(inY == -1){
					action = new Action(4);
				} else if(inY == 0){
					action = new Action(8);
				} else{
					action = new Action(0);
				}
				break;
			case 1:
				if(inY == -1){
					action = new Action(3);
				} else if(inY == 0){
					action = new Action(2);
				} else{
					action = new Action(1);
				}
				break;
			default: //we should never hit here
				action = new Action(-1);
		}
		return action;
	}
}
