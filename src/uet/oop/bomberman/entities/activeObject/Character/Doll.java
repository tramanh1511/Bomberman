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

    // Thời gian giữa 2 lần chuyển hướng
    private int randomTimeInterval = 0;
    
    public Doll(int x, int y, Image img) {
        super(x, y, img);
        speed = 3;
    }

    /**
     * Hàm di chuyển lên xuống trái phải.
     */
    @Override
    public void moveUp() {
        if (canMove(getX(), getY() - speed)) {
            setY(getY() - speed);
        }
        setImg(Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animation, 20).getFxImage());
    }

    @Override
    public void moveDown() {
        if (canMove(getX(), getY() - speed)) {
            setY(getY() - speed);
        }
        setImg(Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animation, 20).getFxImage());
    }

    @Override
    public void moveLeft() {
        if (canMove(getX() - speed, getY())) {
            setX(getX() - speed);
        }
        setImg(Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animation, 20).getFxImage());
    }

    @Override
    public void moveRight() {
        if (canMove(getX() + speed, getY())) {
            setX(getX() + speed);
        }
        setImg(Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animation, 20).getFxImage());
    }

    public boolean canMove(int x, int y) {
        int xUnit = getYMap();
        int yUnit = getXMap();
        return BombermanGame.map[xUnit][yUnit] != '*' && BombermanGame.map[xUnit][yUnit] != '#';
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
        if (animation > 100) {
            animation = 0;
        }
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
            if (getY() % Sprite.SCALED_SIZE == 0 && getX() % Sprite.SCALED_SIZE == 0 && randomTimeInterval < 0) {
                int xMap = getYMap();
                int yMap = getXMap();
                Direction = hardMove.getDirection(xMap, yMap, speed, BombermanGame.map);
                randomTimeInterval = 40;
            } else {
                randomTimeInterval--;
            }
            Move();

        }
    }

}
