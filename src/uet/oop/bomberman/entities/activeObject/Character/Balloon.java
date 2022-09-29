package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.image.Image;


public class Balloon extends Character {

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    /**
     * Hàm di chuyển lên xuống trái phải.
     */
    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }

    /**
     * Hàm kiểm tra xem có thể đi vào ô (x,y) ko.
     *
     * @param x
     * @param y
     * @param map
     */
    @Override
    public boolean canMove(int x, int y, char[][] map) {
        return false;
    }


    @Override
    public void update() {

    }
}
