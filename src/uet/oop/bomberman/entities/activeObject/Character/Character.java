package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;


/**
 * Các vật thể di chuyển.
 */
public abstract class Character extends Entity {

    // Xem còn sống ko
    boolean alive = true;
    public int animation = 0;
    // Tốc độ di chuyển

    public boolean delete = false;
    public int speed;

    public Character(int x, int y, Image img) {
        super(x, y, img);
        // Tốc độ default
        speed = 2;
    }

    /**
     * Hàm di chuyển lên xuống trái phải.
     */
    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void moveLeft();
    public abstract void moveRight();

    /**
     * Hàm kiểm tra xem có thể đi vào ô (x,y) ko.
     */
    public abstract boolean canMove(int x, int y, char[][] map);

    public void update() {

    }
}
