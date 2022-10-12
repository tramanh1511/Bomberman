package uet.oop.bomberman.entities.activeObject.Character.moveEnemy;

import uet.oop.bomberman.BombermanGame;

import java.util.Random;

/**
 * Random hướng đi của ballon, kiểu đơn giản nhất.
 */
public class easyMove {
    /**
     * Random hướng đi của balloon
     *
     * @param x tọa độ hàng trên map
     * @param y tọa độ cột trên map
     * @return hướng random đi được
     */
    public static int getDirection(int x, int y) {
        Random random = new Random();
        int randomDirection;

        while (true) {
            randomDirection = random.nextInt(4);
            if (randomDirection == 0 && canPass(x - 1, y)) { // up
                return randomDirection;
            }
            if (randomDirection == 1 && canPass(x + 1, y)) { // down
                return randomDirection;
            }
            if (randomDirection == 3 && canPass(x, y + 1)) { // left
                return randomDirection;
            }
            if (randomDirection == 2 && canPass(x, y - 1)) { // right
                return randomDirection;
            }
        }
    }

    private static boolean canPass(int x, int y) {
        return BombermanGame.map[x][y] != '*' && BombermanGame.map[x][y] != '#'
                && BombermanGame.bombMap[x][y] == ' ';
    }
}