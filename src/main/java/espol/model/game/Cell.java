package espol.model.game;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Cell extends Node {
    private Character c;
    private Pair position;
    private boolean selected;
    private ImageView imgView;

    public Cell(Character option, Pair position, boolean selected) {
        this.c = option;
        this.position = position;
        this.selected = selected;
        this.imgView = createImgView();
    }
    private ImageView createImgView() {
        ImageView imgView = new ImageView();
        imgView.setFitHeight(100);
        imgView.setFitWidth(100);
        return imgView;
    }
    public Character getC() { return c; }
    public void setC(Character c) { this.c = c; }
    public Pair getPosition() { return position; }
    public void setPosition(Pair position) { this.position = position; }
    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
    public void setImgView(ImageView imgView) { this.imgView = imgView; }
    public ImageView getImgView() { return imgView; }
}
