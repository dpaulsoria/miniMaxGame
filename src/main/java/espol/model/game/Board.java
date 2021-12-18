package espol.model.game;

import espol.model.tda.ArrayList;
import static espol.model.game.Utilitaria.*;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Board {
    private GridPane grid;
    private Character player;
    private Character bot;
    public static final Character EMPTY_CHAR = 'n';
    private Game gg;
    private final String borderStyles = "-fx-border-color: #0d2c6b; -fx-border-width: 2px;";
    private Image X;
    private Image O;
    private Image EMPTY;
    private final double SIZE = 360;
    private int utility;
    private TreeMap<Integer, ArrayList<Cell>> map = new TreeMap();

    public Board() {}
    public Board(Character p, Game g) {
        setBasicsOptions();
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
                tmp.addLast(cell);
                cell.setOnMouseClicked(e -> {
                    cellClicked(cell);
                });

                grid.add(cell, j, i);
            }
            map.put(i,tmp);
        }
        setGridStyles(grid);
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
            if (b) gg.setWinner(player);
            //miniMax nextMove = new miniMax(this);
            if (!checkBoardFull()) gg.botTurn();
        }
    }

    public void markIn(Pair position, Character c) {
        map.get(position.x).get(position.y).setC(c);
        map.get(position.x).get(position.y).setSelected(true);
        map.get(position.x).get(position.y).setImage((c.equals('X') ? X:O));
    }
    public void clone(Board board) {
        this.bot = board.getBot();
        this.grid = board.getGrid();
        this.player = board.getPlayer();
        this.gg = board.getGg();
        this.X = board.getX();
        this.O = board.getO();
        this.EMPTY = board.getEMPTY();
        this.map = board.getMap();
        this.utility = board.getUtility();
    }

    private void setBasicsOptions() {
        try {
            X = new Image(new FileInputStream("src/img/X.png"));
            O = new Image(new FileInputStream("src/img/O.png"));
            EMPTY = new Image(new FileInputStream("src/img/circle.png"));
        } catch(Exception e) {
            System.out.println(e.toString());
        }
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