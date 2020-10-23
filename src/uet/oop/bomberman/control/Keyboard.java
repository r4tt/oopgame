package uet.oop.bomberman.control;

import com.sun.org.apache.xpath.internal.objects.XBoolean;
import jdk.jfr.internal.SecuritySupport;
import sun.jvm.hotspot.gc.shared.Space;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class Keyboard implements KeyListener {
    private boolean[] keys = new boolean[30];
    public boolean up, down, left, right; // move
    public boolean space; // put bomb

    public update() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
    }



}
