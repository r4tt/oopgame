package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    protected int x;
    protected int y;
    protected boolean alive = true;
    protected Sprite sprite;
    protected Image img;

    public Entity( int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public Entity( int x, int y, Image img, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.sprite = sprite;
    }

    public boolean checkhcn(int a, int b, int c, int d) {
        int z = x + sprite.get_realHeight() * 2 - 1;
        int t = y + sprite.get_realWidth() * 2 - 1;

        double x1 = x + 0.1;
        double y1 = y + 0.1;
        double z1 = z - 0.1;
        double t1 = t - 0.1;

        double x2 = a;
        double y2 = b;
        double z2 = c;
        double t2 = d;

        if (z1 >= x2 && z2 >= x1 && t1 >= y2 && t2 >= y1) return false;
        return true;
    }

    public void render(GraphicsContext gc) {
        /*SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        Image base = iv.snapshot(params, null);
        gc.drawImage(base, y * Sprite.SCALED_SIZE, x * Sprite.SCALED_SIZE);*/
        gc.drawImage(img, y, x);
    }

    public abstract void update();
    public abstract boolean equals(Object e);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}