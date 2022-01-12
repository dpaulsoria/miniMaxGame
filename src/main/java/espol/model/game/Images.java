package espol.model.game;

import javafx.scene.image.Image;

import java.io.FileInputStream;

public class Images {
    public static Image X;
    public static Image O;
    public static Image EMPTY;

    public Images() {
        try {
            X = new Image(new FileInputStream("src/img/X.png"));
            O = new Image(new FileInputStream("src/img/O.png"));
            EMPTY = new Image(new FileInputStream("src/img/circle.png"));
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
