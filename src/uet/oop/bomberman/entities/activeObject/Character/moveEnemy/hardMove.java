package uet.oop.bomberman.entities.activeObject.Character.moveEnemy;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Character.Bomber;
import uet.oop.bomberman.entities.activeObject.Character.moveEnemy.AStar.AStar;
import uet.oop.bomberman.entities.activeObject.Character.moveEnemy.AStar.Node;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

/**
 * đuổi theo Bomber.
 */
public class hardMove {
    /**
     * tim duong di tranh chuong ngai vat ngan nhat tu Enemy den Bomber theo thuat toan A*
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

                AStar aStar = new AStar(map, x, y, xBomber, yBomber);
                List<Node> resultPath = aStar.aStarSearch(); // duong di luu cac node tu vi tri Bomber den Enemy
                if (resultPath != null) {
                    Node nextNode = resultPath.get(resultPath.size() - 2); //  Node tai vi tri truoc enemy trong duong di
                                                                           // chinh la buoc tiep Enemy chon di
                    if (x - 1 == nextNode.getRow()) {
                        return 0; // up
                    } else if (x + 1 == nextNode.getRow()) {
                        return 1; // down
                    } else if (y - 1 == nextNode.getCol()) {
                        return 2; // left
                    } else if (y + 1 == nextNode.getCol()) {
                        return 3; // right
                    } else {
                        return mediumMove.getDirection(x, y, map);
                    }
                }
            }
        }
        return mediumMove.getDirection(x, y, map);
=======
/**
 * Thuật toán di chuyển đường đi cho enemy thông minh nhất :) tìm mọi cách kill bomber.
 */

public class hardMove {
    /**
     * Ứng dụng thuật toán tìm đường A*.
     * @param x tọa độ hàng trên map
     * @param y tọa độ cột trên map
     * @param map tọa độ map
     * @return đường đi đuổi theo bomber
     */
    public static int getDirection(int x, int y, char[][] map) {

        return 1;
    }
}
