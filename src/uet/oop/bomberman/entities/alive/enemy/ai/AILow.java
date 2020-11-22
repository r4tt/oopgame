package uet.oop.bomberman.entities.alive.enemy.ai;

public class AILow extends AI {

    @Override
    public int calculateDirection() {
        int res = random.nextInt(4);
        return res;
    }

}
