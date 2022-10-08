package uet.oop.bomberman.entities.activeObject.Character.moveEnemy;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Onel đuổi theo hướng của Bomber.
 */
public class mediumMove {
    /**
     * Duyệt theo 4 hướng để tìm của bomber -> oneal đuổi theo.
     *
     * @param x   tọa độ hàng trên map
     * @param y   tọa độ cột trên map
     * @param map lưu map từ file level
     */
    public static int getDirection(int x, int y, char[][] map) {
        for (int i = 0; i < BombermanGame.activeObjects.size(); i++) {
            if (BombermanGame.activeObjects.get(i) instanceof Bomber) {
                Bomber bomber = (Bomber) BombermanGame.activeObjects.get(i);

                int xBomber = bomber.getY() / Sprite.SCALED_SIZE;
                int yBomber = bomber.getX() / Sprite.SCALED_SIZE;

                if (x - 2 == xBomber && y == yBomber) {
                    return 0; // up
                } else if (x + 2 == xBomber && y == yBomber) {
                    return 1; // down
                } else if (x == xBomber && y - 2 == yBomber) {
                    return 2; // left
                } else if (x == xBomber && y + 2 == yBomber) {
                    return 3; // right
                } else {
                    return easyMove.getDirection(x, y, map);
                }
            }
        }
        return easyMove.getDirection(x, y, map);
    }
}
