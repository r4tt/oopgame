package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Entity {

    public Wall(int x, int y, Image img, Sprite sprite) {
        super(x, y, img, sprite);
    }

    @Override
    public void update() {

    }

    public boolean equals(Object e) {
        return  false;
    }
}
