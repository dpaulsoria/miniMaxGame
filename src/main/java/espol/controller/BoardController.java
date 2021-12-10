package espol.controller;

import espol.model.game.Board;
import espol.model.game.Game;
import espol.startGame.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable{
    @FXML
    private Label markLabel;
    @FXML
    private Button giveUpButton;
    @FXML
    private BorderPane root;
    @FXML
    private AnchorPane anchorPane;

    private Game gg;
    private Board board;
    private Character player = 'n';

    public void setGame(Game g) {
        gg = g;
        player = gg.getPlayer();
        markLabel.setText(String.valueOf(player));
        board = new Board(player);
        GridPane grid = board.getGrid();
        grid.setLayoutX(48);
        grid.setLayoutY(40);
        anchorPane.getChildren().add(grid);
    }
    @FXML
    protected void clearTable() { board.clear(); }
    @FXML
    protected void giveUp() {
        try {
            App.setRoot("hello-view");
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.setMinSize(500, 400);
        root.setMaxSize(500, 400);
    }
}