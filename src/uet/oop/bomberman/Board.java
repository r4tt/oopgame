package uet.oop.bomberman;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.control.Keyboard;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.entities.alive.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {

    private int _width;
    private int _height;
    private List<Entity> entityList;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    private Keyboard keyboard = new Keyboard();
    private GraphicsContext gc;
    public static Bomber bomberman;

    public static int[][] check = new int[100][100];

    Scanner scanner;


    public Board() {
        entityList = new ArrayList<>();
        stillObjects = new ArrayList<>();
    }

    void getWH(int lever) throws FileNotFoundException {
        String mapPath = "test.txt";
        scanner = new Scanner(new File("E:\\New folder (2)\\code\\JV\\bomberman-starter-starter-2\\res\\levels\\Level1.txt"));
        int n = 0;
        n = scanner.nextInt();
        _height = scanner.nextInt();
        _width = scanner.nextInt();
        scanner.nextLine();
    }


    void createMap() {
        gc = BombermanGame.ca.getGraphicsContext2D();
        for (int i = 0; i < _height; i++) {
            String s = scanner.nextLine();
            for (int j = 0; j < _width; j++) {
                Entity object = new Grass(i * 32, j * 32, Sprite.grass.getFxImage());
                char c = s.charAt(j);
                if (c == '#') {
                    object  = new Wall(i, j, Sprite.wall.getFxImage());
                    stillObjects.add(object);
                    check[i][j] = 5;
                    continue;
                }
                if (c == '*') {
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                    stillObjects.add(object);
                    check[i][j] = 6;
                    continue;
                }
                if (c == 'x') {
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                    stillObjects.add(object);
                    check[i][j] = 10;
                    continue;
                }
                if (c == 's') {
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                    stillObjects.add(object);
                    check[i][j] = 7;
                    continue;
                }
                if (c == 'f') {
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                    stillObjects.add(object);
                    check[i][j] = 8;
                    continue;
                }
                if (c == 'b') {
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                    stillObjects.add(object);
                    check[i][j] = 9;
                    continue;
                }

                //System.out.println("ss");
                //object.render(gc);
                stillObjects.add(object);
                //System.out.print(c);
            }
            //System.out.println();
        }
        scanner.close();

    }

    public void setup() {
        for (int i = 0; i < 50; i++)
            for (int j = 0; j < 50; j++)
                check[i][j] = 0;
        bomberman = new Bomber(32, 32, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        keyboard = new Keyboard();
    }

    public void update() {
        bomberman.setKey(keyboard);
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, BombermanGame.ca.getWidth(), BombermanGame.ca.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        //entities.forEach(g -> g.render(gc));
        //Entity ss =  new Bomber(32 * 2, 32, Sprite.player_right.getFxImage());
        //ss.render(gc);
    }

    public void renderEntity() {
        for (int i = 0; i < entities.size(); i++) {
            Entity tmp = entities.get(i);
            Entity delTrace = new Grass(tmp.getX(), tmp.getY(), Sprite.grass.getFxImage());
            delTrace.render(gc);
        }
        update();
        entities.forEach(g -> g.render(gc));

    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public int getWidth() {
        return _width;
    }

    public void setWidth(int _width) {
        this._width = _width;
    }

    public int getHeight() {
        return _height;
    }

    public void setHeight(int _height) {
        this._height = _height;
    }
}
