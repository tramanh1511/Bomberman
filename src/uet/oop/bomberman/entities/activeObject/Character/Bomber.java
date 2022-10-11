package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Bomb.Bomb;
import uet.oop.bomberman.entities.activeObject.Item.*;
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;


/**
 * Nhân vật chính Bomber.
 */
public class Bomber extends Character {

    public int bombCount; // Số lượng bomb nhả ra mỗi lần
    public int powerFlames; // Độ dài của flame
    public boolean wallPass;

    public int animationTime = 90;

    /**
     * Tạo 1 bomber với các thuộc tính cùng với các nút di chuyển.
     */
    public Bomber(int x, int y, Image image) {
        super(x, y, image);
        speed = 32; // Tốc độ ban đầu
        bombCount = 1;
        powerFlames = 1;
        wallPass = false;
        active = true;
    }

    public void setPowerFlames(int powerFlames) {
        this.powerFlames = powerFlames;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setbombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    /**
     * Hàm di chuyển lên xuống trái phải.
     */
    @Override
    public void moveUp() {
        if (canMove(getX(), getY() - speed)) {
            setY(getY() - speed);
        }
        this.setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                Sprite.player_up_2, animation, 20).getFxImage());
    }

    @Override
    public void moveDown() {
        if (canMove(getX(), getY() + speed)) {
            setY(getY() + speed);
        }
        this.setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animation, 20).getFxImage());
    }

    @Override
    public void moveLeft() {
        if (canMove(getX() - speed, getY())) {
            setX(getX() - speed);
        }
        this.setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animation, 20).getFxImage());
    }

    @Override
    public void moveRight() {
        if (canMove(getX() + speed, getY())) {
            setX(getX() + speed);
        }

        this.setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animation, 20).getFxImage());
    }

    /**
     * Hàm kiểm tra xem Bomber có thể đi vào ô (x,y) ko.
     */
    @Override
    public boolean canMove(int x, int y) {
        if (wallPass) return true;
        int xUnit = x / Sprite.SCALED_SIZE;
        int yUnit = y / Sprite.SCALED_SIZE;
        System.out.println(map[yUnit][xUnit]);
        return map[yUnit][xUnit] != '*' && map[yUnit][xUnit] != '#';
    }

    void putBomb() {
        BombermanGame.bombSound.play(true, 0);
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int count = bombCount - 1;
        activeObjects.add(new Bomb(getXMap(), getYMap(), Sprite.bomb.getFxImage(), powerFlames));
        for (int i = 0; i < 4; i++) {
            if (count == 0) break;
            int xUnit = getX() + Sprite.SCALED_SIZE * dx[i];
            int yUnit = getY() + Sprite.SCALED_SIZE * dy[i];
            if (canMove(xUnit, yUnit)) {
                count--;
                activeObjects.add(new Bomb(xUnit / Sprite.SCALED_SIZE, yUnit / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage(), powerFlames));
            }
        }
    }

    public void powerUp(Item item) {
        // Tăng độ dài flame
        if (item instanceof flameItem) {
            setPowerFlames(powerFlames + 1);
        }

        // Tăng tốc độ x2
        if (item instanceof speedItem) {
            setSpeed(speed * 2);
        }

        // Tăng số bom 1 lần thả
        if (item instanceof bombItem) {
            setbombCount(bombCount + 1);
        }

        // Xuyen tuong
        if (item instanceof wallItem) {
            wallPass = true;
        }

        if (item instanceof Portal) {
            System.out.println("level");
            levelUp = true;
        }
    }

    public void handleKeyEvent1(KeyEvent keyEvent) {
        if (animation > 200) animation = 0;
        else animation++;
        switch (keyEvent.getCode()) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case ENTER:
                putBomb();
                break;
        }
    }

    public void handleKeyEvent2(KeyEvent keyEvent) {
        animation++;
        switch (keyEvent.getCode()) {
            case W:
                moveUp();
                break;
            case S:
                moveDown();
                break;
            case A:
                moveLeft();
                break;
            case D:
                moveRight();
                break;
            case SPACE:
                putBomb();
                break;
        }
    }

    @Override
    public void update() {
        if (!active) {
            animationTime--;
            if (animationTime < 0) {
                delete = true;
                deadSound.play(false, 0);
            } else {
                if (!deadSound.isPlaying()) {
                    deadSound.play(true, 0);
                }
                setImg(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animationTime, 30).getFxImage());
            }
        }
    }

    /**
     * Xử lý va chạm của Bomber với các entity khác.
     *
     * @param entity đối tượng va chạm
     */
    @Override
    public void collide(activeEntity entity) {
        // Nếu bomber hoặc entity chết thì ko làm gì
        if (!entity.active || !active || entity instanceof Bomber) {
            return;
        }
        int xBomber = getYMap();
        int yBomber = getXMap();
        int xEntity = entity.getYMap();
        int yEntity = entity.getXMap();

        if (xBomber == xEntity && yBomber == yEntity) {
            if (entity instanceof Character) {
                active = false;
                return;
            }
            if (entity instanceof Item) {
                powerUpSound.play(true, 0);
                Item item = (Item) entity;
                item.active = true;
                powerUp(item);
            }
        }
    }
}
