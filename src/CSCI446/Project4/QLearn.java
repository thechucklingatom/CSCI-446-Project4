package CSCI446.Project4;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mathew Gostnell on 12/10/2016.
 * @author Mathew Gostnell
 */
public class QLearn {

    private Map<StateAction, Integer> n;    // table mapping StateAction frequencies
    private Action a = null;    // last action taken
    private State p = null;     // the previous state visited

    private double r = 0.f;
    private double a = 1.f;

    public Action QLearnAgent(State e, Action j) {
        if (p != null) {
            // N[p, a] <- N[p, a] + 1
            StateAction sa = new StateAction(p, a);
            if (n.containsKey(new StateAction(p, a))) { // update the existing state-action frequency
                n.put(new StateAction(p, a), n.get(new StateAction(p, a)) + 1);
            } else { // insert new frequency count into our table
                n.put(new StateAction(e, a), 1);
            }

            // Q[i, a] = Q[i, a] + a(r + max of
        }
        if (e.getTile().type.equals(Tile.TileType.FINISH)) {
            p = null;
        } else {

        }
        return null;
    }
/*
function Q-LEARNiNG-AGENT(e) returns an action
    static: 	Q, a table of action values
    		    N, a table of state-action frequencies
                a, the last action taken
                p, the previous state visited
                r, the reward received in state p
    j ← STATE[e]
    if p is non-null then
        N[a,p] ← N[a,p] + 1
        Q[a,p] ← Q[a,p] + α(r + maxa’ Q[a’,j] - Q[a,p])
    if TERMINAL?[e] then
        p ← null
    else
        p ← j
        r ← REWARD[e]
    a ← arg maxa’, f(Q[a',j], N[a',j])
 return a
     */
}