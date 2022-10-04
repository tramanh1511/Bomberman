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
     * @param x     tọa độ hàng trên map
     * @param y     tọa độ cột trên map
     * @param map   lưu map từ file level
     * @param speed tốc độ của ballon
     * @return hướng đi "thông minh hơn Balloon" của Oneal
     */
    public static int getDirection(int x, int y, int speed, char[][] map) {
        for (int i = 0; i < BombermanGame.activeObjects.size(); i++) {
            if (BombermanGame.activeObjects.get(i) instanceof Bomber) {
                Bomber bomber = (Bomber) BombermanGame.activeObjects.get(i);

                int xBomber = bomber.getY() / Sprite.SCALED_SIZE;
                int yBomber = bomber.getX() / Sprite.SCALED_SIZE;

                if (x - speed == xBomber && y == yBomber && map[x - speed][y] == ' ') {
                    return 0; // đi lên
                } else if (x + speed == yBomber && y == xBomber && map[x + speed][y] == ' ') {
                    return 1; // đi xuống
                } else if (x == yBomber && y - speed == xBomber && map[x][y - speed] == ' ') {
                    return 2; // sang trái
                } else if (x == yBomber && y + speed == xBomber && map[x][y + speed] == ' ') {
                    return 3; // sang phải
                } else {
                    return easyMove.getDirection(x, y, speed, map);
                }
            }
        }
        return easyMove.getDirection(x, y, speed, map);
    }
}
