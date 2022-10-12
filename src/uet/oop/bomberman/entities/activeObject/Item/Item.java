package uet.oop.bomberman.entities.activeObject.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.activeEntity;

/**
 * PowerUp Items.
 */
public abstract class Item extends activeEntity {

    public Item(int x, int y, Image img) {
        super(x, y, img);
        active = false;
    }

    public void update() {
        if (active) {
            int x = getYMap();
            int y = getXMap();
            BombermanGame.map[x][y] = ' ';
            delete = true; // Xoá
        }
    }

}
