package uet.oop.bomberman;

import uet.oop.bomberman.control.Keyboard;

import java.awt.*;

public class Game extends Canvas {

    /*
    |--------------------------------------------------------------------------
    | Options & Configs
    |--------------------------------------------------------------------------
     */
    public static final double VERSION = 1.9;

    public static final int TILES_SIZE = 16,
            WIDTH = TILES_SIZE * (int)(31 / 2), //minus one to ajust the window,
            HEIGHT = 13 * TILES_SIZE;

    public static int SCALE = 3;

    public static final String TITLE = "Bomberman " + VERSION;

    //initial configs
    private static final int BOMBRATE = 1;
    private static final int BOMBRADIUS = 1;
    private static final double PLAYERSPEED = 1.0;

    public static final int TIME = 200;
    public static final int POINTS = 0;
    public static final int LIVES = 3;

    protected static int SCREENDELAY = 3;


    //can be modified with bonus
    protected static int bombRate = BOMBRATE;
    protected static int bombRadius = BOMBRADIUS;
    protected static double playerSpeed = PLAYERSPEED;


    //Time in the level screen in seconds
    protected int _screenDelay = SCREENDELAY;

    private Keyboard _input;
    private boolean _running = false;
    private boolean _paused = true;



}
