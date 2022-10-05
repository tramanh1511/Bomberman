package uet.oop.bomberman.entities.activeObject.Character.moveEnemy;

import java.util.Random;

/**
 * Random hướng đi của ballon, kiểu đơn giản nhất.
 */
public class easyMove {
    /**
     * Random hướng đi của balloon
     * @param x tọa độ hàng trên map
     * @param y tọa độ cột trên map
     * @param map lưu map từ file level
     * @return hướng random đi được
     */
    public static int getDirection(int x, int y, char[][] map) {
        Random random = new Random();
        int randomDirection = random.nextInt(4);
        boolean isRunning = true;

        while (isRunning) {
            randomDirection = random.nextInt(4);
            if (randomDirection == 0) { // up
                if (map[x - 1][y] == ' ') {
                    isRunning = false;
                    break;
                }
            }
            if (randomDirection == 1) { // down
                if (map[x + 1][y] == ' ') {
                    isRunning = false;
                    break;
                }
            }
            if (randomDirection == 3) { // left
                if (map[x][y +1] == ' ') {
                    isRunning = false;
                    break;
                }
            }
            if (randomDirection == 2) { // right
                if (map[x][y - 1] == ' ') {
                    isRunning = false;
                    break;
                }
            }
        }
        return randomDirection;
    }
}
