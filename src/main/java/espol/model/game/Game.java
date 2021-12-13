package espol.model.game;

import espol.model.tda.ArrayList;
import espol.model.tda.BinaryTree;
import java.util.Map;
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
    public void botTurn(Pair position) {
        Cell c = new Cell(new Pair(position.x, position.y), true);
        c.setC(bot);
        c.setImage((player.equals('X') ? board.getO():board.getX()));
        board.getGrid().add(c, position.x, position.y);
        TreeMap<Integer, ArrayList<Cell>> map = board.getMap();
        map.get(position.y).get(position.x).setC(bot);
    }
    
    public int utilidad(){
        int pJugador=p(board.getMap(),player);
        System.out.println(">" + player + ":" + pJugador);
        int pBot=p(board.getMap(),bot);
        System.out.println(">" + bot + ":" + pBot);
        return pJugador-pBot;
    }
    
    public int p(TreeMap<Integer, espol.model.tda.ArrayList<Cell>> tablero, Character c){
        int utilP=0;
        int filas=0;
        int columnas=0;
        int diagonales=0;
        espol.model.tda.ArrayList<Cell> F0 = tablero.get(0);
        espol.model.tda.ArrayList<Cell> F1 = tablero.get(1);
        espol.model.tda.ArrayList<Cell> F2 = tablero.get(2);
        //columnas
        for(int i=0;i<F0.size();i++){
            if((F0.get(i).getC()==c || F0.get(i).getC()=='n') && (F1.get(i).getC()==c || F1.get(i).getC()=='n') && (F2.get(i).getC()==c || F2.get(i).getC()=='n')){
                columnas++;
                }
        }
        System.out.println("    " + columnas);
        //filas
        int tmp=0;
        for(Map.Entry<Integer, espol.model.tda.ArrayList<Cell>> par: tablero.entrySet()){  //comprobar si el caracter es el mismo o está vacío
            tmp=0;                
            espol.model.tda.ArrayList<Cell> array=par.getValue();
                    for(int i=0;i<array.size();i++){
                        if(array.get(i).getC()==c || array.get(i).getC()=='n')
                            tmp++;                  
                    } 
            if(tmp==3){
            filas++;
            }
        }
        System.out.println("    " + filas);
        //diagonales 
        if((F0.get(0).getC()==c || F0.get(0).getC()=='n') && (F1.get(1).getC()==c || F1.get(1).getC()=='n') && (F2.get(2).getC()==c || F2.get(2).getC()=='n')){
            diagonales++;
        }        
        if((F0.get(2).getC()==c || F0.get(2).getC()=='n') && (F1.get(1).getC()==c || F1.get(1).getC()=='n') && (F2.get(0).getC()==c || F2.get(0).getC()=='n')){
            diagonales++;
        }
        System.out.println("    " + diagonales);
        utilP=filas+columnas+diagonales;                    
        return utilP;
    }

    public Character getPlayer() { return player; }
    public void setPlayer(Character player) { this.player = player; }
    public Character getBot() { return bot; }
    public void setBot(Character bot) { this.bot = bot; }
    public boolean isPlayerBegins() { return playerBegins; }
    public void setPlayerBegins(boolean playerBegins) { this.playerBegins = playerBegins; }
    public Board getBoard() { return board; }
    public void setBoard(Board board) { this.board = board; }
    public BinaryTree<Character> getMiniMax() { return miniMax; }
    public void setMiniMax(BinaryTree<Character> miniMax) { this.miniMax = miniMax; }
    public boolean isBotTurn() { return botTurn; }
    public void setBotTurn(boolean botTurn) { this.botTurn = botTurn; }
    public boolean isGameWon() { return gameWon; }
    public void setGameWon(boolean gameWon) { this.gameWon = gameWon; }
    public Character getWinner() { return winner; }
    public void setWinner(Character winner) { this.winner = winner; }
}
