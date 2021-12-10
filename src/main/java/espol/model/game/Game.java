package espol.model.game;

import espol.model.tda.BinaryTree;

import java.util.ArrayList;
import java.util.TreeMap;

public class Game {
    private Character player;
    private Character bot;
    private boolean playerBegins;
    private Board board;
    private BinaryTree<Character> miniMax;
    private boolean botTurn;
    private boolean gameWon = false;
    private Character winner = 'n';

    public Game(Character opt, boolean ifPlayerBegins) {
        player = opt;
        bot = (player.equals('X') ? 'O':'X');
        playerBegins = ifPlayerBegins;
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public Character getBot() {
        return bot;
    }

    public void setBot(Character bot) {
        this.bot = bot;
    }

    public boolean isPlayerBegins() {
        return playerBegins;
    }

    public void setPlayerBegins(boolean playerBegins) {
        this.playerBegins = playerBegins;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) { this.board = board; }

    public BinaryTree<Character> getMiniMax() {
        return miniMax;
    }

    public void setMiniMax(BinaryTree<Character> miniMax) {
        this.miniMax = miniMax;
    }

    public boolean isBotTurn() {
        return botTurn;
    }

    public void setBotTurn(boolean botTurn) {
        this.botTurn = botTurn;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public Character getWinner() {
        return winner;
    }

    public void setWinner(Character winner) {
        this.winner = winner;
    }
}
