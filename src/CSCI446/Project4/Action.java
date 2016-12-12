package CSCI446.Project4;

/**
 * Created by Thew on 12/10/2016.
 */
public class Action {
    private final DIRECTION dir;

    public Action(DIRECTION dir) {
        this.dir = dir;
    }

    public Action(int d) {
        switch(d) {
            case 0:
                dir = DIRECTION.NORTH;
                break;
            case 1:
                dir = DIRECTION.NORTHEAST;
                break;
            case 2:
                dir = DIRECTION.EAST;
                break;
            case 3:
                dir =  DIRECTION.SOUTHEAST;
                break;
            case 4:
                dir = DIRECTION.SOUTH;
                break;
            case 5:
                dir = DIRECTION.SOUTHWEST;
                break;
            case 6:
                dir = DIRECTION.WEST;
                break;
            case 7:
                dir = DIRECTION.NORTHWEST;
                break;
            case 8:
                dir = DIRECTION.STOP;
                break;
            default:
                dir = DIRECTION.STOP;
                break;
        }
    }

    public String toString() {
        return dir.toString();
    }

    public void printAction() {
        dir.printAction();
    }

    public int getActionInt() {
        return dir.orientation;
    }

    public enum DIRECTION {
        /*
            0   := NORTH
            1   := NORTHEAST
            2   := EAST
            3   := SOUTHEAST
            4   := SOUTH
            5   := SOUTHWEST
            6   := WEST
            7   := NORTHWEST
            8   := STOP
         */
        NORTH(0), NORTHEAST(1), EAST(2), SOUTHEAST(3), SOUTH(4),  SOUTHWEST(5), WEST(6), NORTHWEST(7), STOP(8);

        private int orientation; // int representation of our Directions

        /**
        Create a new Direction based on the mapping above
         */
        DIRECTION(int dir) {
            this.orientation = dir%9;
        }// end Direction(int dir)

        /**
        Convert this Direction into an Integer based on the mapping above
         */
        public int getInt() {
            return orientation;
        } // end getInt()

        public void printAction() {
            System.out.println("Action: " + this.getDirection());
        } // end printAction()

        /**
        Convert this Direction into a String based on the mapping above
         **/
        public String getDirection() {
            switch(orientation) {
                case 0:
                    return "NORTH";
                case 1:
                    return "NORTHEAST";
                case 2:
                    return "EAST";
                case 3:
                    return "SOUTHEAST";
                case 4:
                    return "SOUTH";
                case 5:
                    return "SOUTHWEST";
                case 6:
                    return "WEST";
                case 7:
                    return "NORTHWEST";
                case 8:
                    return "STOP";
                default:
                    return ""; // this case should never happen due to modulo in constructor
            }// end switch
        } // end getDirection()
    } // end enum DIRECTION

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(o instanceof Action){
            return dir.equals(((Action) o).dir);
        }else{
            return false;
        }
    }
} // end Class Action
