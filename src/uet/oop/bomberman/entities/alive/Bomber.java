package uet.oop.bomberman.entities.alive;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.control.Keyboard;
import uet.oop.bomberman.entities.Entity;

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

    public boolean checkmove(double x, double y) {
        return  true;
    }

    public void move() {
        double xx = 0;
        double yy = 0;
        if (key.down) yy++;
        if (key.up) yy--;
        if (key.left) xx--;
        if (key.right) xx++;

        if (checkmove(0, yy)) {
            y += yy * 4;
            isMoved = true;
        }
        if (checkmove(xx, 0)) {
            x += xx * 4;
            isMoved = true;
        }
        if(xx != 0 || yy != 0) {
            System.out.println("check udmove " + x + " "+ y + " " + xx +" " + yy);
        }
        //System.out.println("check udmove " + x + " "+ y + " " + xx +" " + yy);

    }


}

