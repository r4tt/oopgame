package uet.oop.bomberman.entities.alive.enemy.ai;


import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.alive.enemy.Enemy;
import uet.oop.bomberman.entities.alive.player.Bomber;
import uet.oop.bomberman.entities.alive.player.bomb.Bomb;

import java.util.LinkedList;
import java.util.Queue;

public class AIBfs extends AI {

    public static class pair {
        public int first;
        public int second;

        public pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
        public pair() {

        }
    }

    Bomber _player;
    Enemy _e;

    public AIBfs(Bomber player, Enemy e) {
        _player = player;
        _e = e;
    }

    @Override
    public int calculateDirection() {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        int[][] F = new int[100][100];
        Queue<pair> queue = new LinkedList<>();
        int u = (_player.getX() + _player.getSprite().get_realHeight() - 1) / 32;
        int v = (_player.getY() + _player.getSprite().get_realWidth() - 1) / 32;
        int dstx = _e.getX() / 32;
        int dsty = _e.getY() / 32;
        F[u][v] = 0;
        pair ss = new pair(u, v);
        queue.add(ss);
        while (queue.size() != 0)
        {
            pair tmp = queue.element();
            queue.remove();
            if (tmp.first == dstx && tmp.second == dsty) {
                int id = 0;
                int min = 15140703;
                for (int i = 0; i < 4; i++) {
                    int x = tmp.first + dx[i];
                    int y = tmp.second + dy[i];
                    if (F[x][y] == 0) continue;
                    if (F[x][y] < min) {
                        min = F[x][y];
                        id = i;
                    }
                }
                return id;
            }
            for (int i = 0; i < 4 ; i++) {
                int x = tmp.first + dx[i];
                int y = tmp.second + dy[i];
                if (Board.check[x][y] > 4) continue;
                if (F[x][y] != 0) continue;
                if (x == u & y == v) continue;
                boolean checkbomb = true;
                for (            int j = 0; j < Bomber.bombList.size(); j++) {
                        Bomb tmpBomb = Bomber.bombList.get(j);
                        if (tmpBomb.isExploded() == true) continue;
                        if (tmpBomb.getX() / 32 == x && tmpBomb.getY() / 32 == y)
                        checkbomb = false;
                }
                if (checkbomb == false) continue;

                F[x][y] = F[tmp.first][tmp.second] + 1;
                pair tmppair = new pair(x, y);
                queue.add(tmppair);
            }
        }
        return (_e.getDirection() + 2) % 4;
    }



}
