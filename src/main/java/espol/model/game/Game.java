package espol.model.game;

import espol.model.tda.ArrayList;
import espol.model.tda.Tree;

import java.util.Map;
import java.util.TreeMap;

public class Game {
    private Character player;
    private Character bot;
    private boolean playerBegins;
    private Board board;
    private Tree<Character> miniMax;
    private boolean botTurn;
    private boolean gameWon = false;
    private Character winner = 'n';
    private Character EMPTY_CHAR = Board.EMPTY_CHAR;
    private boolean playerTurn = false;

    public Game(Character opt, boolean ifPlayerBegins) {
        player = opt;
        bot = (player.equals('X') ? 'O':'X');
        playerBegins = ifPlayerBegins;
    }
    public void botTurn() {
        //miniMax nextMove = new miniMax(this.getBoard());
        //this.getBoard().markIn(new Pair(nextMove.Row, nextMove.Col), bot);
        //time();
        getBoard().markIn(new Pair(1,1), bot);
    }
    public void time() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public int utilityFunction(){
        int pPlayer=p(board.getMap(),player);
        System.out.println(">" + player + ":" + pPlayer);
        int pBot=p(board.getMap(),bot);
        System.out.println(">" + bot + ":" + pBot);
        return pPlayer-pBot;
    }
    
    public int p(TreeMap<Integer, ArrayList<Cell>> tablero, Character c){
        int utilP=0;
        int filas=0;
        int columnas=0;
        int diagonales=0;
        ArrayList<Cell> F0 = tablero.get(0);
        ArrayList<Cell> F1 = tablero.get(1);
        ArrayList<Cell> F2 = tablero.get(2);
        //columnas
        for(int i=0;i<F0.size();i++){
            if((F0.get(i).getC()==c || F0.get(i).getC()==EMPTY_CHAR) && (F1.get(i).getC()==c || F1.get(i).getC()==EMPTY_CHAR) && (F2.get(i).getC()==c || F2.get(i).getC()==EMPTY_CHAR)){
                columnas++;
                }
        }
        System.out.println("    " + columnas);
        //filas
        int tmp=0;
        for(Map.Entry<Integer, ArrayList<Cell>> par: tablero.entrySet()){  //comprobar si el caracter es el mismo o está vacío
            tmp=0;                
            espol.model.tda.ArrayList<Cell> array=par.getValue();
                    for(int i=0;i<array.size();i++){
                        if(array.get(i).getC()==c || array.get(i).getC()==EMPTY_CHAR)
                            tmp++;                  
                    } 
            if(tmp==3){
            filas++;
            }
        }
        System.out.println("    " + filas);
        //diagonales 
        if((F0.get(0).getC()==c || F0.get(0).getC()==EMPTY_CHAR) && (F1.get(1).getC()==c || F1.get(1).getC()==EMPTY_CHAR) && (F2.get(2).getC()==c || F2.get(2).getC()==EMPTY_CHAR)){
            diagonales++;
        }        
        if((F0.get(2).getC()==c || F0.get(2).getC()==EMPTY_CHAR) && (F1.get(1).getC()==c || F1.get(1).getC()==EMPTY_CHAR) && (F2.get(0).getC()==c || F2.get(0).getC()==EMPTY_CHAR)){
            diagonales++;
        }
        System.out.println("    " + diagonales);
        utilP=filas+columnas+diagonales;                    
        return utilP;
    }

    public boolean checkIfBotWin() { return checkGame(bot); }
    public boolean checkIfPlayerWin() { return checkGame(player); }

    public boolean checkGame(Character c) {
        // Validar filas
        for (int x = 0; x<3; x++) {
            for (int y = 0; y<3; y++) {
                if (board.getMap().get(x).get(y).getC().equals(c)) return true;
            }
        }
        // Validar columnas
        for (int y = 0; y<3; y++) {
            for (int x = 0; x<3; x++) {
                if (board.getMap().get(x).get(y).getC().equals(c)) return true;
            }
        }
        // Validar diagonales
        for (int i = 0; i<3; i++) {
            if (board.getMap().get(i).get(i).getC().equals(c)) return true;
        }
        for (int i = 2; i>-1; i--) {
            if (board.getMap().get(i).get(i).getC().equals(c)) return true;
        }
        return false;
    }

    public boolean isPlayerTurn() { return playerTurn; }
    public void setPlayerturn(Boolean b) { this.playerTurn = b; }
    public Character getPlayer() { return player; }
    public void setPlayer(Character player) { this.player = player; }
    public Character getBot() { return bot; }
    public void setBot(Character bot) { this.bot = bot; }
    public boolean isPlayerBegins() { return playerBegins; }
    public void setPlayerBegins(boolean playerBegins) { this.playerBegins = playerBegins; }
    public Board getBoard() { return board; }
    public void setBoard(Board board) { this.board = board; }
    public Tree<Character> getMiniMax() { return miniMax; }
    public void setMiniMax(Tree<Character> miniMax) { this.miniMax = miniMax; }
    public boolean isBotTurn() { return botTurn; }
    public void setBotTurn(boolean botTurn) { this.botTurn = botTurn; }
    public boolean isGameWon() { return gameWon; }
    public void setGameWon(boolean gameWon) { this.gameWon = gameWon; }
    public Character getWinner() { return winner; }
    public void setWinner(Character winner) { this.gameWon = true; this.winner = winner; }
}
