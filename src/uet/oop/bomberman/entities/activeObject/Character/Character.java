package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Các vật thể di chuyển.
 */
public abstract class Character extends Entity {

    // Xem còn sống ko
    boolean alive = true;
    // Tốc độ di chuyển
    public int speed;

    /**
     * Các sprite tạo animation di chuyểnn lên xuống trái phải.
     */
    protected List<Sprite> up = new ArrayList<>();
    protected List<Sprite> down = new ArrayList<>();
    protected List<Sprite> right = new ArrayList<>();
    protected List<Sprite> left = new ArrayList<>();

    public Character(int x, int y, Image img) {
        super(x, y, img);
        // Tốc độ default là 1
        speed = 1;
    }

    // Hàm tạo animation di chuyển lên
    public void createAnimationMoveUp(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        up.add(sprite1);
        up.add(sprite2);
        up.add(sprite3);
    }

    // Hàm tạo animation di chuyển xuống
    public void createAnimationMoveDown(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        down.add(sprite1);
        down.add(sprite2);
        down.add(sprite3);
    }

    // Hàm tạo animation di chuyển sang phải
    public void createAnimationMoveRight(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        right.add(sprite1);
        right.add(sprite2);
        right.add(sprite3);
    }

    // Hàm tạo animation di chuyển sang trái
    public void createAnimationMoveLeft(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        left.add(sprite1);
        left.add(sprite2);
        left.add(sprite3);
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
