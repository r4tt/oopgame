package uet.oop.bomberman.entities.alive.enemy;
import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.alive.Mob;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.alive.enemy.ai.AI;
import uet.oop.bomberman.entities.alive.player.Bomber;
import uet.oop.bomberman.entities.alive.player.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Random;

public abstract class Enemy extends Mob {

    protected AI ai;
    protected int direction = 1;
    protected boolean isMove = true;
    protected int speed = 1;


    protected final double MAX_STEPS;
    protected final double rest;
    protected double _steps;


    public Enemy(int x, int y, Image img, Sprite sprite) {
        super( x, y, img, sprite);
        MAX_STEPS = 32 / speed;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
        _steps = MAX_STEPS;
    }

    public abstract boolean equals(Object e);

	/*
	|--------------------------------------------------------------------------
	| Mob Move
	|--------------------------------------------------------------------------
	 */

    public void calculateMove() {
        int xx = 0;
        int yy = 0;
        if(_steps <= 0){
            direction = ai.calculateDirection();
            _steps = MAX_STEPS;
        }
        if(direction == 0) yy--;
        if(direction == 2) yy++;
        if(direction == 3) xx--;
        if(direction == 1) xx++;

        boolean check = checkmovefull(xx, yy);
        if (check == true) {
            if (alive == true) {
                x += xx;
                y += yy;
                _steps -= 1 + rest;
                isMove = true;
            }
            else {

            }
        } else{
            _steps = 0;
            isMove = false;

        }
    }

    public boolean checkmovefull(int xx, int yy) {
        if (checkmove(xx, yy) == false) return false;
        for (int i = 0; i < Bomber.bombList.size(); i++) {
            Bomb tmp = Bomber.bombList.get(i);
            if (tmp.isExploded() == true) {
                continue;
            }
            x += xx;
            y += yy;
            boolean check = this.checkcollision(tmp);
            x -= xx;
            y -= yy;
            //System.out.println(check);
            if (check == false) return false;

        }
        return true;
    }

    /*
	|--------------------------------------------------------------------------
	| Mob Render & Update
	|--------------------------------------------------------------------------
	 */
    public void update() {
        //System.out.println("ss");

        animate();
        if (alive == false) {
            return;
        }
        calculateMove();
    }
 
    public void render(GraphicsContext gc) {
        chooseSprite();
        gc.drawImage(sprite.getFxImage(), y, x);
    }

    protected abstract void chooseSprite();
}
    