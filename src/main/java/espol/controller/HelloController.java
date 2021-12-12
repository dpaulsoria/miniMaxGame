package espol.controller;

import espol.model.game.Game;
import espol.startGame.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label titleLabel;
    @FXML
    private Button startButton;
    @FXML
    private VBox root;
    @FXML
    private ImageView img;
    @FXML
    private RadioButton xRadioButton;
    @FXML
    private RadioButton oRadioButton;
    @FXML
    private RadioButton meRadioButton;
    @FXML
    private RadioButton botRadioButton;

    private Character playerMark;
    private boolean playerBegins;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            img.setImage(new Image(new FileInputStream("src/img/tic-tac-toe.png")));
        } catch(Exception e) {
            System.out.printf(e.toString());
        }
        playerMark = 'n';
        titleLabel.setText("MiniMax Game!");
        playerBegins = false;
    }
    @FXML
    protected void startGame() {
        if (!validateGame()) return;
        System.out.println("Begins: " + (playerBegins ? "Player":"Bot"));
        System.out.println("Player Mark: " + playerMark);
        Game game = new Game(playerMark, playerBegins);
        try {
            FXMLLoader fxml = App.loadFXMLLoad("board");
            App.setRoot(fxml);
            BoardController bc = fxml.getController();
            bc.setGame(game);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    private boolean validateGame() {
        return (!meRadioButton.isSelected() || !botRadioButton.isSelected() || !xRadioButton.isSelected() || !oRadioButton.isSelected());
    }
    @FXML
    protected void setMeRadioButton() {
        if (!meRadioButton.isSelected()) { meRadioButton.setSelected(true); botRadioButton.setSelected(false); }
        else if (botRadioButton.isSelected()) { botRadioButton.setSelected(false); meRadioButton.setSelected(true); }
        setPlayerBegins(true);
    }
    @FXML
    protected void setBotRadioButton() {
        if (!botRadioButton.isSelected()) { botRadioButton.setSelected(true); meRadioButton.setSelected(false); }
        else if (meRadioButton.isSelected()) { meRadioButton.setSelected(false); botRadioButton.setSelected(true); }
        setPlayerBegins(false);
    }
    @FXML
    protected void setORadioButton() {
        if (!oRadioButton.isSelected()) { oRadioButton.setSelected(true); xRadioButton.setSelected(false); }
        else if (xRadioButton.isSelected()) { xRadioButton.setSelected(false); oRadioButton.setSelected(true); }
        setPlayerMark('O');
    }
    @FXML
    protected void setXRadioButton() {
        if (!xRadioButton.isSelected()) { xRadioButton.setSelected(true); oRadioButton.setSelected(false); }
        else if (oRadioButton.isSelected()) { oRadioButton.setSelected(false); xRadioButton.setSelected(true); }
        setPlayerMark('X');
    }
    private void setPlayerMark(Character opt) { this.playerMark = opt; }
    private void setPlayerBegins(boolean opt) { this.playerBegins = opt; }
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