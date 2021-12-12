package espol.model.game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class Cell extends ImageView {
    private Character c;
    private Pair position;
    private boolean selected;
    private Image charImage;
    public static final Character EMPTY = 'n';
    private final double SIZE = 120 - 10;

    public Cell(Pair position, boolean selected) {
        c = EMPTY;
        setImage(charImage);
        this.position = position;
        this.selected = selected;
        this.setFitHeight(SIZE);
        this.setFitWidth(SIZE);
    }

    @Override
    public String toString() { return position.toString() + ": " + c + " - " + selected; }
    public Character getC() { return c; }
    public void setC(Character c) { this.c = c; }
    public Pair getPosition() { return position; }
    public void setPosition(Pair position) { this.position = position; }
    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
}
