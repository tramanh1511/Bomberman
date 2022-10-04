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
     * @param speed tốc độ của ballon
     * @return hướng random đi được
     */
    public static int getDirection(int x, int y, int speed, char[][] map) {
        Random random = new Random();
        int randomDirection = random.nextInt(4);
        boolean isRunning = true;

        while (isRunning) {
            randomDirection = random.nextInt(4);
            if (randomDirection == 0) { // đi lên
                if (map[x - speed][y] == ' ') {
                    isRunning = false;
                    break;
                }
            }
            if (randomDirection == 1) { // đi xuống
                if (map[x + speed][y] == ' ') {
                    isRunning = false;
                    break;
                }
            }
            if (randomDirection == 3) { // sang phải
                if (map[x][y + speed] == ' ') {
                    isRunning = false;
                    break;
                }
            }
            if (randomDirection == 2) { // sang trái
                if (map[x][y - speed] == ' ') {
                    isRunning = false;
                    break;
                }
            }
        }
        return randomDirection;
    }
}
