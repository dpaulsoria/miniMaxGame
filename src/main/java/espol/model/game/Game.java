package espol.model.game;

import espol.model.tda.BinaryTree;
import javafx.util.Pair;
import java.util.ArrayList;

public class Game {
    private Character player;
    private Character bot;
    private ArrayList<Pair<Integer, Integer>> reserved = new ArrayList<>();
    private BinaryTree<Character> miniMax;
    private boolean botTurn;

    public Game(Character player) {
        bot = getBotChar(player);
    }

    private Character getBotChar(Character player) {
        return (player.equals('X') ? 'O':'X');
    }

    private BinaryTree<Character> genMiniMax(Character bot) {
        return null;
    }

    public void setBotTurn(boolean turn) { botTurn = turn; }
    public boolean isBotTurn() { return botTurn; }
}
