package espol.startGame;

import espol.model.game.Game;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        scene = new Scene(fxmlLoader.load(), 280, 215);
        stage.setTitle("Hello!");
        stage.getIcons().add(new Image(new FileInputStream("src/img/icon.png")));
        stage.setScene(scene);
        stage.show();
    }
    public static void setRoot(String fxml) throws IOException { scene.setRoot(loadFXML(fxml)); }
    public static void setRoot(FXMLLoader fxml) throws IOException { scene.setRoot(fxml.load()); }
    private static Parent loadFXML(String fxml) throws IOException {
        return (new FXMLLoader(App.class.getResource(fxml +  ".fxml"))).load();
    }
    public static FXMLLoader loadFXMLLoad(String fxml) throws IOException {
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }
    public static void main(String[] args) {
        launch();
    }
}