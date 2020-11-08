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
import uet.oop.bomberman.entities.alive.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application  {

    public static final boolean _running = true;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private Keyboard keyboard = new Keyboard();

    public static Bomber bomber;
    public static int maxBomb = 1;
    public static int speed = 1;
    public static int flame = 1;

    //public static GraphicsContext test;
    public static Canvas ca;

    public static void main(String[] args) {
    //    System.out.println("begin game");
        Application.launch(BombermanGame.class);

    }

    public void start(Stage stage) {
        bomber = new Bomber(32, 32, Sprite.player_right.getFxImage(), Sprite.player_right, 1, 1, 1);

        Board board = new Board();

        try {
            board.getWH(1);
        }  catch(FileNotFoundException e) {
            System.out.println("assss");
        }
        board.setup();
        ca = new Canvas(Sprite.SCALED_SIZE * board.getWidth(), Sprite.SCALED_SIZE * board.getHeight());
        board.createMap();
        board.renderall();
        //board.renderEntity();

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
                    board.setKeyboard(keyboard);
                    bomber.setKey(keyboard);
                    //board.renderEntity();
                    //board.update();
                    //board.renderall();
                });
        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    keyboard.put(code, false);
                    bomber.setKey(keyboard);
                    board.setKeyboard(keyboard);
                });


        long bg = System.nanoTime();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                /*long cr = System.nanoTime();
                if (cr - bg == 60) {
                 //   System.out.println(cr - bg);
                    board.update();
                    board.renderall();
                }
                cr = bg;
                */

                board.update();
                board.renderall();
                //System.out.println("ss");
            }
        };
        timer.start();



    }

}
