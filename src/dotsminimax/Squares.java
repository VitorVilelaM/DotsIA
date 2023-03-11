package dotsminimax;

import java.util.ArrayList;

/**
 *
 * @author vitor
 */
public class Squares {
    private int value = 4;
    ArrayList<Positions> squarePoints = new ArrayList<>();

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<Positions> getSquarePoints() {
        return squarePoints;
    }
    
}
