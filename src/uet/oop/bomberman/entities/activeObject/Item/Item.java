package uet.oop.bomberman.entities.activeObject.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.powerUpSound;

/**
 * PowerUp Items.
 */
public abstract class Item extends activeEntity {

    public Item(int x, int y, Image img) {
        super(x, y, img);
        active = false;

    }

    public void update() {
        if (!alive) {
            int x = getY() / Sprite.SCALED_SIZE;
            int y = getX() / Sprite.SCALED_SIZE;
            BombermanGame.map[x][y] = ' ';
            delete = true; // Xoá
            active = true;
        }

    }

}
