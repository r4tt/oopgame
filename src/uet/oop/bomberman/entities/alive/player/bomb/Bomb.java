package uet.oop.bomberman.entities.alive.player.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.alive.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Objects;

public class Bomb extends Animated {

    protected int timeToExplode = 120;
    public int timeAfterExplode = 20;
    protected boolean exploded = false;
    protected boolean invisible = true;
    int flame;

    public Bomb(int x, int y, Image img, Sprite sprite, int flame) {
        super(x, y, img, sprite);
        this.flame = flame;
    }

    public Bomb(int x, int y, Image img, Sprite sprite) {
        super(x, y, img, sprite);
    }

    @Override
    public void update() {
        //if (exploded == true) return;
        if (timeToExplode > 0) {
            timeToExplode--;
        } else {
            if (timeAfterExplode > 0) {
                timeAfterExplode--;
            }
            else {
                if (exploded == false) {
                    exploded = true;
                }
            }
        }
    }

    public void renderx(GraphicsContext gc, int gt) {
        int tmpx, tmpy;
        Image tmpimg = Sprite.explosion_vertical.getFxImage();
        tmpx = x;
        tmpy = y;
        for (int i = 1; i < flame; i++) {
            tmpx = tmpx + gt * 32;
            BombermanGame.board.updatebomb(tmpx, tmpy);
            if (Board.check[tmpx / 32][tmpy / 32] > 4) return;
            gc.drawImage(tmpimg, tmpy, tmpx);
        }
        tmpx = tmpx + gt * 32;
        BombermanGame.board.updatebomb(tmpx, tmpy);
        if (Board.check[tmpx / 32][tmpy / 32] > 4) return;
        tmpimg = Sprite.explosion_vertical_down_last.getFxImage();
        if (gt == -1) tmpimg = Sprite.explosion_vertical_top_last.getFxImage();
        gc.drawImage(tmpimg, tmpy, tmpx);
    }

    public void rendery(GraphicsContext gc, int gt) {
        int tmpx, tmpy;
        Image tmpimg = Sprite.explosion_horizontal.getFxImage();
        tmpx = x;
        tmpy = y;
        for (int i = 1; i < flame; i++) {
            tmpy = tmpy + gt * 32;
            BombermanGame.board.updatebomb(tmpx, tmpy);
            if (Board.check[tmpx / 32][tmpy / 32] > 4) return;
            gc.drawImage(tmpimg, tmpy, tmpx);
        }
        tmpy = tmpy + gt * 32;
        BombermanGame.board.updatebomb(tmpx, tmpy);
        if (Board.check[tmpx / 32][tmpy / 32] > 4) return;
        tmpimg = Sprite.explosion_horizontal_right_last.getFxImage();
        if (gt == -1) tmpimg = Sprite.explosion_horizontal_left_last.getFxImage();
        gc.drawImage(tmpimg, tmpy, tmpx);
    }

    public void render(GraphicsContext gc) {
        animate();
        if (timeToExplode == 0) {
            gc.drawImage(Sprite.bomb_exploded.getFxImage(), y, x);
            renderx(gc, -1);
            renderx(gc, 1);
            rendery(gc, 1);
            rendery(gc, -1);


        } else {
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);
            gc.drawImage(sprite.getFxImage(), y, x);

        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bomb bomb = (Bomb) o;
        return timeToExplode == bomb.timeToExplode &&
                timeAfterExplode == bomb.timeAfterExplode &&
                exploded == bomb.exploded &&
                flame == bomb.flame;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public int getFlame() {
        return flame;
    }

    public void setFlame(int flame) {
        this.flame = flame;
    }
}
