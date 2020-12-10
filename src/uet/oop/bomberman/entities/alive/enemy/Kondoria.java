package uet.oop.bomberman.entities.alive.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.alive.enemy.ai.AILow;
import uet.oop.bomberman.entities.alive.player.Bomber;
import uet.oop.bomberman.entities.alive.player.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Kondoria extends Enemy {


    public Kondoria(int x, int y, Image img, Sprite sprite) {
        super(x, y, img, sprite);
        ai = new AILow();
    }


    public boolean checkmovefull(int xx, int yy) {
        int tmpx = x + xx;
        int tmpy = y + yy;
        if (tmpx < 32 || tmpy < 32) return false;
        int maxy = Sprite.SCALED_SIZE * BombermanGame.board.getWidth() - 32;
        int maxx = Sprite.SCALED_SIZE * BombermanGame.board.getHeight() - 32;
        tmpx = tmpx + sprite.get_realHeight() * 2 - 1;
        tmpy = tmpy + sprite.get_realWidth() * 2 - 1;
        if (tmpx > maxx || tmpy > maxy) return false;
        return true;
    }

    /*
	|--------------------------------------------------------------------------
	| Mob Sprite
	|--------------------------------------------------------------------------
	 */

    protected void chooseSprite() {
        switch(direction) {
            case 0:
            case 1:
                sprite = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, _animate, 60);
                break;
            case 2:
            case 3:
                sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, _animate, 60);
                break;
        }
    }

    @Override
    public boolean equals(Object e) {
        return false;
    }

}

















