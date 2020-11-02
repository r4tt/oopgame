package uet.oop.bomberman;

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


    public Board() {
        entityList = new ArrayList<>();
        stillObjects = new ArrayList<>();
    }

    void createMap(int lever) throws FileNotFoundException {
        String mapPath = "test.txt";
        Scanner scanner;
        scanner = new Scanner(new File("E:\\New folder (2)\\code\\JV\\bomberman-starter-starter-2\\res\\levels\\Level1.txt"));

        int n = 0;
        n = scanner.nextInt();
        _height = scanner.nextInt();
        _width = scanner.nextInt();
        //System.out.println(n + " " + _width +" "+ _height);
        scanner.nextLine();

        for (int i = 0; i < _height; i++) {
            String s = scanner.nextLine();
            for (int j = 0; j < _width; j++) {
                Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                char c = s.charAt(j);
                if (c == '#') {
                    object  = new Wall(j, i, Sprite.wall.getFxImage());
                }
                if (c == '*') {
                    object = new Brick(j, i, Sprite.brick.getFxImage());
                }
                stillObjects.add(object);
                //System.out.print(c);
            }
            //System.out.println();
        }
        scanner.close();

    }

    void createlever(int lever) {
        try {
            createMap(lever);
        }
        catch(FileNotFoundException e) {
            System.out.println("assss");
        }
    }

    public void setup() {
        gc = BombermanGame.ca.getGraphicsContext2D();
        bomberman = new Bomber(32, 32, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        keyboard = new Keyboard();
    }

    public void update() {
        //bomberman.setKey(keyboard);
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, BombermanGame.ca.getWidth(), BombermanGame.ca.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public void renderbomber() {
        bomberman.render(gc);
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
