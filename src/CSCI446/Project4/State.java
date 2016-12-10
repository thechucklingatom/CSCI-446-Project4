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
    public String toPrintString() {
        return "State:\n" + t.toPrintString() + v.toPrintString();
    }
}
