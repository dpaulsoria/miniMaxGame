package espol.model.game;

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

    public Board(Character p) {
        player = p;
        bot = (player.equals('X') ? 'O':'X');
        grid = new GridPane();
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                StackPane sp = new StackPane();
                Cell cell = new Cell(player, new Pair(i, j), false);
                cell.setCellEvents(sp);
                sp.getChildren().add(cell.getImgView());
                sp.setStyle(borderStyles);
                grid.add(sp, j, i);
            }
        }
        grid.setPrefSize(320,320);
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
