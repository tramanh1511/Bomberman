package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.loadMap;
import java.util.Random;
import uet.oop.bomberman.entities.activeObject.Character.moveEnemy.*;

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
        speed = random.nextInt( 3);
    }

    /**
     * Hàm di chuyển lên xuống trái phải.
     */
    @Override
    public void moveUp() {
        this.setY(this.getY() - speed);
        this.setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animation, 20).getFxImage());
    }

    @Override
    public void moveDown() {
        this.setY(this.getY() + speed);
        this.setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animation, 20).getFxImage());
    }

    @Override
    public void moveLeft() {
        this.setX(this.getX() - speed);
        this.setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animation, 20).getFxImage());
    }

    @Override
    public void moveRight() {
        this.setX(this.getX() + speed);
        this.setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animation, 20).getFxImage());
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
                int xMap = getY() / Sprite.SCALED_SIZE;
                int yMap = getX() / Sprite.SCALED_SIZE;
                randomDirection = mediumMove.getDirection(xMap, yMap, speed, loadMap.map);
                randomTimeInterval = 60;
            } else {
                randomTimeInterval--;
            }
           // Move();

        }
    }
}

