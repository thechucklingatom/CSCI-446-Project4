package CSCI446.Project4;

/**
 * Created by Thew on 12/10/2016.
 */
public class State {
    private final Tile t;
    private final Velocity v;

    public State(Tile t, Velocity v) {
        this.t = t;
        this.v = v;
    }

    public Tile getTile() { return t; }
    public Velocity getVelocity() { return v; }

    @Override
    public String toString() {
        return "State:\n" + t.toString() + v.toString();
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof State){
            return t.equals(((State) o).t) && v.equals(((State) o).v);
        }else{
            return false;
        }
    }
}
