package espol.model.game;

import espol.model.tda.Tree;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.TreeMap;

public class Board {
    private GridPane grid;
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
    private Tree<Capsule> currentTree;

    public Board(Character p, Game g) {
        grid = new GridPane();
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
                    cellClicked(cell);
                });
                cell.setStyle(borderStyles);
                grid.add(cell, j, i);
            }
            map.put(i,tmp);
        }
        setGridStyles(grid);
        currentTree = Utilitaria.createTree(map, gg.isPlayerBegins() ? player:bot);
        if (!gg.isPlayerBegins()) gg.firstBotTurn(Utilitaria.getMaxN(currentTree).getMap());
//        gg.printBoard(tree.getRoot().getContent().getMap());
//        gg.printBoard(tree.getChild(0).getRoot().getContent().getMap());
    }
    

    public void setGridStyles(GridPane grid) {
        grid.setAlignment(Pos.CENTER);
        grid.setStyle(borderStyles);
        grid.setPrefSize(SIZE-10,SIZE);
        grid.setMinSize(SIZE-10,SIZE);
        grid.setMaxSize(SIZE-10,SIZE);
    }
    
    public void cellClicked(Cell cell) {
        if (!cell.isSelected()) {
            cell.setSelected(true);
            cell.setC(player);
            cell.setImage((player.equals('X') ? X:O));
            utility =  gg.utilityFunction();
            System.out.println("--> Utility: " + utility);
            boolean b = gg.checkGame(player);
            System.out.println("Check game: " + b);
            gg.printBoard(this.getMap());
            if (b) gg.setWinner(player);
            miniMax nextMove = new miniMax(this);

            //if (!checkBoardFull()) gg.botTurn(Utilitaria.getMaxN(currentTree));
        }
    }

    public void markIn(Pair position, Character c) {
        map.get(position.x).get(position.y).setC(c);
        map.get(position.x).get(position.y).setSelected(true);
        if (c.equals('X') || c.equals('O')) map.get(position.x).get(position.y).setImage((c.equals('X') ? X:O));
        else map.get(position.x).get(position.y).setImage(EMPTY);
    }

    public void clear() { grid.getChildren().clear(); }

    public boolean checkBoardFull() {
        int c = 0;
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                if (map.get(i).get(j).getC().equals('n')) c++;
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
}