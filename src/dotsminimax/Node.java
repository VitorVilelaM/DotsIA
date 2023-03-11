package dotsminimax;

import java.util.ArrayList;

/**
 *
 * @author vitor
 */
public class Node {
    private int id;
    private Player player;
    private ArrayList<Node> filhos;

    public Node(int id) {
        this.id = id;
        filhos = new ArrayList<Node>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Node> getFilhos() {
        return filhos;
    }

    public void setFilhos(ArrayList<Node> filhos) {
        this.filhos = filhos;
    }
    
    
    
    
    
}
