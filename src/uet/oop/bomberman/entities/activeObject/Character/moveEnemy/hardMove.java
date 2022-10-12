package uet.oop.bomberman.entities.activeObject.Character.moveEnemy;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Character.Bomber;
import uet.oop.bomberman.entities.activeObject.Character.moveEnemy.AStar.AStar;
import uet.oop.bomberman.entities.activeObject.Character.moveEnemy.AStar.Node;

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

                int xBomber = bomber.getYMap();
                int yBomber = bomber.getXMap();

                AStar aStar = new AStar(map, x, y, xBomber, yBomber);
                List<Node> resultPath = aStar.aStarSearch(); // duong di luu cac node tu vi tri Bomber den Enemy
                if (resultPath != null) {
                    if (resultPath.size() < 2) return easyMove.getDirection(x, y);
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
                    }
                }
            }
        }
        return easyMove.getDirection(x, y);
    }
}