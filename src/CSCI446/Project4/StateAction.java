package CSCI446.Project4;

/**
 * Created by Thew on 12/10/2016.
 */
public class StateAction {
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

    @Override
    public boolean equals(Object o){
        if(o instanceof StateAction){
            return s.equals(((StateAction) o).getState()) && a.equals(((StateAction) o).getAction());
        }else{
            return false;
        }
    }
}
