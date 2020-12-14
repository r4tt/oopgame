package uet.oop.bomberman.entities.alive.player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.Keyboard;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.alive.Mob;
import uet.oop.bomberman.entities.alive.player.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.SimpleAudioPlayer;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class Bomber extends Mob {
    protected Keyboard key = new Keyboard();
    protected boolean isMoved;
    protected int speed;
    protected int maxBomb;
    protected int flame;
    protected int cntbomb = 0;
    protected int direction = 1;
    public static List<Bomb> bombList = new ArrayList<>();

    public Bomber(int x, int y, Image img, Sprite sprite, int speed, int maxBomb, int flame) {
        super( x, y, img, sprite);
        this.speed = speed;
        this.maxBomb = maxBomb;
        this.flame = flame;
    }

    public Bomber(int x, int y, Image img, Sprite sprite) {
        super( x, y, img, sprite);
    }


    /*
    |--------------------------------------------------------------------------
    | Update and Render
    |--------------------------------------------------------------------------
     */

    @Override
    public void update() {
        if (alive == false) {
            time--;
        }
        isMoved = false;
        bombList.forEach(Entity::update);
        for (int i = 0; i < bombList.size(); i++) {
            if (bombList.get(i).isExploded() == true) {
                bombList.remove(i);
                cntbomb--;
            }
        }
        if (key.space)  putbomb();
        if (key.down || key.up || key.left || key.right) {
            if (key.right) direction = 1;
            if (key.left) direction = 2;
            if (key.up) direction = 3;
            if (key.down) direction = 4;
            move();
        }
        powerup();
    }

    public void render(GraphicsContext gc) {
        if (alive == false) {
            time--;
            sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 60);
        } else chooseSprite();
        animate();
        gc.drawImage(sprite.getFxImage(), y, x);
    }

    /*
    |--------------------------------------------------------------------------
    | Putbomb
    |--------------------------------------------------------------------------
     */

    void putbomb() {
        int bombidx = (x + sprite.get_realHeight() - 1) / 32;
        int bombidy = (y + sprite.get_realWidth() - 1) / 32;
        for (int i = 0; i < bombList.size(); i++) {
            Entity tmp = bombList.get(i);
            if (tmp.getY() == bombidy * 32 && tmp.getX() == bombidx * 32)
                return;
        }

        if (cntbomb == maxBomb) {
            return;
          }
        cntbomb++;
        SimpleAudioPlayer.open("BOM_SET.wav", 1);
        Bomb bomb = new Bomb(bombidx * Sprite.SCALED_SIZE, bombidy * Sprite.SCALED_SIZE, Sprite.bomb.getFxImage(), Sprite.bomb,flame);
        bomb.render(Board.gc);
        bombList.add(bomb);
    }

    /*
    |--------------------------------------------------------------------------
    | Checkmove and Move
    |--------------------------------------------------------------------------
     */

    public boolean checkmovefull(int xx, int yy) {
        x += 2;
        if (checkmove(xx, yy) == false) {
            x -= 2;
            return false;
        }
        x -= 2;

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
        if (alive == false) return;
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
        } else {
            isMoved = false;
        }

    }

    /*
    |--------------------------------------------------------------------------
    | Powerup
    |--------------------------------------------------------------------------
     */

    private void powerup() {
        int tmpx = (x + sprite.get_realHeight() - 1) / 32;
        int tmpy = (y + sprite.get_realWidth() - 1) / 32;
        int gt = Board.check[tmpx][tmpy];
        if (gt != 0) {
            if (gt == 1) incSpeed();
            if (gt == 2) incFlame();
            if (gt == 3) incBomb();
            //Board.check[tmpx][tmpy] = 0;
            BombermanGame.board.updateItem(tmpx * 32, tmpy * 32);
        }


    }

    private void incBomb() {
        maxBomb++;
    }

    private void incFlame() {
        flame++;
    }

    private void incSpeed() {
        speed *= 2;
    }

    /*
    |--------------------------------------------------------------------------
    | Mob Sprite
    |--------------------------------------------------------------------------
     */

    private void chooseSprite() {
        if (direction == 3) {
                sprite = Sprite.player_up;
                if(isMoved) {
                    sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                }
        }
        if (direction == 1) {
            sprite = Sprite.player_right;
            if (isMoved) {
                sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
            }
        }
        if (direction == 4) {
                sprite = Sprite.player_down;
                if(isMoved) {
                    sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                }
        }
        if (direction == 2) {
                sprite = Sprite.player_left;
                if(isMoved) {
                    sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                }
        }
    }


    /*
    |--------------------------------------------------------------------------
    | getter setter
    |--------------------------------------------------------------------------
     */

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

