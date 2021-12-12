package espol.model.game;

import espol.model.tda.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Board {
    private GridPane grid;
    private Character player;
    private Character bot;
    private final String borderStyles = "-fx-border-color: black; -fx-border-width: 2px;";
    private Image X;
    private Image O;
    private Image EMPTY;
    private final double SIZE = 360;

    public Board(Character p, GridPane g) {
        setBasicsOptions();
        player = p;
        bot = (player.equals('X') ? 'O':'X');
        grid = g;
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                Cell cell = new Cell(new Pair(i, j), false);
                cell.setImage(EMPTY);
                cell.setOnMouseClicked(e -> {
                    if (!cell.isSelected()) {
                        cell.setSelected(true);
                        cell.setC(player);
                        cell.setImage((player.equals('X') ? X:O));
                     }
                });
                cell.setStyle(borderStyles);

                grid.add(cell, j, i);
            }
        }
        grid.setAlignment(Pos.CENTER);
        grid.setStyle(borderStyles);
        grid.setPrefSize(SIZE-10,SIZE);
        grid.setMinSize(SIZE-10,SIZE);
        grid.setMaxSize(SIZE-10,SIZE);
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

    public GridPane getGrid() { return grid; }
    public void setGrid(GridPane grid) { this.grid = grid; }
    public Character getPlayer() { return player; }
    public void setPlayer(Character player) { this.player = player; }
    public Character getBot() { return bot; }
    public void setBot(Character bot) { this.bot = bot; }
    public String getBorderStyles() { return borderStyles; }
}
