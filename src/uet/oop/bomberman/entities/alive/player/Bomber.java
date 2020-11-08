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
    public static List<Bomb> bombList = new ArrayList<>();

    public Bomber(int x, int y, Image img, Sprite sprite, int speed, int maxBomb, int flame) {
        super( x, y, img, sprite);
        this.speed = speed;
        this.maxBomb = maxBomb;
        this.flame = flame;
    }

    @Override
    public void update() {
        bombList.forEach(Entity::update);
        for (int i = 0; i < bombList.size(); i++) {
            if (bombList.get(i).isExploded() == true) {
                bombList.remove(i);
                cntbomb--;
            }
        }
        if (key.space)
            putbomb();
        if (key.down || key.up || key.left || key.right)
            move();
    }

    void putbomb() {
        if (cntbomb == maxBomb) {
            return;
        }
        cntbomb++;
        int bombidx = x/32;
        int bombidy = y/32;
        Bomb bomb = new Bomb(bombidx * Sprite.SCALED_SIZE, bombidy * Sprite.SCALED_SIZE, Sprite.bomb.getFxImage(), Sprite.bomb,flame);
        bomb.render(Board.gc);
        bombList.add(bomb);
    }

    public boolean checkmovefull(int xx, int yy) {
        if (checkmove(xx, yy) == false) return false;
        for (int i = 0; i < bombList.size(); i++) {
            Bomb tmp = bombList.get(i);
            if (tmp.isExploded() == true) {
                continue;
            }
            x += xx;
            y += yy;
            boolean check = this.checkcollision(tmp);
            x -= xx;
            y -= yy;
            //System.out.println(check);
            if (check == true) {
                tmp.setInvisible(false);
            } else {
                if (tmp.isInvisible() == false)
                    return false;
            }

        }
        return true;
    }

    public void move() {
        int xx = 0;
        int yy = 0;
        if (key.down) xx = xx + 2 * speed;
        if (key.up) xx = xx - 2 * speed;
        if (key.left) yy = yy - 2 * speed;
        if (key.right) yy = yy + 2 * speed;
        if (checkmovefull(xx, yy)) {
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

