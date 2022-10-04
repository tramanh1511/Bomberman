package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.activeObject.Bomb.Bomb;
import uet.oop.bomberman.entities.stillObject.Brick;
import uet.oop.bomberman.entities.stillObject.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.loadMap;

import static uet.oop.bomberman.BombermanGame.activeObjects;


/**
 * Nhân vật chính Bomber.
 */
public class Bomber extends Character {
    KeyEvent keyEvent;

    /**
     * Tạo 1 bomber với các thuộc tính cùng với các nút di chuyển.
     */
    public Bomber(int x, int y, Image image) {
        super(x, y, image);

        // Tốc độ ban đầu
        speed = 4;

    }

    /**
     * Hàm di chuyển lên xuống trái phải.
     */
    @Override
    public void moveUp() {
        if (canMove(getX(), getY() - speed, loadMap.map)) {
            setY(getY() - speed);
            System.out.println("move up " + getX() + " " + getY());
        }
        this.setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                Sprite.player_up_2, getY(), 20).getFxImage());
    }

    @Override
    public void moveDown() {
        if (canMove(getX(), getY() + speed, loadMap.map)) {
            setY(getY() + speed);
           System.out.println("move down " + getX() + " " + getY());
        }
        this.setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, getY(), 20).getFxImage());
    }

    @Override
    public void moveLeft() {
        if (canMove(getX() - speed, getY(), loadMap.map)) {
            setX(getX() - speed);
            System.out.println("move left " + getX() + " " + getY());
        }
        this.setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, getX(), 20).getFxImage());
    }

    @Override
    public void moveRight() {
        if (canMove(getX() + speed, getY(), loadMap.map) && canMove(getX() + speed, getY(), loadMap.map)) {
            setX(getX() + speed);
            System.out.println("move right " + getX() + " " + getY());
        }
        this.setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, getX(), 20).getFxImage());
    }


  /*  @Override
    public void moveUp() {
        if (canMove(this.getX() + 3, this.getY() - speed, loadMap.map) && canMove(this.getX() + Sprite.SCALED_SIZE - 5, this.getY() - speed, loadMap.map)) {
            if (loadMap.map[(this.getY() - speed) / Sprite.SCALED_SIZE][(this.getX() + 3) / Sprite.SCALED_SIZE] != '#') {
                this.setY(this.getY() - speed);
            }

        }
        this.setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, this.getY(), 20).getFxImage());
    }

    @Override
    public void moveDown() {
        if (canMove(this.getX() + 3, this.getY() + speed + Sprite.SCALED_SIZE, loadMap.map) && canMove(this.getX() + Sprite.SCALED_SIZE - 5, this.getY() + speed + Sprite.SCALED_SIZE - 3, loadMap.map)) {
            if (loadMap.map[(this.getY() + speed + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE][(this.getX() + 3) / Sprite.SCALED_SIZE] != '#') {
                this.setY(this.getY() + speed);
            }
        }
        this.setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, this.getY(), 20).getFxImage());
    }

    @Override
    public void moveLeft() {
        if (canMove(this.getX() + Sprite.SCALED_SIZE - 5 - speed, this.getY() + 3, loadMap.map) && canMove(this.getX() - speed + Sprite.SCALED_SIZE - 3, this.getY() + Sprite.SCALED_SIZE - 5, loadMap.map)) {
            if (loadMap.map[(this.getY() + 3) / Sprite.SCALED_SIZE][(this.getX() + Sprite.SCALED_SIZE - 5 - speed) / Sprite.SCALED_SIZE] != '#') {
                this.setX(this.getX() - speed);
            }

        }
        this.setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, this.getX(), 20).getFxImage());
    }

    @Override
    public void moveRight() {
        if (canMove(this.getX() + Sprite.SCALED_SIZE - 5 + speed, this.getY() + 3, loadMap.map) && canMove(this.getX() + speed + Sprite.SCALED_SIZE - 3, this.getY() + Sprite.SCALED_SIZE - 5, loadMap.map)) {
            if (loadMap.map[(this.getY() + 3) / Sprite.SCALED_SIZE][(this.getX() + Sprite.SCALED_SIZE - 5 + speed) / Sprite.SCALED_SIZE] != '#') {
                this.setX(this.getX() + speed);
            }
        }
        this.setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, this.getX(), 20).getFxImage());
    }
*/

    /**
     * Hàm kiểm tra xem Bomber có thể đi vào ô (x,y) ko.
     */
    @Override
    public boolean canMove(int x, int y, char[][] map) {
       int xUnit = (int) x / Sprite.SCALED_SIZE;
       int yUnit = (int) y / Sprite.SCALED_SIZE;
       System.out.println("toa do " + xUnit + " " + yUnit);
        return map[yUnit][xUnit] != '#' && map[yUnit][xUnit] != '*';
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

    public boolean collide() {
       /* if (entity instanceof Balloon) {
            //System.out.println("collide");
            setImg(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, getY(), 20).getFxImage());
            return true;
        }*/
        return checkEnemy(getX(), getY());
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
                break;
        }
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    @Override
    public void update() {
        // System.out.println("active entity");
        if (collide()) {
            System.out.println("Die");
            setImg(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, getX(), 20).getFxImage());
        }
    }
}
