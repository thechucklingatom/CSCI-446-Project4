package CSCI446.Project4;

/**
 * Created by Thew on 12/10/2016.
 */
public class Velocity {
    public double xVelocity;
    public double yVelocity;

    public Velocity() {
        this.xVelocity = 0;
        this.yVelocity = 0;
    }

    public Velocity(double xVel, double yVel) {
        xVelocity = xVel;
        yVelocity = yVel;
    }

    @Override
    public String toString() {
        return "Velocity:\n\t<" + xVelocity + ", " + yVelocity + ">";
    }
}
