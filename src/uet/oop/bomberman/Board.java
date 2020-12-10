package uet.oop.bomberman;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.control.Keyboard;
import uet.oop.bomberman.entities.alive.enemy.Doll;
import uet.oop.bomberman.entities.alive.enemy.Kondoria;
import uet.oop.bomberman.entities.tile.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.tile.Gate;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.alive.enemy.Balloon;
import uet.oop.bomberman.entities.alive.enemy.Oneal;
import uet.oop.bomberman.entities.alive.player.Bomber;
import uet.oop.bomberman.entities.tile.powerup.PowerupBombs;
import uet.oop.bomberman.entities.tile.powerup.PowerupFlames;
import uet.oop.bomberman.entities.tile.powerup.PowerupSpeed;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;

public class Board {

    private int _width;
    private int _height;
    public static List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private List<Entity> backGround = new ArrayList<>();
    public static List<Entity> items = new ArrayList<>();
    public boolean checkwin = false;
    private Keyboard keyboard = new Keyboard();
    public static GraphicsContext gc;

    public static int[][] check = new int[100][100];

    Scanner scanner;

    void getWH(int lever) throws FileNotFoundException {
        String mapPath = "res/levels/Level" + lever + ".txt";
        scanner = new Scanner(new File(mapPath));
        int n = 0;
        n = scanner.nextInt();
        _height = scanner.nextInt();
        _width = scanner.nextInt();
        scanner.nextLine();
        //scanner.close();
    }


    void createMap() {
        gc = BombermanGame.ca.getGraphicsContext2D();
        for (int i = 0; i < _height; i++) {
            String s = scanner.nextLine();
            for (int j = 0; j < _width; j++) {
                Entity object = new Grass(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.grass.getFxImage(), Sprite.grass);
                backGround.add(object);
                char c = s.charAt(j);
                if (c == '#') {
                    object  = new Wall(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.wall.getFxImage(), Sprite.wall);
                    backGround.add(object);
                    check[i][j] = 5;
                    continue;
                }
                if (c == '*') {
                    object = new Brick(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.brick.getFxImage(), Sprite.brick);
                    stillObjects.add(object);
                    check[i][j] = 6;
                    continue;
                }
                if (c == 'x') {
                    object = new Brick(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.brick.getFxImage(), Sprite.brick);
                    stillObjects.add(object);
                    check[i][j] = 10;
                    continue;
                }
                if (c == 's') {
                    object = new Brick(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.brick.getFxImage(), Sprite.brick);
                    stillObjects.add(object);
                    check[i][j] = 7;
                    continue;
                }
                if (c == 'f') {
                    object = new Brick(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.brick.getFxImage(), Sprite.brick);
                    stillObjects.add(object);
                    check[i][j] = 8;
                    continue;
                }
                if (c == 'b') {
                    object = new Brick(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.brick.getFxImage(), Sprite.brick);
                    stillObjects.add(object);
                    check[i][j] = 9;
                    continue;
                }
                if (c == '1') {
                    object = new Balloon(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.balloom_left1.getFxImage(), Sprite.balloom_left1);
                    entities.add(object);
                    continue;
                }
                if (c == '2') {
                    object = new Oneal(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.oneal_left1.getFxImage(), Sprite.oneal_left1);
                    entities.add(object);
                    continue;
                }
                if (c == '3') {
                    object = new Kondoria(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.kondoria_left1.getFxImage(), Sprite.kondoria_left1);
                    entities.add(object);
                    continue;
                }
                if (c == '4') {
                    object = new Doll(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.doll_left1.getFxImage(), Sprite.doll_left1);
                    entities.add(object);
                    continue;
                }
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
        checkwin = false;
        entities.clear();
        stillObjects.clear();
        items.clear();
        entities.add(BombermanGame.bomber);
        keyboard = new Keyboard();
    }


    public void updatebomb(int x, int y) {
        if (check[x / 32][y / 32] > 5) {
            updateObjects(x , y);
        }
        updatemod(x, y);
    }

    public void updatemod(int x, int y) {
        for (int i = 0; i < entities.size(); i++) {
            Entity tmp = entities.get(i);
            if (tmp.isAlive() == false) continue;
            if (tmp.checkhcn(x, y, x + 31, y + 31) == false)
                tmp.setAlive(false);
        }
    }

    public void updateItem(int x, int y) {
        if (check[x / 32][y / 32] == 4) {
            if (entities.size() == 1)
                checkwin = true;
            return;
        }
        check[x / 32][y / 32] = 0;
        for (int i = 0; i < items.size(); i++) {
            Entity tmp = items.get(i);
            if (tmp.getX() == x && tmp.getY() == y)
                items.remove(i);
        }
    }

    public void updateObjects(int x, int y) {
        for (int i = 0; i < stillObjects.size(); i++) {
            Entity tmp = stillObjects.get(i);
            if (tmp.getX() == x && tmp.getY() == y && tmp.isAlive() == true) {
                tmp.setAlive(false);
                break;
            }
        }
    }

    public void update() {
        if (checkwin == true) {
            return;
        }
        for (int i = 0; i < entities.size(); i++) {
            Entity tmp = entities.get(i);
            tmp.update();
            if (tmp.getTime() == 0 && i != 0) {
                entities.remove(i);
            }
        }
        for (int i = 0; i < stillObjects.size(); i++) {
            Brick tmp = (Brick) stillObjects.get(i);
            tmp.update();
            if (tmp.getTime() == 0) {
                int x = tmp.getX();
                int y = tmp.getY();
                check[x / 32][y / 32] -= 6;
                if (check[x / 32][y / 32] != 0) {
                    Entity Object = new Gate(x, y, Sprite.portal.getFxImage());
                    switch (check[x / 32][y / 32]) {
                        case 1:
                            Object = new PowerupSpeed(x, y, Sprite.powerup_speed.getFxImage());
                            break;
                        case 2:
                            Object = new PowerupFlames(x, y, Sprite.powerup_flames.getFxImage());
                            break;
                        case 3:
                            Object = new PowerupBombs(x, y, Sprite.powerup_bombs.getFxImage());
                            break;
                        case 4:
                            Object = new Gate(x, y, Sprite.portal.getFxImage());
                            break;
                        default:
                            break;
                    }
                    items.add(Object);
                }
                stillObjects.remove(i);
            }
        }
        if (BombermanGame.bomber.getTime() <= 0) {
            createNewBomber();
        }
    }
    public void createNewBomber() {
        BombermanGame.bomber.setX(32);
        BombermanGame.bomber.setY(32);
        BombermanGame.bomber.setTime(30);
        BombermanGame.bomber.setAlive(true);
    }

    public void renderall() {
        if (checkwin == true) {
            BombermanGame.win = true;
            return;
        }
        gc.clearRect(0, 0, BombermanGame.ca.getWidth(), BombermanGame.ca.getHeight());

        backGround.forEach(g -> g.render(gc));
        stillObjects.forEach(g -> g.render(gc));
        items.forEach(g -> g.render(gc));
        Bomber.bombList.forEach(g -> g.render(gc));
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
