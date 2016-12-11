package CSCI446.Project4;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Mathew Gostnell on 12/10/2016.
 *
 * @author Mathew Gostnell
 * @version v1.2112016
 */
public class QLearn {

    private Random rng = new Random();      // used for explorer function to chose random action
    private static QLearn instance = null;  // unique instance for Singleton pattern
    private Map<StateAction, Integer> n;    // table mapping StateAction frequencies
    private Map<StateAction, Double> q;     // table mapping StateAction to utility values

    private World w;            // used to initialize q with all state-action pairs
    private Action a = null;    // last action taken
    private State p = null;     // the previous state visited
    private double r = 0.f;     // the reward received in state p
    private double alpha = 1.f; // the diminishing alpha to reduce training over time

    private QLearn(State s, World w) {
        this.w = w;
        a = new Action(8);  // default start in the stop action
        p = s;                 // previous state was the start state
        n = new HashMap<>();   // instantiate frequency table
        q = new HashMap<>();   // instantiate StateAction utility value table

        initializeQ(w); // initialize the values for utilities based on the difference of rewards
    }

    /**
     * Generate a Mapping of StateAction to utility
     *
     * @param w the World object we iterate over to generate new states
     */
    private void initializeQ(World w) {
        for (Tile[] row : w.theWorld) {
            for (Tile t : row) {
                for (int i = -5; i <= 5; i++) { // for each x velocity ...
                    for (int j = -5; j <= 5; j++) { // for each y velocity ...
                        State s = new State(t, new Velocity(i, j)); // create a state for this tile and velocity
                        for (int k = 0; k < Action.DIRECTION.values().length; k++) {
                            Action a = new Action(k); // the action in a direction or stop
                            StateAction sa = new StateAction(s, a);
                            q.put(sa, s.getTile().getReward());
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
    public static QLearn getInstance(World w, State s) {
        if (instance == null) {
            instance = new QLearn(s, w);
        }
        return instance;
    }

    public void runQLearn() {
        int numActions = 0;
        State starting = p; // start at previous location or the start in this case
        Action a = null;    // obtain new actions based on QLearnAgent(State s);
        do {
            a = QLearnAgent(starting);
            starting = new State(w.move(a), w.curVel);
            numActions++;
        } while (a != null);
        System.out.println("QLearn finished the race after:\n\t" + numActions + " actions!");
    }

    /**
     * Calculates the action we should take given our QLearning process
     *
     * @param e the event our agent just transitioned into
     * @return the next Action our agent should take given our policy
     */
    public Action QLearnAgent(State e) {
        /**
         * Q[a, p]  := the value of performing Action a in State p
         * alpha    := a double between 0 and 1 inclusive that is the learning rate
         *
         function Q-LEARNiNG-AGENT(e) returns an action
         static:
         * [Q] := a table of action values
         * [N] := a table of state-action frequencies
         * [a] := the last action taken
         * [p] := the previous state visited
         * [r] := the reward received in state p

         j ← STATE[e]
         if p is non-null then
         |  N[a,p] ← N[a,p] + 1
         |  Q[a,p] ← Q[a,p] + alpha(r + maxa’ Q[a’,j] - Q[a,p])
         if TERMINAL?[e] then
         |  p ← null
         else
         |  p ← j
         |  r ← REWARD[e]
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
            if (q.containsKey(sa)) {
                sa = new StateAction(e, a); // this state and our last action
                double newUtil = q.get(sa) + alpha * (r + maxUtility(e));
                q.put(sa, newUtil);
            } else {
                q.put(sa, 0.0);
            }

            // Q[i, a] = Q[i, a] + alpha(r + max a' (Q[a', j] - Q[a', p]))
        }
        if (e.getTile().type.equals(Tile.TileType.FINISH)) {
            p = null;
        } else {
            p = e;
            r = p.getTile().getReward();
        }
        alpha -= 0.001f; // slowly decrease to assist in convergence
        a = explorerFunction(new StateAction(e, maxActionUtility(e)));
        return null;
    }

    private Action explorerFunction(StateAction sa) {
        int STATE_OCCURRENCE = 5;
        if (n.containsKey(sa) && n.get(sa) < STATE_OCCURRENCE) {
            return sa.getAction();
        } else {
            return new Action(rng.nextInt(9));
        }
    }

    private Action maxActionUtility(State e) {
        double maxUtil = Double.MIN_VALUE;
        Action rtnActn = null;

        for (int i = 0; i < 9; i++) {
            StateAction sa = new StateAction(e, new Action(i));
            double q1, q2; // Q[a', j], Q[a', p]
            if (q.containsKey(sa)) {
                q1 = q.get(sa);
            } else { // assume no utility if not found or initialized
                q1 = 0.f;
                q.put(sa, q1);
            }
            sa = new StateAction(p, new Action(i));
            if (q.containsKey(sa)) {
                q2 = q.get(sa);
            } else {
                q2 = 0.f;
                q.put(sa, q2);
            }

            double util = q1 - q2;
            if (util > maxUtil) {
                maxUtil = util;
                rtnActn = sa.getAction();
            }
        }
        return rtnActn;
    } // end maxActionUtility()

    /**
     * Given a state, calculate the largest utility for that state regardless of action
     *
     * @param e State we are calculating utility for
     * @return double maxUtility
     */
    private double maxUtility(State e) {
        double maxUtil = Double.MIN_VALUE;
        for (int i = 0; i < 9; i++) { // for each action, find the largest increase in utility
            StateAction sa = new StateAction(e, new Action(i)); // create new StateAction
            double q1, q2; // Q[a', j], Q[a', p]                // declare utility variables
            if (q.containsKey(sa)) { // if we can access the utility of this StateAction, do so
                q1 = q.get(sa);
            } else { // assume no utility if not found or initialized
                q1 = 0.f;
                q.put(sa, q1);
            }
            sa = new StateAction(p, new Action(i));
            if (q.containsKey(sa)) {
                q2 = q.get(sa);
            } else {
                q2 = 0.f;
                q.put(sa, q2);
            }
            // track the max and update if necessary
            double util = q1 - q2;
            if (util > maxUtil) {
                maxUtil = util;
            }
        }
        return maxUtil; // return maximum utility
    } // end maxUtility(...)
}