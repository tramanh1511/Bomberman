package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.activeObject.Bomb.Bomb;
import uet.oop.bomberman.entities.activeObject.Bomb.Flame;
import uet.oop.bomberman.entities.activeObject.Item.Item;
import uet.oop.bomberman.entities.activeObject.Item.bombItem;
import uet.oop.bomberman.entities.activeObject.Item.flameItem;
import uet.oop.bomberman.entities.activeObject.Item.speedItem;
import uet.oop.bomberman.entities.activeObject.Portal;
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.loadMap;

import static uet.oop.bomberman.BombermanGame.activeObjects;
import static uet.oop.bomberman.BombermanGame.deadSound;


/**
 * Nhân vật chính Bomber.
 */
public class Bomber extends Character {

    public int bombCount = 1;
    public int powerFlames;

    public int animationTime = 90;
    /**
     * Tạo 1 bomber với các thuộc tính cùng với các nút di chuyển.
     */
    public Bomber(int x, int y, Image image) {
        super(x, y, image);

        speed = 32; // Tốc độ ban đầu
        bombCount = 1; // Số lượng bomb nhả ra mỗi lần
        powerFlames = 1; // Độ dài của flame
        active = true;
    }

    /**
     * Hàm di chuyển lên xuống trái phải.
     */
    @Override
    public void moveUp() {
        if (canMove(getX(), getY() - speed, loadMap.map)) {
            setY(getY() - speed);
        }
        this.setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                Sprite.player_up_2, animation, 20).getFxImage());
    }

    @Override
    public void moveDown() {
        if (canMove(getX(), getY() + speed, loadMap.map)) {
            setY(getY() + speed);
        }
        this.setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animation, 20).getFxImage());
    }

    @Override
    public void moveLeft() {
        if (canMove(getX() - speed, getY(), loadMap.map)) {
            setX(getX() - speed);
        }
        this.setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animation, 20).getFxImage());
    }

    @Override
    public void moveRight() {
        if (canMove(getX() + speed, getY(), loadMap.map)) {
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
     //  System.out.println(xUnit + " " + yUnit);
       return map[yUnit][xUnit] != '#' &&  map[yUnit][xUnit] != '*';
        /*for (Entity entity : stillObjects) {
            int x1 = entity.getX();
            int y1 = entity.getY();
            System.out.println("bomber: " + x + " " + y);
            if (x1 - 20 == x && y1 == y) {
                if (entity instanceof Brick) {
                    System.out.println(x1 + " " + y1);
                    System.out.println("va cham");
                    return false;
                }
            }

        }
        return true;*/
    }

    public void powerUp(Item item) {
        // Tăng độ dài flame
        if (item instanceof flameItem) {
            Flame.powerFlames++;
        }

        // Tăng tốc độ x2
        if (item instanceof speedItem) {
            speed *= 2;
        }

        // Tăng số bom 1 lần thả
        if (item instanceof bombItem) {
            bombCount++;
        }
    }

    void putBomb() {
        BombermanGame.bombSound.play(true, 0);
        activeObjects.add(new Bomb(getX() / Sprite.SCALED_SIZE, getY() / Sprite.SCALED_SIZE,
                Sprite.bomb.getFxImage(), powerFlames));
    }

    public void handleKeyEvent(KeyEvent keyEvent) {
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
                Item item = (Item) entity;
                powerUp(item);
                item.setActive(true);
            }
            if (entity instanceof Portal) {
                BombermanGame.levelUp = true;
            }
        }
    }
}
