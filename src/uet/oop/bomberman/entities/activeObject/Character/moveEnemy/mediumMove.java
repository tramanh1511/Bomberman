package uet.oop.bomberman.entities.activeObject.Character.moveEnemy;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Character.Bomber;
import uet.oop.bomberman.entities.activeObject.activeEntity;

import static uet.oop.bomberman.BombermanGame.activeObjects;

/**
 * Onel đuổi theo hướng của Bomber.
 * cần cải tiến, có bug
 */
public class mediumMove {
    /**
     * Duyệt theo 4 hướng để tìm bomber -> oneal đuổi theo.
     *
     * @param x tọa độ hàng trên map
     * @param y tọa độ cột trên map
     */
    public static int getDirection(int x, int y) {
        for (activeEntity entity : activeObjects) {
            if (entity instanceof Bomber) {
                Bomber bomber = (Bomber) entity;
                int xBomber = bomber.getYMap();
                int yBomber = bomber.getXMap();
                if (x - 2 == xBomber && y == yBomber && canPass(x - 1, y)) {
                    return 0; // up
                } else if (x + 2 == xBomber && y == yBomber && canPass(x + 1, y)) {
                    return 1; // down
                } else if (x == xBomber && y - 2 == yBomber && canPass(x, y - 1)) {
                    return 2; // left
                } else if (x == xBomber && y + 2 == yBomber && canPass(x, y + 1)) {
                    return 3; // right
                } else return easyMove.getDirection(x, y);
            }
        }
        return easyMove.getDirection(x, y);
    }

    private static boolean canPass(int x, int y) {
        return BombermanGame.map[x][y] != '*' && BombermanGame.map[x][y] != '#'
                && BombermanGame.bombMap[x][y] == ' ';
    }
}
