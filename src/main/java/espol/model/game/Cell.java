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

    public Cell(Character option, Pair position, boolean selected) {
        setImageChar(option);
        setImage(charImage);
        this.c = option;
        this.position = position;
        this.selected = selected;
        this.setFitHeight(110);
        this.setFitWidth(110);
    }

    private void setImageChar(Character option) {
        try {
            charImage = new Image(new FileInputStream("src/img/" + option + ".png"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
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
