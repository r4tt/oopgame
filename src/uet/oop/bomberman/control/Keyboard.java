package uet.oop.bomberman.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class Keyboard implements KeyListener {

    private boolean[] keys = new boolean[150];
    public boolean up, down, left, right, space;

    public void update() {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        space = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_X];
        //System.out.println("check ud keybroad " + up +" "+ down + " " + left +" " + right);

    }

    public void put(String s, boolean bo) {
        int id = 0;
        if ((s == "DOWN") || (s == "S")) id = KeyEvent.VK_DOWN;
        if ((s == "UP") || (s == "W")) id = KeyEvent.VK_UP;
        if ((s == "LEFT") || (s == "D")) id = KeyEvent.VK_LEFT;
        if ((s == "RIGHT") || (s == "R")) id = KeyEvent.VK_RIGHT;
        if ((s == "SPACE") || (s == "X")) id = KeyEvent.VK_SPACE;
        keys[id] = bo;
        update();
        //System.out.println(KeyEvent.VK_DOWN+ " " + id);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;

    }

}
