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
        Player IA = new Player("IA");
        Player P1 = new Player("P1");
        Player p = IA;
        boolean repeat = false;

        // Jogador 1 = IA, Jogador 2 = Player
        startMoves(moves);
        String gameSquares[] = new String[4];

        fillTable(table);
        
        Node start = new Node(1, "IA");
        boolean posicoes[] = new boolean[12];

        for (int i = 0; i < 12; i++) {
            posicoes[i] = true;
        }

        posicoes[0] = false;
        verificaJogada(moves, table, 1, aux, "IA");
        count++;

        printTable(table);

        System.out.printf("Vez do jogador: ");
        jogada = sc.nextInt();

        while (!(verificaJogada(moves, table, jogada, aux, "P1"))) {
            System.out.println("JOGADA INVALIDA");
            System.out.print("Tente novamente: ");
            jogada = sc.nextInt();
        }
        count++;
        printTable(table);
        
        posicoes[jogada - 1] = false;
        verificaJogada(moves, table, jogada, aux, "P1");
        verificaQuadrado(moves, table, jogada, gameSquares, aux, "P1");

        preencherArvore(start, posicoes, table, moves, gameSquares, "IA");
        minMax(start);
        verificaGanhador(start);
        /*
        while (count < 12) {
            repeat = false;
            if (verificaJogada(moves, table, jogada, aux, gameSquares)) {
                count++;
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
         */
    }

    public static void verificaGanhador(Node start) {
        if (start.getFilhos().isEmpty()) {
            if (start.getMinmax() == 0) {
                printaResults(start);
            }
        } else {
            for (Node aux : start.getFilhos()) {
                verificaGanhador(aux);
            }
        }
    }

    public static void minMax(Node start) {
        String ganhador;
        if (start.getFilhos().isEmpty()) {
            ganhador = verificaGanhador(start.getGamesSquare());
            if (ganhador.equals("IA")) {
                start.setMinmax(1);
            } else if (ganhador.equals("P1")) {
                start.setMinmax(-1);
            } else {
                start.setMinmax(0);
            }
        } else {
            for (Node aux : start.getFilhos()) {
                minMax(aux);
            }
        }
    }

    public static String verificaGanhador(String[] ganhadores) {
        int count = 0;
        String ganhador;

        for (String a : ganhadores) {
            if (a.equals("IA")) {
                count++;
            }
        }

        if (count > 2) {
            ganhador = "IA";
        } else if (count < 2) {
            ganhador = "P1";
        } else {
            ganhador = "Drawn";
        }

        return ganhador;
    }

    public static void printaResults(Node filho) {
        System.out.println("NODE: " + filho.getId() + " RESULTADO: " + filho.getGamesSquare()[0] + " " + filho.getGamesSquare()[1] + " " + filho.getGamesSquare()[2] + " " + filho.getGamesSquare()[3]);
    }

    public static void preencherArvore(Node start, boolean posicoes[], String table[][], HashMap moves, String gameSquares[], String p) {
        String p1 = "IA", p2 = "P1";
        Positions auxPos = null;
        boolean trade = true;
        String copiaSquares[] = new String[4];
        String copia[][] = new String[5][5];

        for (int i = 0; i < 12; i++) {
            if (posicoes[i]) {

                Node aux = new Node(i + 1, p);
                start.getFilhos().add(aux);

                posicoes[i] = false;
                copiaTabuleiro(copia, table);
                if (verificaJogada(moves, copia, i + 1, auxPos, p)) {
                    if (verificaQuadrado(moves, copia, i + 1, gameSquares, auxPos, p)) {
                        trade = false;
                    }
                }

                if (trade) {
                    if (p.equals(p1)) {
                        p = p2;
                    } else {
                        p = p1;
                    }
                }
                copiaResult(gameSquares, copiaSquares);
                aux.setGamesSquare(copiaSquares);
                preencherArvore(aux, posicoes, copia, moves, gameSquares, p);
                posicoes[i] = true;
                atualizaQuadrado(i + 1, moves, auxPos);
            }

        }
    }

    public static void copiaResult(String original[], String[] copia) {
        int i = 0;
        for (String aux : original) {
            copia[i] = aux;
            i++;
        }
    }

    public static void copiaTabuleiro(String copia[][], String original[][]) {
        int i, j;
        for (i = 0; i < original.length; i++) {
            for (j = 0; j < original.length; j++) {
                copia[i][j] = original[i][j];
            }
        }
    }

    public static void atualizaQuadrado(int jogada, HashMap moves, Positions aux) {
        aux = (Positions) moves.get(jogada);
        aux.setPicked(false);
    }

    public static boolean verificaQuadrado(HashMap moves, String table[][], int jogada, String gameSquares[], Positions aux, String p) {
        Positions p1, p2, p3, p4;
        boolean repeat = false;

        if (jogada == 1 || jogada == 3 || jogada == 6 || jogada == 4) {
            p1 = (Positions) moves.get(1);
            p2 = (Positions) moves.get(3);
            p3 = (Positions) moves.get(6);
            p4 = (Positions) moves.get(4);

            if (p1.isPicked() && p2.isPicked() && p3.isPicked() && p4.isPicked()) {
                gameSquares[0] = p;
                aux = (Positions) moves.get(13);
                table[aux.getX()][aux.getY()] = p;
                repeat = true;
            }
        }
        if (jogada == 2 || jogada == 4 || jogada == 7 || jogada == 5) {
            p1 = (Positions) moves.get(2);
            p2 = (Positions) moves.get(4);
            p3 = (Positions) moves.get(7);
            p4 = (Positions) moves.get(5);

            if (p1.isPicked() && p2.isPicked() && p3.isPicked() && p4.isPicked()) {
                gameSquares[1] = p;
                aux = (Positions) moves.get(14);
                table[aux.getX()][aux.getY()] = p;
                repeat = true;
            }
        }
        if (jogada == 6 || jogada == 8 || jogada == 9 || jogada == 11) {
            p1 = (Positions) moves.get(6);
            p2 = (Positions) moves.get(8);
            p3 = (Positions) moves.get(9);
            p4 = (Positions) moves.get(11);

            if (p1.isPicked() && p2.isPicked() && p3.isPicked() && p4.isPicked()) {
                gameSquares[2] = p;
                aux = (Positions) moves.get(15);
                table[aux.getX()][aux.getY()] = p;
                repeat = true;
            }
        }
        if (jogada == 7 || jogada == 9 || jogada == 10 || jogada == 12) {
            p1 = (Positions) moves.get(7);
            p2 = (Positions) moves.get(9);
            p3 = (Positions) moves.get(10);
            p4 = (Positions) moves.get(12);

            if (p1.isPicked() && p2.isPicked() && p3.isPicked() && p4.isPicked()) {
                gameSquares[3] = p;
                aux = (Positions) moves.get(16);
                table[aux.getX()][aux.getY()] = p;
                repeat = true;
            }
        }

        return repeat;
    }

    public static boolean verificaJogada(HashMap moves, String table[][], int jogada, Positions aux, String p) {
        boolean itsOk = false;
        if (jogada < 13) {
            aux = (Positions) moves.get(jogada);
            if (!aux.isPicked()) {
                aux.setPicked(true);
                table[aux.getX()][aux.getY()] = p;
                itsOk = true;
            } else {
                //   System.out.println("JOGADA JA FEITA");
            }
        } else {
            //System.out.println("JOGADA INVALIDA");
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
