package espol.model.game;

import espol.controller.HelloController;
import espol.model.tda.Tree;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.TreeMap;

public class Board {
    private GridPane grid;
    private GridPane grid1;
    private Character player;
    private Character bot;
    public static final Character EMPTY_CHAR = 'n';
    private Game gg;
    private final String borderStyles = "-fx-border-color: #0d2c6b; -fx-border-width: 2px;";
    private Image X = Images.X;
    private Image O = Images.O;
    private Image EMPTY = Images.EMPTY;
    private final double SIZE = 360;
    private int utility;
    private TreeMap<Integer, ArrayList<Cell>> map = new TreeMap();
    private TreeMap<Integer, ArrayList<Cell>> map1 = new TreeMap();
    private Tree<Capsule> currentTree;
    private Tree<Capsule> recommendTree;

    public Board(Character p, Game g) {
        grid = new GridPane();
        grid1 = new GridPane();
        gg = g;
        gg.setBoard(this);
        player = p;
        bot = (player.equals('X') ? 'O':'X');
        for (int i = 0; i<3; i++) {
            ArrayList<Cell> tmp = new ArrayList();
            for (int j = 0; j<3; j++) {
                Cell cell = new Cell(new Pair(i, j), false);
                cell.setImage(EMPTY);
                tmp.add(cell);
                cell.setOnMouseClicked(e -> {
                    cellClicked(cell, map);
                });
                cell.setStyle(borderStyles);
                grid.add(cell, j, i);
            }
            map.put(i,tmp);
        }
        setGridStyles(grid);
        for (int i = 0; i<3; i++) {
            ArrayList<Cell> tmp = new ArrayList();
            for (int j = 0; j<3; j++) {
                Cell cell = new Cell(new Pair(i, j), true);
                cell.setImage(EMPTY);
                cell.setFitHeight(80);
                cell.setFitWidth(80);
                tmp.add(cell);
                grid1.add(cell, j, i);
            }
            map1.put(i,tmp);
        }
        currentTree = miniMax.createTree(map, gg.isPlayerBegins() ? player:bot);
        recommendTree = miniMax.createTree(map, player);
        refresh(map,map1);
        if (!gg.isPlayerBegins()) gg.firstBotTurn(miniMax.getMaxN(currentTree).getMap());
    }
    

    public void setGridStyles(GridPane grid) {
        grid.setAlignment(Pos.CENTER);
        grid.setStyle(borderStyles);
        grid.setPrefSize(SIZE-10,SIZE);
        grid.setMinSize(SIZE-10,SIZE);
        grid.setMaxSize(SIZE-10,SIZE);
    }
    
    public void cellClicked(Cell cell, TreeMap<Integer, ArrayList<Cell>> map) {
        if (!cell.isSelected()) {
            cell.setSelected(true);
            cell.setC(player);
            cell.setImage((player.equals('X') ? X:O));
            utility =  gg.utilityFunction();
            System.out.println("--> Utility: " + utility);
            boolean b = gg.checkGame(player);
            System.out.println("Check game: " + b);
            gg.printBoard(this.getMap());
            if (b) {
                gg.setWinner(player);
                gg.setGameWon(b);
                gg.endGame(map);
            }
            if(gg.isGameWon()) System.out.println("end game");
            if(!gg.isGameWon()){
                currentTree = miniMax.createTree(map, gg.isPlayerBegins() ? player:bot);
                if (!checkBoardFull()) gg.botTurn(miniMax.getMaxN(currentTree).getMap(), map, bot);
            }
        }
        checkEmpate();
    }

    public void markIn(Pair position, Character c) {
        map.get(position.x).get(position.y).setC(c);
        map.get(position.x).get(position.y).setSelected(true);
        if (c.equals('X') || c.equals('O')) map.get(position.x).get(position.y).setImage((c.equals('X') ? X:O));
        else map.get(position.x).get(position.y).setImage(EMPTY);
    }

    public void checkEmpate() {
        if (gg.checkIfBotWin()) HelloController.alert("Ganan las " + bot + "!", "INFORMATION");
        else if (gg.checkIfPlayerWin()) HelloController.alert("Ganan las " + player + "!", "INFORMATION");
        else if (checkBoardFull() && !gg.checkIfPlayerWin() && !gg.checkIfBotWin()) HelloController.alert("EMPATE!", "INFORMATION");
    }

    public void clear() { grid.getChildren().clear(); }

