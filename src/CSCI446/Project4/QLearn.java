package CSCI446.Project4;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mathew Gostnell on 12/10/2016.
 *
 * @author Mathew Gostnell
 */
public class QLearn {

    private QLearn instance = null;         // unique instance for Singleton pattern
    private Map<StateAction, Integer> n;    // table mapping StateAction frequencies
    private Map<StateAction, Double> q;     // table mapping StateAction to utility values

    private World w;            // used to initialize q with all state-action pairs
    private Action a = null;    // last action taken
    private State p = null;     // the previous state visited
    private double r = 0.f;     // the reward received in state p
    private double alpha = 1.f; // the diminishing alpha to reduce training over time

    private QLearn(State s) {
        a = new Action(Action.DIRECTION.STOP);
        p = s;
    }

    /**
     * Generate a Mapping of StateAction to utility
     * @param w the World object we iterate over to generate new states
     */
    private void initializeQ(World w) {
        for (Tile[] row : w.theWorld) {
            for (Tile t : row) {
                // for each action we have, north, east, south, west, stop,
                // calculate the utility as the max utility across all actions
                // Q(i) = max_a Q(a, i)
                for (int i = -1; i <= 1; i++) { // for each x velocity ...
                    for (int j = -1; j <= 1; i++) { // for each y velocity ...
                        State s = new State(t, new Velocity(i, j));
                        double maxUtility = Double.MIN_VALUE;

                        for (int k = 0; k < Action.DIRECTION.values().length; k++) {
                            Action a = new Action(k); // the action in a direction or stop
                            State pos = new State(w.pseudoMove(a), w.curVel);
                            StateAction sa = new StateAction(pos, a);

                            double utility = pos.getTile().getReward() - s.getTile().getReward();
                            q.put(sa, utility);
                        }
                    }
                }
            }
        }
    }

    /**
     * Return unique instance of QLearn
     *
     * @param s the starting state of this agent
     * @return unique instance of QLearn
     */
    public QLearn getInstance(World w, State s) {
        if (instance == null) {
            instance = new QLearn(s);
        }
        return instance;
    }


    /**
     * Calculates the action we should take given our QLearning process
     *
     * @param e the event our agent just transitioned into
     * @param j the action our agent just took to transition into State e
     * @return the next Action our agent should take given our policy
     */
    public Action QLearnAgent(State e) {
        /**
         * Q[a, p]  := the value of performing Action a in State p
         * alpha    := a double between 0 and 1 inclusive that is the learning rate
         *
         function Q-LEARNiNG-AGENT(e) returns an action
            static: 	Q, a table of action values
                        N, a table of state-action frequencies
                        a, the last action taken
                        p, the previous state visited
                        r, the reward received in state p
         j ← STATE[e]
         if i is non-null then
            N[a,p] ← N[a,p] + 1
            Q[a,p] ← Q[a,p] + alpha(r + maxa’ Q[a’,j] - Q[a,p])
         if TERMINAL?[e] then
            p ← null
         else
            p ← j
            r ← REWARD[e]
         a ← arg maxa’, f(Q[a',j], N[a',j])
         return a
         */
        if (p != null) {
            // N[p, a] <- N[p, a] + 1
            StateAction sa = new StateAction(p, a);
            if (n.containsKey(sa)) {
                // update the existing state-action frequency
                n.put(sa, n.get(sa) + 1);
            } else {
                // insert new frequency count into our table
                n.put(new StateAction(e, a), 1);
            }

            // Q[i, a] = Q[i, a] + alpha(r + max a' (Q[a', j] - Q[a', p]))
        }
        if (e.getTile().type.equals(Tile.TileType.FINISH)) {
            p = null;
        } else {

        }
        return null;

    }

}