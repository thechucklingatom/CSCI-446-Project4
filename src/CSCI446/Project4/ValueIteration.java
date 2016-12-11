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

	private double epsilon;
	private double maxChange = 0;
	private final double discount = .6;
	private double reward = -1;
	private double pOfSucces = 1;
	private double pOfFail = 0;
	private World world;
	private List<State> states;
	private List<Double> utilities;
	private List<StateAction> policy;
	private List<Tile> walls;

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
				if(newUtility - oldUtility > maxChange){
					maxChange = newUtility - oldUtility;
				}
			}
		}//while
	}

	//a lot of calculations and calls needed for this, so separated into new method
	public double maxUtilAction(State s){

		for(int i = -1; i < 2; i++){ //iterate through the possible actions
			for(int j = -1; j < 2; j++){
				//find the tile that corresponds to that stateaction pair

			}
		}
		return 0;
	}

	public void generateS(){
		for(Tile[] tiles : world.theWorld){
			for(Tile tile : tiles){
				if(tile.type == Tile.TileType.WALL || tile.type == Tile.TileType.FINISH){
					if(tile.type == Tile.TileType.WALL){
						walls.add(tile);
					}
					continue;
				}
				for(int i = -5; i < 6; i++) { //iterate through the possible xVel
					for(int j = -5; j < 6; j++) { //possible yVel
						states.add(new State(tile, new Velocity(i, j))); //this means that we have every possible state
						utilities.add(0.0);
					}
				}
			}//inner for
		}//outer for
	}
}
