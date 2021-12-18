package espol.model.game;

import espol.model.tda.Tree;

import java.util.ArrayList;
import java.util.TreeMap;

public class miniMax {

    private int minimo_Value = Integer.MIN_VALUE;
    private int maximo_Value = Integer.MAX_VALUE;
    int Row;
    int Col;
    private Board board;
    public miniMax(Board board) { this.board = getMiniMax(board); }

    public Board getMiniMax(Board currentBoard) {
        Tree<Board> tree = new Tree(board);
        int max = minimo_Value;
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {

            }
        }
        return null;
    }

    public static ArrayList<TreeMap<Integer, ArrayList<Cell>>> getNextMoves(TreeMap<Integer, ArrayList<Cell>> map, Character c) {
        ArrayList<TreeMap<Integer, ArrayList<Cell>>> result = new ArrayList<>();
        TreeMap<Integer, ArrayList<Cell>> tmp = Board.cloneMap(map);
        System.out.println("Get next Moves " + Board.countNulls(tmp));
        for (int i = 0; i<Board.countNulls(tmp); i++) {
            tmp = nextMove(tmp, c);
            result.add(tmp);
        }
        return result;
    }
    private static TreeMap<Integer, ArrayList<Cell>> nextMove(TreeMap<Integer, ArrayList<Cell>> map1, Character c) {
        TreeMap<Integer, ArrayList<Cell>> map = Board.cloneMap(map1);
        ArrayList<Pair> nulls = Board.getNullPairs(map);
        System.out.println("Nulls pair");
        nulls.forEach((v) -> {
            System.out.println("    " + v);
        });
        Pair currentNull;
        System.out.println("Nulls size: " + nulls.size());
        if (nulls.size() != 0) {
            currentNull = nulls.get(0);
            map.get(currentNull.x).get(currentNull.y).setC(c);
        }
        return map;
    }
    /*
    public Board dminiMax(Board board) {
        Tree<Board> tree = new Tree(board);
        Board play = new Board();
        int max = minimo_Value;
        for (int x = 0; x<3; x++) {
            for (int y = 0; y<3; y++) {
                if (!board.getMap().get(x).get(y).isSelected()) {
                    Board child = new Board();
                    child.clone(board);
                    Character child_c = child.getGg().isPlayerTurn() ? child.getPlayer(): child.getBot();
                    child.markIn(new Pair(x, y), child_c);
                    tree.addChild(new Tree(child));
                    int min = maximo_Value;
                    for (int X = 0; X<3; X++) {
                        for (int Y = 0; Y<3; Y++) {
                            if (!child.getMap().get(X).get(Y).isSelected()) {
                                Board secondChild = new Board();
                                secondChild.clone(child);
                                Character secondChild_c = secondChild.getGg().isPlayerTurn() ? secondChild.getPlayer(): secondChild.getBot();
                                secondChild.markIn(new Pair(X, Y), secondChild_c);
                                tree.getLastChild().addChild(new Tree(secondChild));
                                int U = secondChild.getGg().utilityFunction();
                                if (secondChild.getGg().getWinner().equals(child_c) && secondChild.getGg().getWinner().equals(secondChild_c))
                                    U = minimo_Value;
                                min = Math.min(min, U);
                            }
                        }
                    }
                    child.setUtility(min);
                    int utility = child.getUtility();
                    play = utility > max ? child:play;
                    Row = utility > max ? x:Row;
                    Col = utility > max ? y:Col;
                    max = Math.max(max, utility);
                }
            }
        }
        return play;
    }
    */
}
