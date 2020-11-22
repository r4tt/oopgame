package uet.oop.bomberman.entities.alive.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.alive.enemy.ai.AILow;
import uet.oop.bomberman.entities.alive.enemy.ai.AIMedium;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Oneal extends Enemy {

    public Oneal(int x, int y, Image img, Sprite sprite) {
        super( x, y, img, sprite);
        ai = new AIMedium(BombermanGame.bomber, this);
        direction = -1;
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
                if(isMove)
                    sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, _animate, 60);
                else
                    sprite = Sprite.oneal_left1;
                break;
            case 2:
            case 3:
                if(isMove)
                    sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, _animate, 60);
                else
                    sprite = Sprite.oneal_left1;
                break;
        }
    }


    @Override
    public boolean equals(Object e) {
        return false;
    }
}
