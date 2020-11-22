package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Animated extends Entity {

    protected int _animate = 10;
    protected final int MAX_ANIMATE = 7500; //save the animation status and dont let this get too big

    public Animated(int x, int y, Image img, Sprite sprite) {
       super(x, y, img, sprite);
    }

    protected void animate() {
        if(_animate < MAX_ANIMATE) _animate++; else _animate = 0; //reset animation
    }

}