package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Character.moveEnemy.easyMove;
import uet.oop.bomberman.graphics.Sprite;
import static uet.oop.bomberman.BombermanGame.deadSound;

public class Balloon extends Character {
    // Biến random hướng đi của Balloon
    private int randomDirection = 2;

    // Thời gian giữa 2 lần chuyển hướng
    private int randomTimeInterval = 0;
    private int animationTime = 60;

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
        if (!alive) {
            animationTime--;
            if (animationTime < 0) {
                deadSound.play(false, 1);
                delete = true;
                active = false;
            } else {
                // Animation ballon chết
                if (!deadSound.isPlaying()) {
                    deadSound.play(true, 1);
                }
                if (animationTime > 30) {
                    setImg(Sprite.balloon_dead.getFxImage());
                } else {
                    setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animationTime, 20).getFxImage());
                }
            }
        } else {
            if (getY() % Sprite.SCALED_SIZE == 0 && getX() % Sprite.SCALED_SIZE == 0 && randomTimeInterval < 0) {
                int xMap = getY() / Sprite.SCALED_SIZE;
                int yMap = getX() / Sprite.SCALED_SIZE;
                randomDirection = easyMove.getDirection(xMap, yMap, BombermanGame.map);
                randomTimeInterval = 30;
            } else {
                randomTimeInterval--;
            }
            Move();
        }
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
