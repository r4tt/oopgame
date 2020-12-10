package uet.oop.bomberman.entities.alive.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.alive.enemy.ai.AIBfs;
import uet.oop.bomberman.entities.alive.enemy.ai.AILow;
import uet.oop.bomberman.entities.alive.enemy.ai.AIMedium;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Doll extends Enemy {

    public Doll(int x, int y, Image img, Sprite sprite) {
        super( x, y, img, sprite);
        ai = new AIBfs(BombermanGame.bomber, this);
        direction = 1;
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
                    sprite = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, _animate, 60);
                else
                    sprite = Sprite.doll_left1;
                break;
            case 2:
            case 3:
                if(isMove)
                    sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, _animate, 60);
                else
                    sprite = Sprite.doll_left1;
                break;
        }
    }


    @Override
    public boolean equals(Object e) {
        return false;
    }
}
