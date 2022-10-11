package uet.oop.bomberman.entities.activeObject.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends activeEntity {
    protected int timeAfter = 30;
    public int powerFlames;
    public int timeExplode = 90;
    public boolean added = false;

    public Bomb(int xUnit, int yUnit, Image img, int powerFlames) {
        super(xUnit, yUnit, img);
        this.powerFlames = powerFlames;
        active = true;
        delete = false;
    }

    /**
     * Tạo flame theo 4 hướng.
     */
    public void update() {
        if (timeExplode > 0) { // Chưa nổ thì cho hiện bomb
            timeExplode--;
            setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timeExplode, 20).getFxImage());
        } else { // Nổ rồi thì cho hiện flame
            if (!added) {
                // Tạo flame
                Flame flame = new Flame(getXMap(), getYMap(), 1, false, Sprite.explosion_vertical.getFxImage());
                flame.createFlame();
                BombermanGame.activeObjects.addAll(flame.flameList);
                added = true;
            }
            timeAfter--; // Đếm ngược thời gian bom khi nổ
            if (timeAfter < 0) { // Nếu đã hết thời gian nổ
                delete = true;
                active = false;
            }
            // Animation bom nổ
            setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, timeAfter, 20).getFxImage());
            BombermanGame.bombSound.play(false, 0);
        }
    }

}




