package uet.oop.bomberman.entities.activeObject.Character.moveEnemy;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Onel đuổi theo hướng của Bomber.
 */
public class mediumMove {
    /**
     * Duyệt theo 4 hướng để tìm bomber -> oneal đuổi theo.
     * @param x   tọa độ hàng trên map
     * @param y   tọa độ cột trên map
     * @param map lưu map từ file level
     */
    public static int getDirection(int x, int y, int speed, char[][] map) {
        for (int i = 0; i < BombermanGame.activeObjects.size(); i++) {
            // Tìm vị trí của bomber
            if (BombermanGame.activeObjects.get(i) instanceof Bomber) {
                Bomber bomber = (Bomber) BombermanGame.activeObjects.get(i);
                int xBomber = bomber.getYMap();
                int yBomber = bomber.getXMap();

                if (x - speed == xBomber && y == yBomber) {
                    return 0; // up
                } else if (x + speed == xBomber && y == yBomber) {
                    return 1; // down
                } else if (x == xBomber && y - speed == yBomber) {
                    return speed; // left
                } else if (x == xBomber && y + speed == yBomber) {
                    return 3; // right
                }
            }
        }
        return easyMove.getDirection(x, y, map);
    }
}
