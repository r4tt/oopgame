package uet.oop.bomberman.entities.alive;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.control.Keyboard;
import uet.oop.bomberman.entities.Animated;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Mob extends Animated {

    public Mob(int x, int y, Image img, Sprite sprite) {
        super( x, y, img, sprite);
    }

    public abstract void update();
    public abstract boolean equals(Object e);

    public void render(GraphicsContext gc) {
        gc.drawImage(img, y, x);
    }

    public boolean checkmove(int xx, int yy) {
        int tmpx = x + xx;
        int tmpy = y + yy;
        int idx1, idy1, idx2, idy2;
        idx1 = tmpx / 32;
        idy1 = tmpy / 32;
        tmpx = tmpx + sprite.get_realHeight() * 2 - 1;
        tmpy = tmpy + sprite.get_realWidth() * 2 - 1;
        idx2 = tmpx / 32;
        idy2 = tmpy / 32;
        //System.out.println(tmpx +" "+ tmpy);
        if (Board.check[idx1][idy1] >= 5 || Board.check[idx1][idy2] >= 5) return false;
        if (Board.check[idx2][idy1] >= 5 || Board.check[idx2][idy2] >= 5) return false;
        return  true;
    }

    public boolean checkcollision(Entity e) {
        int a = e.getX();
        int b = e.getY();
        Sprite spr= e.getSprite();
        int c = a + spr.get_realHeight() * 2 - 1;
        int d = b + spr.get_realWidth() * 2 - 1;
        return checkhcn(a, b, c, d);
    }

}
