package uet.oop.bomberman.entities.alive.player.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;

import java.awt.*;
import java.util.Objects;

public class Bomb extends Entity {

    protected int timeToExplode = 120;
    public int timeAfterExplode = 20;
    protected boolean exploded = false;

    int flame;

    public Bomb(int x, int y, Image img, int flame) {
        super(x, y, img);
        this.flame = flame;
    }

    @Override
    public void update() {
       /* if (timeToExplode > 0) {
            timeToExplode--;
        } else {
            if (timeAfterExplode > 0) {
                if (exploded == false) {
                    exploded = true;
                }
                timeAfterExplode--;
            }
            else {
                remove();
            }
        }*/
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
}
