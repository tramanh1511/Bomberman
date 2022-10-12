package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Character.moveEnemy.hardMove;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Enemy mạnh nhất :v.
 */
public class Doll extends Character {

    // hướng đi của Doll
    private int Direction = 2;
    private int animationTime = 90;

    public Doll(int x, int y, Image img) {
        super(x, y, img);
        speed = 2;
    }

    /**
     * Hàm di chuyển lên xuống trái phải.
     */
    @Override
    public void moveUp() {
        setY(getY() - speed);
        setImg(Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animation, 20).getFxImage());
    }

    @Override
    public void moveDown() {
        setY(getY() + speed);
        setImg(Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animation, 20).getFxImage());
    }

    @Override
    public void moveLeft() {
        setX(getX() - speed);
        setImg(Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animation, 20).getFxImage());
    }

    @Override
    public void moveRight() {
        setX(getX() + speed);
        setImg(Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animation, 20).getFxImage());
    }

    /**
     * Hàm di chuyển chung.
     */
    public void Move() {
        switch (Direction) {
            case 0:
                moveUp();
                break;
            case 1:
                moveDown();
                break;
            case 2:
                moveLeft();
                break;
            case 3:
                moveRight();
                break;
        }
    }

    /**
     * Cập nhật trạng thái của doll
     */
    @Override
    public void update() {
        animation++;

        if (!active) {
            animationTime--;
            if (animationTime < 0) {
                delete = true;
            }

            // Animation ballon chết
            if (animationTime > 60) {
                setImg(Sprite.doll_dead.getFxImage());
            } else {
                setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animationTime, 20).getFxImage());
            }
        } else {
            if (getY() % Sprite.SCALED_SIZE == 0 && getX() % Sprite.SCALED_SIZE == 0) {
                int xMap = getYMap();
                int yMap = getXMap();
                Direction = hardMove.getDirection(xMap, yMap, BombermanGame.map);
            }
            Move();
        }
    }

}
