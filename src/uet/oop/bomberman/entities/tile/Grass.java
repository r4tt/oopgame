package uet.oop.bomberman.entities.tile;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends Entity {

    public Grass(int x, int y, Image img, Sprite sprite) {
        super(x, y, img, sprite);
    }


    public void render(GraphicsContext gc) {
        /*SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        Image base = iv.snapshot(params, null);

        gc.drawImage(base, y, x);*/
        gc.drawImage(img, y, x);
    }

    @Override
    public void update() {

    }

    public boolean equals(Object e) {
        return  false;
    }
}
