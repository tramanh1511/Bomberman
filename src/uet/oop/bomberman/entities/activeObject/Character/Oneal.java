package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Character.moveEnemy.mediumMove;
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Sprite;
import java.util.Random;

/**
 * Enemy Oneal di chuyển với tốc độ random từ 1->2
 * Bomber đến gần oneal thì oneal có khả năng đuổi theo.
 */

public class Oneal extends Character {

    // Biến random hướng đi của Oneal
    private int randomDirection = 2;

    // Thời gian giữa 2 lần chuyển hướng
    private int randomTimeInterval = 60;

    private int animationTime = 90;
    Random random = new Random();

    public Oneal(int x, int y, Image img) {
        super(x, y, img);

        // Tốc độ random
        speed = random.nextInt(2);

    }

    /**
     * Hàm di chuyển lên xuống trái phải.
     */
    @Override
    public void moveUp() {
        if (canMove(getX(), getY() - speed, BombermanGame.map)) {
            setY(getY() - speed);
        }
        setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animation, 20).getFxImage());
    }

    @Override
    public void moveDown() {
        if (canMove(getX(), getY() - speed, BombermanGame.map)) {
            setY(getY() - speed);
        }
        setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animation, 20).getFxImage());
    }

    @Override
    public void moveLeft() {
        if (canMove(getX() - speed, getY(), BombermanGame.map)) {
            setX(getX() - speed);
        }
        setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animation, 20).getFxImage());
    }

    @Override
    public void moveRight() {
        if (canMove(getX() + speed, getY(), BombermanGame.map)) {
            setX(getX() + speed);
        }
        setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animation, 20).getFxImage());
    }

    public boolean canMove(int x, int y, char[][] map) {
        int xUnit = getYMap();
        int yUnit = getXMap();
        return map[xUnit][yUnit] != '*' && map[xUnit][yUnit] != '#';
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
     * Cập nhật trạng thái của oneal
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
                setImg(Sprite.balloon_dead.getFxImage());
            } else {
                setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animationTime, 20).getFxImage());
            }
        } else {
            if (getY() % Sprite.SCALED_SIZE == 0 && getX() % Sprite.SCALED_SIZE == 0 && randomTimeInterval <= 0) {
                int xMap = getYMap();
                int yMap = getXMap();
                randomDirection = mediumMove.getDirection(xMap, yMap, speed, BombermanGame.map);
                speed = random.nextInt(2); // Random lại speed
                randomTimeInterval = 60;
            } else {
                randomTimeInterval--;
            }
            Move();

        }
    }

    @Override
    public void collide(activeEntity entity) {

    }
}

