package dotsminimax;

import java.util.ArrayList;

/**
 *
 * @author vitor
 */
public class Node {
    private int id;
    private int minmax = 0;
    private String player;
    private String[] gamesSquare = new String[4];

    public String[] getGamesSquare() {
        return gamesSquare;
    }

    public void setGamesSquare(String[] gamesSquare) {
        this.gamesSquare = gamesSquare;
    }
    private ArrayList<Node> filhos;

    public int getMinmax() {
        return minmax;
    }

    public void setMinmax(int minmax) {
        this.minmax = minmax;
    }
    
    public Node(int id, String P) {
        this.id = id;
        filhos = new ArrayList<Node>();
        player = P;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public ArrayList<Node> getFilhos() {
        return filhos;
    }

    public void setFilhos(ArrayList<Node> filhos) {
        this.filhos = filhos;
    }
    
    public void printNode(){
        System.out.println("Nome do Jogador: "+player+" "+"ID Node: " + id);
    
    }
    
}
