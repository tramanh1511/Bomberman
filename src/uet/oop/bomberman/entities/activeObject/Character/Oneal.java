package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;


public class Oneal extends Character {

    public Oneal(int x, int y, Image img) {
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


    @Override
    public boolean canMove(int x, int y, char[][] map) {
        return false;
    }


    @Override
    public void update() {

    }
}
