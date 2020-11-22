package uet.oop.bomberman.entities.alive.enemy;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.alive.Mob;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Enemy extends Mob {

    public Enemy(int x, int y, Image img, Sprite sprite) {
        super( x, y, img, sprite);
    }

    public abstract void update();
    public abstract boolean equals(Object e);

    /*
    public void render(GraphicsContext gc) {
        gc.drawImage(img, y, x);
    }
    */

}
