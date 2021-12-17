package espol.model.game;

import espol.model.tda.ArrayList;
import espol.model.tda.Tree;

import java.util.Comparator;
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

        return null;
    }

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
}
