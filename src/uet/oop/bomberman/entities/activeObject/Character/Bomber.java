package uet.oop.bomberman.entities.activeObject.Character;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.loadMap;

/**
 * Nhân vật chính Bomber.
 */
public class Bomber extends Character {
    // Các khóa di chuyển và đặt bom

    /**
     * Tạo 1 bomber với các thuộc tính cùng với các nút di chuyển.
     */
    public Bomber(int x, int y, Image image) {
        super(x, y, image);

        speed = 16;

        // Tạo animation di chuyển lên xuống trái phải
        createAnimationMoveUp(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2);
        createAnimationMoveDown(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2);
        createAnimationMoveLeft(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2);
        createAnimationMoveRight(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2);
    }

    /**
     * Hàm di chuyển lên xuống trái phải.
     */

  /*  @Override
    public void moveUp() {
        if (canMove(this.getX()+3,this.getY()-speed, loadMap.map) && canMove(this.getX()+ Sprite.SCALED_SIZE-5,this.getY()-speed,loadMap.map))
        {
            if(loadMap.map[(this.getY()-speed)/ Sprite.SCALED_SIZE][(this.getX()+3)/Sprite.SCALED_SIZE] != '#') {
                this.setY(this.getY() - speed);
            }
        }
        this.setImg(Sprite.movingSprite(up.get(0),up.get(1),up.get(2),this.getY(), 20).getFxImage());
    }

    @Override
    public void moveDown() {
        if (canMove(this.getX()+3,this.getY()+speed+Sprite.SCALED_SIZE,loadMap.map) && canMove(this.getX()+Sprite.SCALED_SIZE-5,this.getY()+speed+Sprite.SCALED_SIZE-3,loadMap.map))
            if(loadMap.map[(this.getY()+speed+Sprite.SCALED_SIZE)/ Sprite.SCALED_SIZE][(this.getX()+3)/Sprite.SCALED_SIZE] != '#')
            {
                this.setY(this.getY() + speed);
        }
        this.setImg(Sprite.movingSprite(down.get(0),down.get(1),down.get(2),this.getY(), 20).getFxImage());
    }

    @Override
    public void moveLeft() {
        if (canMove(this.getX()-speed,this.getY()+3,loadMap.map) && canMove(this.getX()-speed,this.getY()+Sprite.SCALED_SIZE-3,loadMap.map)) {
            if(loadMap.map[(this.getY()+3)/ Sprite.SCALED_SIZE][(this.getX()-speed)/Sprite.SCALED_SIZE] != '#') {
                this.setX(this.getX() - speed);
            }
        }
        this.setImg(Sprite.movingSprite(left.get(0),left.get(1),left.get(2),this.getX(), 20).getFxImage());
    }

    @Override
    public void moveRight() {
        if (canMove(this.getX()+Sprite.SCALED_SIZE-5+speed,this.getY()+3,loadMap.map) && canMove(this.getX()+speed+Sprite.SCALED_SIZE-3,this.getY()+Sprite.SCALED_SIZE-5,loadMap.map)) {
            if (loadMap.map[(this.getY() + 3) / Sprite.SCALED_SIZE][(this.getX() + Sprite.SCALED_SIZE - 5 + speed) / Sprite.SCALED_SIZE] != '#') {
                this.setX(this.getX() + speed);
            }
        }
        this.setImg(Sprite.movingSprite(right.get(0),right.get(1),right.get(2),this.getX(), 20).getFxImage());
    }*/


    @Override
    public void moveUp() {
        if (canMove(this.getX() + 3,this.getY() - 1, loadMap.map)) {
            setY(this.getY() - 1);
        }
        setImg(Sprite.movingSprite(up.get(0), up.get(1), up.get(2), getY(),10).getFxImage());
    }

    @Override
    public void moveDown() {
        if (canMove(this.getX() + 3,this.getY() + 1 + Sprite.SCALED_SIZE, loadMap.map)) {
            setY(this.getY() + 1);
        }
        setImg(Sprite.movingSprite(down.get(0), down.get(1), down.get(2), getY(),10).getFxImage());
    }

    @Override
    public void moveLeft() {
        if (canMove(this.getX() - 1,this.getY() + 3, loadMap.map)) {
            setX(this.getX() - 1);
        }
        setImg(Sprite.movingSprite(left.get(0), left.get(1), left.get(2), getX(),10).getFxImage());
    }

    @Override
    public void moveRight() {
        if (canMove(this.getX() + Sprite.SCALED_SIZE - 5 + 1,this.getY() + 3, loadMap.map)) {
            setX(this.getX() + 1);
        }
        setImg(Sprite.movingSprite(right.get(0), right.get(1), right.get(2), getX(),10).getFxImage());
    }

    /**
     * Hàm kiểm tra xem Bomber có thể đi vào ô (x,y) ko.
     */
    @Override
    public boolean canMove(int x, int y, char[][] map) {
        return map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '#'
                    && map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '*';
    }

    public void Move(KeyEvent keyEvent) {
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
        }
    }
}
