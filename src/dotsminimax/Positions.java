package dotsminimax;

/**
 *
 * @author vitor
 */
public class Positions {
    private int x;
    private int y;
    private boolean picked;

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    public Positions(int X, int Y){
        this.x = X;
        this.y = Y;
        picked = false;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
