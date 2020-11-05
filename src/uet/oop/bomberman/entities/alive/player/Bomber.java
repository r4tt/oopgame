package uet.oop.bomberman.entities.alive.player;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.control.Keyboard;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.entities.alive.Mob;
import uet.oop.bomberman.entities.alive.player.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Mob {
    protected Keyboard key = new Keyboard();
    protected boolean isMoved;
    protected int speed;
    protected int maxBomb;
    protected int flame;
    protected int cntbomb = 0;
    protected int h = 15;
    protected int w = 12;
    protected static List<Entity> bombList = new ArrayList<>();

    public Bomber(int x, int y, Image img, int speed, int maxBomb, int flame) {
        super( x, y, img);
        this.speed = speed;
        this.maxBomb = maxBomb;
        this.flame = flame;
    }

    @Override
    public void update() {
        bombList.forEach(Entity::update);
        if (key.space)
            putbomb();
        if (key.down || key.up || key.left || key.right)
            move();
    }

    void putbomb() {
        System.out.println("ss "+ cntbomb +" " + maxBomb);
        if (cntbomb == maxBomb) {
            return;
        }
        Entity bomb = new Bomb(x/32, y/32, Sprite.bomb.getFxImage() ,flame);
        bomb.render(Board.gc);
        bombList.add(bomb);
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


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMaxBomb() {
        return maxBomb;
    }

    public void setMaxBomb(int maxBomb) {
        this.maxBomb = maxBomb;
    }

    public int getFlame() {
        return flame;
    }

    public void setFlame(int flame) {
        this.flame = flame;
    }

    public boolean equals(Object e) {
        return  false;
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

    public int getCntbomb() {
        return cntbomb;
    }

    public void setCntbomb(int cntbomb) {
        this.cntbomb = cntbomb;
    }
}

