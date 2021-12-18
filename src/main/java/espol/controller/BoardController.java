package espol.controller;

import espol.model.game.Board;
import espol.model.game.Cell;
import espol.model.game.Game;
import espol.model.tda.ArrayList;
import espol.startGame.App;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import java.io.IOException;
import java.net.URL;
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
        if (!gg.isPlayerBegins()) gg.botTurn();
        printMap(board.getMap());
    }
    @FXML
    protected void newGame() {
        /*
        ObservableList<Node> list = grid.getChildren();
        list.forEach(e -> {
            System.out.println(((Cell)e).toString());
        });
        */
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
    
    public void printMap(TreeMap<Integer, ArrayList<Cell>> map){
        GridPane gp = new GridPane();
        for(int i=0; i<3;i++){
            for(int j=0; j<3;j++){
                Cell tmp = map.get(i).get(j);
                Cell tmpn = new Cell(tmp.getPosition(),true);
                tmpn.setImage(tmp.getImage());
                tmpn.setFitHeight(80);
                tmpn.setFitWidth(80);
                gp.add(tmpn, j, i);
            }
        }  
        gp.setAlignment(Pos.CENTER);
        vh.getChildren().add(gp);
    }
}