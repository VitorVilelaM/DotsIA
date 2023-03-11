package dotsminimax;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Douglas
 */
public class DotsMiniMax {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Positions aux = null;
        String table[][] = new String[5][5];
        HashMap<Integer, Positions> moves = new HashMap<Integer, Positions>();
        Scanner sc = new Scanner(System.in);
        int count = 0, jogada;
        Player IA = new Player("IA", 0);
        Player P1 = new Player("P1", 0);
        Player p = IA;
        boolean repeat = false;

        // Jogador 1 = IA, Jogador 2 = Player
        startMoves(moves);
        Squares gameSquares[] = new Squares[4];
        startSquares(gameSquares, moves, aux);

        fillTable(table);
        printTable(table);
        while (count < 12) {
            System.out.printf("Vez do jogador " + p.getName() + ": ");
            jogada = sc.nextInt();
            repeat = false;
            if (verificaJogada(moves, table, jogada, aux, gameSquares)) {
                count++;
            }
            if (verificaQuadrado(moves, table, jogada, gameSquares, aux, p)) {
                repeat = true;
            }

            if (!repeat) {
                if (p == IA) {
                    p = P1;
                } else {
                    p = IA;
                }
            }
            printTable(table);
        }

        System.out.println("FIM DE JOGO!");

        if (IA.getPoints() != P1.getPoints()) {
            if (IA.getPoints() > P1.getPoints()) {
                p = IA;
            } else {
                p = P1;
            }
            System.out.println("GANHADOR : " + p.getName());
        } else {
            System.out.println("JOGO EMPATADO!");
        }

    }
 
    public static boolean verificaQuadrado(HashMap moves, String table[][], int jogada, Squares gameSquares[], Positions aux, Player p) {

        boolean repeat = false;

        if (jogada == 1 || jogada == 3 || jogada == 6 || jogada == 4) {
            gameSquares[0].setValue(gameSquares[0].getValue() - 1);

            if (gameSquares[0].getValue() == 0) {
                p.setPoints(p.getPoints() + 1);
                aux = (Positions) moves.get(13);
                table[aux.getX()][aux.getY()] = p.getName();
                repeat = true;
            }
        }
        if (jogada == 2 || jogada == 4 || jogada == 7 || jogada == 5) {
            gameSquares[1].setValue(gameSquares[1].getValue() - 1);

            if (gameSquares[1].getValue() == 0) {
                p.setPoints(p.getPoints() + 1);
                aux = (Positions) moves.get(14);
                table[aux.getX()][aux.getY()] = p.getName();
                repeat = true;
            }
        }
        if (jogada == 6 || jogada == 8 || jogada == 9 || jogada == 11) {
            gameSquares[2].setValue(gameSquares[2].getValue() - 1);

            if (gameSquares[2].getValue() == 0) {
                p.setPoints(p.getPoints() + 1);
                aux = (Positions) moves.get(15);
                table[aux.getX()][aux.getY()] = p.getName();
                repeat = true;
            }
        }
        if (jogada == 7 || jogada == 9 || jogada == 10 || jogada == 12) {
            gameSquares[3].setValue(gameSquares[3].getValue() - 1);

            if (gameSquares[3].getValue() == 0) {
                p.setPoints(p.getPoints() + 1);
                aux = (Positions) moves.get(16);
                table[aux.getX()][aux.getY()] = p.getName();
                repeat = true;
            }
        }

        return repeat;
    }

    public static boolean verificaJogada(HashMap moves, String table[][], int jogada, Positions aux, Squares gameSquares[]) {
        boolean itsOk = false;
        if (jogada < 13) {
            aux = (Positions) moves.get(jogada);
            if (!aux.isPicked()) {
                aux.setPicked(true);
                System.out.println("");
                table[aux.getX()][aux.getY()] = "X";
                itsOk = true;
            } else {
                System.out.println("JOGADA JA FEITA");
            }
        } else {
            System.out.println("JOGADA INVALIDA");
        }
        return itsOk;
    }

    public static void startMoves(HashMap moves) {
        Positions p1 = new Positions(0, 1), p2 = new Positions(0, 3), p3 = new Positions(1, 0), p4 = new Positions(1, 2),
                p5 = new Positions(1, 4), p6 = new Positions(2, 1), p7 = new Positions(2, 3), p8 = new Positions(3, 0),
                p9 = new Positions(3, 2), p10 = new Positions(3, 4), p11 = new Positions(4, 1), p12 = new Positions(4, 3),
                p13 = new Positions(1, 1), p14 = new Positions(1, 3), p15 = new Positions(3, 1), p16 = new Positions(3, 3);

        moves.put(1, p1);
        moves.put(2, p2);
        moves.put(3, p3);
        moves.put(4, p4);
        moves.put(5, p5);
        moves.put(6, p6);
        moves.put(7, p7);
        moves.put(8, p8);
        moves.put(9, p9);
        moves.put(10, p10);
        moves.put(11, p11);
        moves.put(12, p12);
        moves.put(13, p13);
        moves.put(14, p14);
        moves.put(15, p15);
        moves.put(16, p16);
    }

    public static void startSquares(Squares squares[], HashMap Moves, Positions aux) {

        //Salvando valores do Quadrado #1
        squares[0] = new Squares();
        aux = (Positions) Moves.get(1);
        squares[0].getSquarePoints().add(aux);

        aux = (Positions) Moves.get(3);
        squares[0].getSquarePoints().add(aux);

        aux = (Positions) Moves.get(4);
        squares[0].getSquarePoints().add(aux);

        aux = (Positions) Moves.get(6);
        squares[0].getSquarePoints().add(aux);

        //Salvando valores do Quadrado #2
        squares[1] = new Squares();

        aux = (Positions) Moves.get(2);
        squares[1].getSquarePoints().add(aux);

        aux = (Positions) Moves.get(4);
        squares[1].getSquarePoints().add(aux);

        aux = (Positions) Moves.get(5);
        squares[1].getSquarePoints().add(aux);

        aux = (Positions) Moves.get(7);
        squares[1].getSquarePoints().add(aux);

        //Salvando valores do Quadrado #3
        squares[2] = new Squares();

        aux = (Positions) Moves.get(6);
        squares[2].getSquarePoints().add(aux);

        aux = (Positions) Moves.get(8);
        squares[2].getSquarePoints().add(aux);

        aux = (Positions) Moves.get(11);
        squares[2].getSquarePoints().add(aux);

        aux = (Positions) Moves.get(9);
        squares[2].getSquarePoints().add(aux);

        //Salvando valores do Quadrado #4
        squares[3] = new Squares();

        aux = (Positions) Moves.get(7);
        squares[3].getSquarePoints().add(aux);

        aux = (Positions) Moves.get(9);
        squares[3].getSquarePoints().add(aux);

        aux = (Positions) Moves.get(12);
        squares[3].getSquarePoints().add(aux);

        aux = (Positions) Moves.get(10);
        squares[3].getSquarePoints().add(aux);

    }

    public static void printTable(String table[][]) {

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                System.out.printf("%s\t", table[i][j]);
            }
            System.out.println("");
        }

    }

    public static void fillTable(String table[][]) {

        /*
            DOTS
         */
        for (int i = 0; i < table.length; i = i + 2) {
            for (int j = 0; j < table.length; j = j + 2) {
                table[i][j] = ".";
            }
        }

        /*
            MOVES
         */
        int initialIndex = 1;
        int cont = 1;
        for (int i = 0; i < table.length; i++) {
            for (int j = initialIndex; j < table.length; j = j + 2) {
                table[i][j] = "" + cont++;
            }
            if (initialIndex == 1) {
                initialIndex = 0;
            } else {
                initialIndex = 1;
            }
        }

        /*
            SQUARES
         */
        for (int i = 1; i < table.length; i = i + 2) {
            for (int j = 1; j < table.length; j = j + 2) {
                table[i][j] = " ";
            }
        }
    }

}
