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
	private double maxChange = .5;
	private final double discount = .9;
	private double reward = -.04;
	private World world;
	private List<State> states;

	public ValueIteration(World world) {
		this.world = world;
		states = new ArrayList<State>();
	}

	public StateAction calculateUtilities(){
		//this is the main iterator that will terminate when the largest change of
		//utility is below a threshold determined by the discount and epsilon
		while(!(maxChange < epsilon * (1 - discount) / discount)){
			maxChange = 0;
			for(State s : states){
				double newUtility = reward + Math.max(;
				)
			}

		}//while
		return null;
	}

	public void generateS(){
		for(Tile[] tiles : world.theWorld){
			for(Tile tile : tiles){
				if(tile.type == Tile.TileType.WALL){
					continue;
				}
				List<Action> actions = tile.actions;
				double currentReward = tile.getReward();
				double currentUtility = tile.getCurrentUtility();
				if(currentUtility == 0){
					currentUtility = tile.getReward();
				}

				for(Action action : actions){

				}

			}//inner for
		}//outer for
	}
}
