package uet.oop.bomberman.entities.activeObject.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends activeEntity {
    public int direction;
    public boolean last;
    public static int length = 4;


    public Flame(int xUnit, int yUnit, int direction, boolean last, Image img) {
        super(xUnit, yUnit, img);
        this.direction = direction;
        this.last = last;
    }
    
    @Override
    public void update() {
        if (direction == 0 || direction == 1 && !last) { // vertical - not last
            setImg(Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, 10, 20).getFxImage());
        }
        if (direction == 0 && last) { // vertical - up - last
            setImg(Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, 10, 20).getFxImage());
        }
        if (direction == 1 && last) { // vertical - down - last
            setImg(Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, 10, 20).getFxImage());
        }

        if (direction == 2 || direction == 3 && !last) { // horizontal - not last
            setImg(Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, 10, 20).getFxImage());
        }
        if (direction == 2 && last) { // horizontal - left - last
            setImg(Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, 10, 20).getFxImage());
        }
        if (direction == 3 && last) { // horizontal - right - last
            setImg(Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, 10, 20).getFxImage());
        }
    }
}
