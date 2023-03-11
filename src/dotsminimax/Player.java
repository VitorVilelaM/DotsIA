package dotsminimax;

/**
 *
 * @author vitor
 */
public class Player {
    private String name;
    private int points;
    
    public Player(String Name, int Points){
        this.name = Name;
        this.points = Points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
