package uet.oop.bomberman.entities.alive;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.control.Keyboard;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Mob {

    protected Keyboard key = new Keyboard();
    protected boolean isMoved;


    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }



    public Keyboard getKey() {
        return key;
    }

    public void setKey(Keyboard key) {
        this.key = key;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    /*
            Update
     */
    @Override
    public void update() {
        move();
    }

    public void move() {
        int xx = 0;
        int yy = 0;
        if (key.down) xx = xx + 4;
        if (key.up) xx = xx - 4;
        if (key.left) yy = yy - 4;
        if (key.right) yy = yy + 4;
        if (checkmove(xx, yy)) {
            x += xx;
            y += yy;
            isMoved = true;
        }

        //System.out.println("check udmove " + x + " "+ y + " " + xx +" " + yy);

    }


}

