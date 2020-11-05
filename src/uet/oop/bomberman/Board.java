package uet.oop.bomberman;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.control.Keyboard;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.entities.alive.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {

    private int _width;
    private int _height;
    public static List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    private Keyboard keyboard = new Keyboard();
    public static GraphicsContext gc;

    public static int[][] check = new int[100][100];

    Scanner scanner;

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
                stillObjects.add(object);
            }
        }
        scanner.close();

    }

    public void setup() {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                check[i][j] = 0;
            }
        }
        entities.clear();
        stillObjects.clear();
        System.out.println("ss1 " +BombermanGame.bomber.getMaxBomb());
        entities.add(BombermanGame.bomber);
        System.out.println("ss2 " +BombermanGame.bomber.getMaxBomb());
        keyboard = new Keyboard();
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, BombermanGame.ca.getWidth(), BombermanGame.ca.getHeight());
        stillObjects.forEach(g -> g.render(gc));
    }

    public void renderEntity() {
        for (int i = 0; i < entities.size(); i++) {
            Entity tmp = entities.get(i);
            Entity delTrace = new Grass(tmp.getX(), tmp.getY(), Sprite.grass.getFxImage());
            delTrace.render(gc);
        }

        update();
        System.out.println(entities.size());
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
