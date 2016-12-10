package CSCI446.Project4;

/**
 * Created by thechucklingatom on 12/10/2016.
 */
public class ValueIteration {

	/*
	Value_Iteration(S,A,P,R,THETA)
		INPUTS
			S is the set of all states
			A is the set of all actions
			P is the state transition function specifying P(s'|s,a)
			THETA is a threshold, THETA>0
		OUTPUT
			pi[S] approximately optimal policy
			V[S] value function
		LOCAL
			real array V_k[S] is a sequence of value functions
			action array pi[S]
		assign V_0[S] arbitrarily
		k <- 0
		repeat
			k <- k+1
			for each state s do
				V_k[s] = max_a sum_s, P(s'|s,a)(R(s,a,s') + Y^V_{k-1}[s'])
		until for all |V_k[s] - V_{k-1}[s]|<0
		for each state s do
			pi[s] = argmax_a sum_s, P(s'|s,a)(R(s,a,s') + Y^V_{k-1}[s'])
		return pi, V_k

	*/

	private double epsilon;


}
