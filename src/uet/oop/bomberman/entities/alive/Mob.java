package uet.oop.bomberman.entities.alive;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.control.Keyboard;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Mob extends Entity {

    public Mob(int x, int y, Image img) {
        super( x, y, img);
    }

    public abstract void update();

    public void render(GraphicsContext gc) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        Image base = iv.snapshot(params, null);

        gc.drawImage(base, y, x);
    }

    public boolean checkmove(int xx, int yy) {
        int tmpx = x + xx;
        int tmpy = y + yy;
        int idx1, idy1, idx2, idy2;
        idx1 = tmpx / 32;
        idy1 = tmpy / 32;
        idx2 = idx1;
        idy2 = idy1;
        if (tmpx % 32 != 0) idx2++;
        if (tmpy % 32 != 0) idy2++;
        //System.out.println(tmpx + " " + tmpy + " " + idx1 + " " + idy1 + " " + idx2 +" " + idy2);
        //System.out.println(Board.check[idx1][idy1] + " " +  Board.check[idx1][idy2]);
        //System.out.println(Board.check[idx2][idy1] + " " +  Board.check[idx2][idy2]);

        if (Board.check[idx1][idy1] >= 5 || Board.check[idx1][idy2] >= 5) return false;
        if (Board.check[idx2][idy1] >= 5 || Board.check[idx2][idy2] >= 5) return false;
        return  true;
        /*if (xx == 0) {
            System.out.println("xxx");
            tmpy += yy;
            idy1 = tmpy / 32;
            idy2 = idy1;
            if (tmpy % 32 != 0) idy2++;
            //System.out.println(tmpx + " " + tmpy + " " + idx1 + " " + idy1 + " " + idx2 +" " + idy2);
            System.out.println(Board.check[idx1][idy1] + " " +  Board.check[idx2][idy2]);
            return ((Board.check[idx1][idy1] < 5) && (Board.check[idx2][idy1] < 5));
        } else {
            System.out.println("yyy");
            tmpx += xx;
            idx1 = tmpx / 32;
            idx2 = idx1 + 1;
            if (tmpx % 32 != 0) idx1++;
            return ((Board.check[idx1][idy1] < 5) && (Board.check[idx1][idy2] < 5));
        }
        //System.out.println(tmpx + " " + tmpy + " " + idx1 + " " + idy1 + " " + idx2 +" " + idy2);
        //System.out.println(Board.check[idx1][idy1] + " " +  Board.check[idx2][idy2]);
        //return ((Board.check[idx1][idy1] < 5) && (Board.check[idx2][idy2] < 5));
        */
    }


}
