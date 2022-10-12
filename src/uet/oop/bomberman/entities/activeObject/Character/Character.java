package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.activeObject.activeEntity;

/**
 * Các vật thể di chuyển.
 */
public abstract class Character extends activeEntity {

    // Tốc độ di chuyển
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
    public boolean canMove(int x, int y) {
        return true;
    }

    public void update() {

    }

    public void collide(activeEntity entity) {

    }

}
