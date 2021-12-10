package espol.model.game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Cell {
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
    public void setCellEvents(StackPane sp) {
        sp.setOnMouseClicked(e -> {
            if (!this.isSelected()) {
                ImageView imgView = this.getImgView();
                try {
                    imgView.setImage(new Image(new FileInputStream("src/img/" + c + ".png")));
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.toString());;
                }
            }
        });
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
