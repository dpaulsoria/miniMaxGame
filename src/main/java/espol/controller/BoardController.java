package espol.controller;

import espol.model.game.*;
import espol.startGame.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class BoardController implements Initializable {
    @FXML
    private Label markLabel;
    @FXML
    private Button giveUpButton;
    @FXML
    private BorderPane root;
    @FXML
    private GridPane grid;
    private GridPane grid1;
    private Game gg;
    private Board board;
    private Character player = 'n';
    @FXML
    private VBox vh;
    @FXML
    private Button newGameButton;

    public void setGame(Game g) {
        gg = g;
        player = gg.getPlayer();
        markLabel.setText(String.valueOf(player));
        board = new Board(player, gg);
        grid = board.getGrid();
        grid.setAlignment(Pos.CENTER);
        root.setCenter(grid);
        grid1 = board.getGrid1();
        grid1.setAlignment(Pos.CENTER);
        vh.getChildren().add(grid1);
    }
    @FXML
    protected void newGame() {
        ArrayList<TreeMap<Integer, ArrayList<Cell>>> tmp = miniMax.getNextMoves(board.getMap(), player);
        System.out.println("New Game " + tmp.size());
        for (int i = 0; i<tmp.size(); i++) {
            TreeMap<Integer, ArrayList<Cell>> map = tmp.get(i);
            map.forEach((k, v) -> {
                System.out.println("Clave: " + k);
                v.forEach((v1) -> {
                    System.out.println("    " + v1);
                });
            });
            System.out.println();
        }
    }
    protected void clearTable() { board.clear(); }
    @FXML
    protected void giveUp() {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("hello-view");
            App.switchWindowGame(fxml, App.height, App.width);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    private void alert(String msg, String mode) {
        Alert a;
        switch (mode) {
            case "ERROR": a = new Alert(Alert.AlertType.ERROR, msg); break;
            case "INFORMATION": a = new Alert(Alert.AlertType.INFORMATION, msg); break;
            case "WARNING": a = new Alert(Alert.AlertType.WARNING, msg); break;
            case "CONFIRMATION": a = new Alert(Alert.AlertType.CONFIRMATION, msg); break;
            default: a = new Alert(Alert.AlertType.NONE, msg); break;
        }
        a.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }
    
    
}