package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.control.Keyboard;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.entities.alive.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application  {

    public static final boolean _running = true;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private Keyboard keyboard = new Keyboard();

    //public static GraphicsContext test;
    public static Canvas ca;

    public static void main(String[] args) {
    //    System.out.println("begin game");
        Application.launch(BombermanGame.class);

    }

    public void start(Stage stage) {
        Board board = new Board();
        board.createlever(1);
        ca = new Canvas(Sprite.SCALED_SIZE * board.getWidth(), Sprite.SCALED_SIZE * board.getHeight());
        board.setup();
        board.render();

        // Tao root container

        Group root = new Group();
        root.getChildren().add(ca);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    keyboard.put(code, true);
                    //board.setKeyboard(keyboard);
                    board.bomberman.setKey(keyboard);
                    board.update();
                    //board.renderbomber();
                    board.render();

                    System.out.println("test keyboard");
                });
        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    keyboard.put(code, false);
                    //board.setKeyboard(keyboard);
                    //board.render();
                });

        /*int d = 1;
        long last = System.nanoTime();
        while (d <= 10) {
            long now = System.nanoTime();
            if (now - last >= 60) {
                System.out.println(last + " " + now);
                last = now;
                d++;
            }

        }*/


//        long bg = System.nanoTime();
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//                long cr = System.nanoTime();
//                System.out.println(cr - bg);
//                cr = bg;
//
//               // System.out.println("ss");
//               // board.bomberman.setKey(keyboard);
//               // board.update();
//               // board.render();
//            }
//        };
//        //timer.start();
//        //timer.handle(1);

    }

}
