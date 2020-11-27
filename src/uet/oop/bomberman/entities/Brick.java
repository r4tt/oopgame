package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.alive.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Animated {

    int time = 30;
    public Brick(int x, int y, Image img, Sprite sprite) {
        super(x, y, img, sprite);
    }

    @Override
    public void update() {
        if (alive == true) return;
        if (time > 0) time--;
        else {
            sprite = Sprite.grass;
            img = sprite.getFxImage();
            alive = true;
        }
    }
    public void render(GraphicsContext gc) {
        if (alive == true) {
            gc.drawImage(img, y, x);
        }
        else {
            animate();
            Sprite sprite = Sprite.movingSprite(Sprite.brick_exploded,Sprite.brick_exploded1, Sprite.brick_exploded2, _animate, 60);
            gc.drawImage(sprite.getFxImage(), y, x);
        }
    }
    public boolean equals(Object e) {
        return  false;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
