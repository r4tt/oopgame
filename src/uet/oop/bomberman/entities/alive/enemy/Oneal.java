package uet.oop.bomberman.entities.alive.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Oneal extends Enemy {

    public Oneal(int x, int y, Image img, Sprite sprite) {
        super( x, y, img, sprite);
    }


    public void render(GraphicsContext gc) {
        gc.drawImage(img, y, x);
    }

    public void update () {

    }
    @Override
    public boolean equals(Object e) {
        return false;
    }
}
