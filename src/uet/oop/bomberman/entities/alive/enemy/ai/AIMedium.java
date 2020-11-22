package uet.oop.bomberman.entities.alive.enemy.ai;


import uet.oop.bomberman.entities.alive.enemy.Enemy;
import uet.oop.bomberman.entities.alive.player.Bomber;

public class AIMedium extends AI {
    Bomber _player;
    Enemy _e;

    public AIMedium(Bomber player, Enemy e) {
        _player = player;
        _e = e;
    }

    @Override
    public int calculateDirection() {

        if(_player == null)
            return random.nextInt(4);

        int vertical = random.nextInt(2);

        if(vertical == 1) {
            int v = calculateRowDirection();
            if(v != -1)
                return v;
            else
                return calculateColDirection();

        } else {
            int h = calculateColDirection();

            if(h != -1)
                return h;
            else
                return calculateRowDirection();
        }

    }

    protected int calculateColDirection() {

        if(_player.getX() < _e.getX())
            return 3;
        else if(_player.getX() > _e.getX())
            return 1;

        return -1;
    }

    protected int calculateRowDirection() {

        if(_player.getY() < _e.getY())
            return 0;
        else if(_player.getY() > _e.getY())
            return 2;

        return -1;
    }


}
