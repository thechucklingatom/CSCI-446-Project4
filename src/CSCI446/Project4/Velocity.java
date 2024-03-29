package CSCI446.Project4;

/**
 * Created by Thew on 12/10/2016.
 */
public class Velocity {
    private double xVelocity;
    private double yVelocity;
    private static double minVal = -5.f;
    private static double maxVal = 5.f;

    public Velocity() {
        this.xVelocity = 0;
        this.yVelocity = 0;
    }

    public Velocity(double xVel, double yVel) {
        xVelocity = xVel;
        yVelocity = yVel;
    }

    public void reset(){
        this.xVelocity = 0;
        this.yVelocity = 0;
    }

    public double getxVelocity() { return xVelocity; }
    public double getyVelocity() { return yVelocity; }

    public void setxVelocity(double xVel) {
        xVelocity += xVel;
        if (xVelocity < minVal) {
            xVelocity = minVal;
        }
        if (xVelocity > maxVal) {
            xVelocity = maxVal;
        }
    }

    public void setyVelocity(double yVel) {
        yVelocity += yVel;
        if (yVelocity < minVal) {
            yVelocity = minVal;
        }
        if (yVelocity > maxVal) {
            yVelocity = maxVal;
        }
    }

    @Override
    public String toString() {
        return "Velocity:\n\t<" + xVelocity + ", " + yVelocity + ">";
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Velocity){
            return (int)xVelocity == (int)((Velocity) o).getxVelocity() &&
                    (int) yVelocity == (int)((Velocity) o).getyVelocity();
        }
        return false;
    }
}