    public boolean checkBoardFull() {
        int c = 0;
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                if (!map.get(i).get(j).getC().equals(EMPTY_CHAR)) c++;
                if (c == 9) return true;
            }
        }
        return false;
    }

    public static TreeMap<Integer, ArrayList<Cell>> cloneMap(TreeMap<Integer, ArrayList<Cell>> map) {
        TreeMap<Integer, ArrayList<Cell>> newMap = new TreeMap<>();
        for (int i = 0; i<3; i++) {
            ArrayList<Cell> tmp = new ArrayList<>();
            for (int j = 0; j<3; j++) {
                Cell newCell = new Cell(map.get(i).get(j).getPosition().clone(), map.get(i).get(j).isSelected());
                newCell.setC(map.get(i).get(j).getC());
                tmp.add(newCell);
            }
            newMap.put(i, tmp);
        }
        return newMap;
    }


    public static int countNulls(TreeMap<Integer, ArrayList<Cell>> map) {
        int nulls = 0;
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                if (map.get(i).get(j).getC().equals(EMPTY_CHAR)) nulls++;
            }
        }
        return nulls;
    }

    public static ArrayList<Pair> getNullPairs(TreeMap<Integer, ArrayList<Cell>> map) {
        ArrayList<Pair> nulls = new ArrayList<>();
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                if (map.get(i).get(j).getC().equals(EMPTY_CHAR)) nulls.add(map.get(i).get(j).getPosition());
            }
        }
        return nulls;
    }

    public boolean equals(Board board) {
        return !player.equals(board.player) || !bot.equals(board.bot) || utility != board.getUtility() || MapEquals(map, board.getMap());
    }

    private boolean MapEquals(TreeMap<Integer, ArrayList<Cell>> map1, TreeMap<Integer, ArrayList<Cell>> map2) {
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                if (!map1.get(i).get(j).equals(map2.get(i).get(j))) return false;
            }
        }
        return true;
    }
    
    public void refresh(TreeMap<Integer, ArrayList<Cell>> map,TreeMap<Integer, ArrayList<Cell>> map1){
        recommendTree = miniMax.createTree(map, player);
        if(miniMax.getMaxN(recommendTree).getMap()!=null){
            TreeMap<Integer, ArrayList<Cell>> tmp = cloneMap(miniMax.getMaxN(recommendTree).getMap());
            for (int i = 0; i<3; i++) {
                for (int j = 0; j<3; j++) {
                    if (!(tmp.get(i).get(j).getC().equals(map1.get(i).get(j).getC()))) {
                        Character c = tmp.get(i).get(j).getC();
                        map1.get(i).get(j).setC(c);
                        if (c.equals('X') || c.equals('O')) map1.get(i).get(j).setImage((c.equals('X') ? X:O));
                        else map1.get(i).get(j).setImage(EMPTY);
                    }
                }
            }
        }
    }

    public void setUtility(int u) { this.utility = u; }
    public int getUtility() { return utility; }
    public GridPane getGrid() { return grid; }
    public void setGrid(GridPane grid) { this.grid = grid; }
    public Character getPlayer() { return player; }
    public void setPlayer(Character player) { this.player = player; }
    public Character getBot() { return bot; }
    public void setBot(Character bot) { this.bot = bot; }
    public String getBorderStyles() { return borderStyles; }
    public Game getGg() { return gg; }
    public void setGg(Game gg) { this.gg = gg; }
    public Image getX() { return X; }
    public void setX(Image x) { X = x; }
    public Image getO() { return O; }
    public void setO(Image o) { O = o; }
    public Image getEMPTY() { return EMPTY; }
    public void setEMPTY(Image EMPTY) { this.EMPTY = EMPTY; }
    public double getSIZE() { return SIZE;  }
    public TreeMap<Integer, ArrayList<Cell>> getMap() { return map; }
    public void setMap(TreeMap<Integer, ArrayList<Cell>> map) { this.map = map; }

    public Tree<Capsule> getRecommendTree() {
        return recommendTree;
    }

    public void setRecommendTree(Tree<Capsule> recommendTree) {
        this.recommendTree = recommendTree;
    }

    public GridPane getGrid1() {
        return grid1;
    }

    public void setGrid1(GridPane grid1) {
        this.grid1 = grid1;
    }

    public TreeMap<Integer, ArrayList<Cell>> getMap1() {
        return map1;
    }

    public void setCurrentTree(Tree<Capsule> t) { currentTree = t; }
    public Tree<Capsule> getCurrentTree() { return currentTree; }
}