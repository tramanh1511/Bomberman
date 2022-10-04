package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.loadMap;
import java.util.Random;

import uet.oop.bomberman.entities.activeObject.Character.moveEnemy.*;

public class Balloon extends Character {
   // Biến random hướng đi của Balloon
    private int randomDirection = 2;

    // Thời gian giữa 2 lần chuyển hướng
    private int randomTimeInterval = 30;

    private int animationTime = 90;
    Random random = new Random();

    public Balloon(int x, int y, Image img) {
        super(x, y, img);

        // Tốc độ default
        speed = 1;
    }

    /**
     * Hàm di chuyển lên xuống trái phải.
     */
    @Override
    public void moveUp() {
        this.setY(this.getY() - speed);
        this.setImg(Sprite.movingSprite(Sprite.balloon_right1, Sprite.balloon_right2, Sprite.balloon_right3, animation, 20).getFxImage());
    }

    @Override
    public void moveDown() {
        this.setY(this.getY() + speed);
        this.setImg(Sprite.movingSprite(Sprite.balloon_left1, Sprite.balloon_left2, Sprite.balloon_left3, animation, 20).getFxImage());
    }

    @Override
    public void moveLeft() {
        this.setX(this.getX() - speed);
        this.setImg(Sprite.movingSprite(Sprite.balloon_left1, Sprite.balloon_left2, Sprite.balloon_left3, animation, 20).getFxImage());
    }

    @Override
    public void moveRight() {
        this.setX(this.getX() + speed);
        this.setImg(Sprite.movingSprite(Sprite.balloon_right1, Sprite.balloon_right2, Sprite.balloon_right3, animation, 20).getFxImage());
    }

    /**
     * Random hướng đi của balloon
     * @param x tọa độ hàng trên map
     * @param y tọa độ cột trên map
     * @param map lưu map từ file level
     * @return hướng random đi được
     */

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
     * Cập nhật trạng thái của balloon
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
            if(animationTime > 60) {
                setImg(Sprite.balloon_dead.getFxImage());
            } else {
                setImg(Sprite.movingSprite(Sprite.mob_dead1,Sprite.mob_dead2,Sprite.mob_dead3, animationTime,20).getFxImage());
            }
        } else {
            if (getY() % Sprite.SCALED_SIZE == 0 && getX() % Sprite.SCALED_SIZE == 0 && randomTimeInterval <= 0) {
                int xMap = getY() / Sprite.SCALED_SIZE;
                int yMap = getX() / Sprite.SCALED_SIZE;
                randomDirection = easyMove.getDirection(xMap, yMap, speed, loadMap.map);
                randomTimeInterval = 30;
            } else {
                randomTimeInterval--;
            }
            Move();
        }
    }
}
