 package uet.oop.bomberman.entities.alive.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.alive.enemy.ai.AILow;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Balloon extends Enemy {


    public Balloon(int x, int y, Image img, Sprite sprite) {
        super(x, y, img, sprite);
        ai = new AILow();
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
                sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, _animate, 60);
                break;
            case 2:
            case 3:
                sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, _animate, 60);
                break;
        }
    }

    @Override
    public boolean equals(Object e) {
        return false;
    }

}

















