package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Bomb.Bomb;
import uet.oop.bomberman.entities.activeObject.Bomb.Flame;
import uet.oop.bomberman.entities.activeObject.Item.Item;
import uet.oop.bomberman.entities.activeObject.Item.bombItem;
import uet.oop.bomberman.entities.activeObject.Item.flameItem;
import uet.oop.bomberman.entities.activeObject.Item.speedItem;
import uet.oop.bomberman.entities.activeObject.Portal;
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;


/**
 * Nhân vật chính Bomber.
 */
public class Bomber extends Character {

    public int bombCount = 1; // Số lượng bomb nhả ra mỗi lần
    public int powerFlames; // Độ dài của flame

    public int animationTime = 90;
    /**
     * Tạo 1 bomber với các thuộc tính cùng với các nút di chuyển.
     */
    public Bomber(int x, int y, Image image) {
        super(x, y, image);

        speed = 32; // Tốc độ ban đầu
        bombCount = 1;
        powerFlames = 1;
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
        if (canMove(getX(), getY() - speed, BombermanGame.map)) {
            setY(getY() - speed);
        }
        this.setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                Sprite.player_up_2, animation, 20).getFxImage());
    }

    @Override
    public void moveDown() {
        if (canMove(getX(), getY() + speed, BombermanGame.map)) {
            setY(getY() + speed);
        }
        this.setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animation, 20).getFxImage());
    }

    @Override
    public void moveLeft() {
        if (canMove(getX() - speed, getY(), BombermanGame.map)) {
            setX(getX() - speed);
        }
        this.setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animation, 20).getFxImage());
    }

    @Override
    public void moveRight() {
        if (canMove(getX() + speed, getY(), BombermanGame.map)) {
            setX(getX() + speed);
        }
        this.setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animation, 20).getFxImage());
    }

    /**
     * Hàm kiểm tra xem Bomber có thể đi vào ô (x,y) ko.
     */
    @Override
    public boolean canMove(int x, int y, char[][] map) {
       int xUnit = (int) x / Sprite.SCALED_SIZE;
       int yUnit = (int) y / Sprite.SCALED_SIZE;
       return map[yUnit][xUnit] != '*' && map[yUnit][xUnit] != '#';
    }

    void putBomb() {
        BombermanGame.bombSound.play(true, 0);
        for (int i = 0; i < bombCount; i++) {
            int bonus = i * Sprite.SCALED_SIZE;
            activeObjects.add(new Bomb((getX() + bonus) / Sprite.SCALED_SIZE, (getY() + bonus) / Sprite.SCALED_SIZE,
                    Sprite.bomb.getFxImage(), powerFlames));
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
    }

    public void handleKeyEvent(KeyEvent keyEvent) {
        if (animation > 100) animation = 0;
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
            case SPACE:
                putBomb();
                break;
        }
    }

    @Override
    public void update() {
        if (!alive) {
            animationTime--;
            if (animationTime < 0) {
                delete = true;
                active = false;
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
     * @param entity đối tượng va chạm
     */
    public void collide(activeEntity entity) {
        // Nếu bomber hoặc entity chết thì ko làm gì
        if (!entity.alive || !active || entity instanceof Bomber) {
            return;
        }
        int xBomber = getX() / Sprite.SCALED_SIZE;
        int yBomber = getY() / Sprite.SCALED_SIZE;

        int xEntity = entity.getX() / Sprite.SCALED_SIZE;
        int yEntity = entity.getY() / Sprite.SCALED_SIZE;

        if (xBomber == xEntity && yBomber == yEntity) {
            if (entity instanceof Character) {
                alive = false;
            }
            if (entity instanceof Item) {
                powerUpSound.play(true, 0);
                Item item = (Item) entity;
                item.alive = false;
                powerUp(item);
            }
            if (entity instanceof Portal) {
                BombermanGame.levelUp = true;
            }
        }
    }
}
