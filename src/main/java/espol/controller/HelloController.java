package espol.controller;

import espol.model.game.Pair;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label titleLabel;
    @FXML
    private Button startButton;
    @FXML
    private Button iwtbButton;
    @FXML
    private ComboBox<Character> cbx;
    @FXML
    private VBox root;
    @FXML
    private GridPane grid;
    @FXML
    private Button clearButton;

    private Character currentChar = 'n'; // Indic null
    private String backgroundStyle = "-fx-background-color: #128299;";
    private String borderStyle = "-fx-border-color: black; -fx-border-width: 2px;";
    private ArrayList<Pair> selected = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGridStyles();
        titleLabel.setText("MiniMax Game!");
        ArrayList<Character> options = new ArrayList<>();
        options.add('X'); options.add('O');
        cbx.setItems(FXCollections.observableList(options));
    }

    private void setGridStyles() {
        grid.setMinSize(340, 340);
        grid.setStyle(borderStyle);
    }

    @FXML
    protected void startGame() {
        if (currentChar.equals('n')) {
            alert("Elegir X/O", "WARNING");
            return;
        }
        for(int x = 0; x<3; x++) {
            for(int y = 0; y<3; y++) {
                grid.add(getCell(x, y), y, x);
            }
        }
    }
    private StackPane getBotChar() {
        
        return null;
    }

    @FXML
    protected void setChar() { currentChar = cbx.getValue(); }

    private StackPane getCell(Integer x, Integer y) {
        StackPane cell = new StackPane();
        cell.setOnMouseClicked(event -> {
            if (currentChar.equals('n')) alert("Choose X/O", "WARNING");
            else {
                Pair currentPosition = new Pair(x, y);
                if (!selected.contains(currentPosition)) {
                    selected.add(currentPosition);
                    try {
                        FileInputStream in = new FileInputStream("src/img/" + currentChar + ".png");
                        Image img = new Image(in);
                        ImageView imageView = getImgView();
                        imageView.setImage(img);
                        cell.getChildren().add(imageView);
                    } catch (IOException e) {
                        System.out.println(e.toString());
                    }
                }
            }
        });
        cell.setStyle(borderStyle);
        return cell;
    }

    private ImageView getImgView() {
        ImageView imgView = new ImageView();
        imgView.setFitHeight(100);
        imgView.setFitWidth(100);
        return imgView;
    }

    @FXML
    public void clearTable() {}

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