package CSCI446.Project4;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mathew Gostnell on 12/10/2016.
 * @author Mathew Gostnell
 */
public class QLearn {

    private Map<StateAction, Integer> n;    // table
    private Action a = null;
    private State p = null;

    private double r = 0.f;
    private double a = 1.f;

    public Action QLearnAgent(State e) {
        State j = e;
        if (p != null) {
            if (n.containsKey(new StateAction(e, a))) { // update the existing state-action frequency
                n.put(new StateAction(e, a), n.get(new StateAction(e, a)) + 1);
            } else { // insert new frequency count into our table
                n.put(new StateAction(e, a), 1);
            }
            // Q[i, a] = Q[i, a] + a(r + max of
        }
    }

    /*
    Q(s,a) ← Q(s, a) + (r + maxa’ Q(s’,a’) - Q(s,a))
Where the learning rate, , is ∈[0,1] and is a discount assigned value that is associated with weighting updates to our Q(s,a) table.


function Q-LEARNiNG-AGENT(e) returns an action
    static: 	Q, a table of action values
    		    N, a table of state-action frequencies
                a, the last action taken
                i, the previous state visited
                r, the reward received in state i
    j ← STATE[e]
    if i is non-null then
        N[a,i] ← N[a,i] + 1
        Q[a,i] ← Q[a,i] + α(r + maxa’ Q[a’,j] - Q[a,i])
    if TERMINAL?[e] then
        i ← null
    else
        i ← j
        r ← REWARD[e]
    a ← arg maxa’, f(Q[a',j], N[a',j])
 return a

     */

}

class StateAction {
    // used as entry into the table of state-action frequencies
    private final State s;
    private final Action a;

    public StateAction(State s, Action a) {
        this.s = s;
        this.a = a;
    }

    public State getState() { return s; }
    public Action getAction() { return a; }
    public void printStateAction() {
        System.out.println(s.toString());
        a.printAction();
    }
}