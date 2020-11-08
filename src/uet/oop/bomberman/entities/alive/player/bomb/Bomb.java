package uet.oop.bomberman.entities.alive.player.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.alive.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Objects;

public class Bomb extends Entity {

    protected int timeToExplode = 120;
    public int timeAfterExplode = 20;
    protected boolean exploded = false;
    protected boolean invisible = true;
    int flame;

    public Bomb(int x, int y, Image img, Sprite sprite, int flame) {
        super(x, y, img, sprite);
        this.flame = flame;
    }

    @Override
    public void update() {
        //if (exploded == true) return;
        if (timeToExplode > 0) {
            timeToExplode--;
        } else {
            if (timeAfterExplode > 0) {
                if (exploded == false) {
                    exploded = true;
                }
                timeAfterExplode--;
            }
            else {
                /*System.out.println("tessttsst");
                for (int i = 0; i < Bomber.bombList.size(); i++) {
                    if (this.equals(Bomber.bombList.get(i))) {
                        Bomber.bombList.remove(i);
                        System.out.println("ss");
                    }
                }*/
                //remove();
                if (exploded == true) return;
            }
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