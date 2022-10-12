package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Character.moveEnemy.easyMove;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.deadSound;


/**
 * Enemy minvo.
 */
public class Minvo extends Character {
    // Biến random hướng đi của minvo
    private int randomDirection = 2;

    // Thời gian giữa 2 lần chuyển hướng
    private int randomTimeInterval = 0;
    private int animationTime = 90;

    public Minvo(int x, int y, Image img) {
        super(x, y, img);
        speed = 2;
    }

    /**
     * Hàm di chuyển lên xuống trái phải.
     */
    @Override
    public void moveUp() {
        if (canMove(getX(), getY() - speed)) {
            setY(getY() - speed);
        }
        setImg(Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animation, 20).getFxImage());
    }

    @Override
    public void moveDown() {
        if (canMove(getX(), getY() + speed)) {
            setY(getY() + speed);
        }
        setImg(Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animation, 20).getFxImage());
    }

    @Override
    public void moveLeft() {
        if (canMove(getX() - speed, getY())) {
            setX(getX() - speed);
        }
        setImg(Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animation, 20).getFxImage());
    }

    @Override
    public void moveRight() {
        if (canMove(getX() + speed, getY())) {
            setX(getX() + speed);
        }
        setImg(Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animation, 20).getFxImage());
    }

    public boolean canMove(int x, int y) {
        int xUnit = y / Sprite.SCALED_SIZE;
        int yUnit = x / Sprite.SCALED_SIZE;
        return (BombermanGame.map[xUnit][yUnit] != '#' && BombermanGame.map[xUnit][yUnit] != '*'
                && BombermanGame.bombMap[xUnit][yUnit] == ' ');
    }

    /**
     * Hàm di chuyển chung.
     */
    public void Move() {
        switch (randomDirection) {
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
     * Cập nhật trạng thái của minvo
     */
    @Override
    public void update() {
        animation++;

        if (!active) {
            animationTime--;
            if (animationTime < 0) {
                deadSound.play(false, 1);
                delete = true;
            } else {
                // Animation ballon chết
                if (!deadSound.isPlaying()) {
                    deadSound.play(true, 1);
                }
                if (animationTime > 60) {
                    setImg(Sprite.minvo_dead.getFxImage());
                } else {
                    setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animationTime, 20).getFxImage());
                }
            }
        } else {
            if (getY() % Sprite.SCALED_SIZE == 0 && getX() % Sprite.SCALED_SIZE == 0) {
                int xMap = getYMap();
                int yMap = getXMap();
                randomDirection = easyMove.getDirection(xMap, yMap);
                randomTimeInterval = 30;
            } else {
                randomTimeInterval--;
            }
            Move();
        }
    }
}
