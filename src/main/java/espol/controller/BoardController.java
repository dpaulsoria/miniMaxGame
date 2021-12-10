package espol.controller;

import espol.model.game.Board;
import espol.model.game.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController {
    @FXML
    private Label markLabel;
    @FXML
    private Button giveUpButton;
    @FXML
    private BorderPane root;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane anchorPane;

    private Game gg;
    private Board board;
    private Character player = 'n';

    public void initialize(Game g) {
        gg = g;
        player = gg.getPlayer();
        markLabel.setText(String.valueOf(player));
    }

    @FXML
    protected void clearTable() { board.clear(); }
    @FXML
    protected void giveUp() {}
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
}