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
	private final double discount = .9;
	private double reward = -1;
	private double pOfSucces = .8;
	private double pOfFail = .2;
	private World world;
	private List<State> states;
	private List<StateAction> policy;

	public ValueIteration(World world) {
		this.world = world;
		states = new ArrayList<>();
		generateS();
	}

	/**
	 * The function that will calculate all the utilities for the each block
	 * @return {@link StateAction} that contains the current state and the best action.
	 */
	public StateAction calculateUtilities(){
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
		return null;
	}

	//a lot of calculations and calls needed for this, so separated into new method
	public double maxUtilAction(State s){

		return 0;
	}

	public void generateS(){
		for(Tile[] tiles : world.theWorld){
			for(Tile tile : tiles){
				if(tile.type == Tile.TileType.WALL || tile.type == Tile.TileType.FINISH){
					continue;
				}
				for(int i = -5; i < 6; i++) { //iterate through the possible xVel
					for(int j = -5; j < 6; j++) { //possible yVel
						states.add(new State(tile, new Velocity(i, j))); //this means that we have every possible state
					}
				}
			}//inner for
		}//outer for
	}
}
