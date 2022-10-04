package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.activeObject.Bomb.Bomb;
import uet.oop.bomberman.entities.activeObject.Bomb.Flame;
import uet.oop.bomberman.entities.activeObject.Item.Item;
import uet.oop.bomberman.entities.activeObject.Item.bombItem;
import uet.oop.bomberman.entities.activeObject.Item.flameItem;
import uet.oop.bomberman.entities.activeObject.Item.speedItem;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.loadMap;

import static uet.oop.bomberman.BombermanGame.activeObjects;


/**
 * Nhân vật chính Bomber.
 */
public class Bomber extends Character {

    public int bombCount = 1;
    public int powerFlames;
    /**
     * Tạo 1 bomber với các thuộc tính cùng với các nút di chuyển.
     */
    public Bomber(int x, int y, Image image) {
        super(x, y, image);

        speed = 32; // Tốc độ ban đầu
        bombCount = 1; // Số lượng bomb nhả ra mỗi lần
        powerFlames = 4; // Độ dài của flame
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
       return map[yUnit][xUnit] == ' ';
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
            Flame.length++;
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
        activeObjects.add(new Bomb(getX() / Sprite.SCALED_SIZE, getY() / Sprite.SCALED_SIZE,
                Sprite.bomb.getFxImage(), powerFlames));
        System.gc();
    }

    private boolean checkEnemy(int x, int y) {
        for (Entity entity : activeObjects) {
            if (entity instanceof Balloon) {
                int xEnemy = entity.getX();
                int yEnemy = entity.getY();
                return x == xEnemy && y == yEnemy || x == xEnemy - 1 && y == yEnemy
                        || x == xEnemy && y == yEnemy - 1 || x == xEnemy + 1 && y == yEnemy || x == xEnemy && y == yEnemy + 1;
            }
        }
        return false;
    }

        /*private void checkEnemy() {
            int ax = player.getX() / 32;
            int ay = player.getY() / 32;
            for (Animal animal : enemy) {
                int bx = animal.getX() / 32;
                int by = animal.getY() / 32;
                if (ax == bx && ay == by
                        || ax == bx && ay == by + 1 || ax == bx && ay == by - 1
                        || ay == by && ax == bx + 1 || ay == by && ax == bx - 1) {
                    player.life = false;
                    break;
                }
            }
        }

        private void checkEnemy2() {    //easy level
            int ax = player.getX();
            int ay = player.getY();
            for (Animal animal : enemy)
                if (ax == animal.getX() && ay == animal.getY()
                        || ax == animal.getX() && ay == animal.getY() - 32
                        || ax == animal.getX() && ay == animal.getY() + 32
                        || ay == animal.getY() && ax == animal.getX() - 32
                        || ay == animal.getY() && ax == animal.getX() + 32) {
                    player.life = false;
                    break;
                }
        }

    }*/

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

    }
}
