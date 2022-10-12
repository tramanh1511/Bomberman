package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Character.moveEnemy.hardMove;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.map;

/**
 * Enemy Oneal di chuyển với tốc độ random từ 1->2
 * Bomber đến gần oneal thì oneal có khả năng đuổi theo.
 */

public class Oneal extends Character {

    // Biến random hướng đi của Oneal
    private int Direction = 2;

    private int animationTime = 30;

    Random random = new Random();

    public Oneal(int x, int y, Image img) {
        super(x, y, img);

        // Tốc độ random
        speed = random.nextInt(2) + 1;
        // speed = 1;
    }

    /**
     * Hàm di chuyển lên xuống trái phải.
     */
    @Override
    public void moveUp() {
        if (map[getYMap()][getXMap()] != '*' && map[getYMap()][getXMap()] != '#') {
            setY(getY() - speed);
        }
        setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animation, 20).getFxImage());
    }

    @Override
    public void moveDown() {
        if (map[getYMap()][getXMap()] != '*' && map[getYMap()][getXMap()] != '#') {
            setY(getY() + speed);
        }
        setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animation, 20).getFxImage());
    }

    @Override
    public void moveLeft() {
        if (map[getYMap()][getXMap()] != '*' && map[getYMap()][getXMap()] != '#') {
            setX(getX() - speed);
        }
        setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animation, 20).getFxImage());
    }

    @Override
    public void moveRight() {
        if (map[getYMap()][getXMap()] != '*' && map[getYMap()][getXMap()] != '#') {
            setX(getX() + speed);
        }
        setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animation, 20).getFxImage());
    }

    public boolean canMove(int x, int y) {
        int xUnit = y / Sprite.SCALED_SIZE;
        int yUnit = x / Sprite.SCALED_SIZE;
        return BombermanGame.map[xUnit][yUnit] != '#' && BombermanGame.map[xUnit][yUnit] != '*'
                && BombermanGame.bombMap[xUnit][yUnit] == ' ';
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
     * Cập nhật trạng thái của oneal
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
                setImg(Sprite.oneal_dead.getFxImage());
            } else {
                setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animationTime, 20).getFxImage());
            }
        } else {
            if (getY() % Sprite.SCALED_SIZE == 0 && getX() % Sprite.SCALED_SIZE == 0) {
                int xMap = getYMap();
                int yMap = getXMap();
                Direction = hardMove.getDirection(xMap, yMap, map);
                speed = random.nextInt(2) + 1; // Random lại speed
            }
            Move();
        }
    }
}

