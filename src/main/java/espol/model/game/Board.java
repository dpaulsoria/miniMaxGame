package espol.model.game;

import javafx.scene.layout.GridPane;

public class Board {
    private GridPane grid;
    private Character player;
    private Character bot;
    private final String borderStyles = "-fx-border-color: black; -fx-border-width: 2px;";

    public Board(Character p) {
        player = p;
        bot = (player.equals('X') ? 'O':'X');
        grid = new GridPane();
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
