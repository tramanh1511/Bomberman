package uet.oop.bomberman.entities.activeObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.Map;

public class Brick extends activeEntity {

    private int animationTime = 40; // Thời gian bom nổ
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (!active) {
            animationTime--; // Đếm ngược bom nổ
            if (animationTime < 0) { // Nếu đã hết thời gian sau khi nổ
                int xBrick = getYMap();
                int yBrick = getXMap();
                BombermanGame.map[xBrick][yBrick] = ' ';
                delete = true; // Xoá
            }
            // Animation brick nổ
            setImg(Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animationTime, 20).getFxImage());
        }
    }
}
