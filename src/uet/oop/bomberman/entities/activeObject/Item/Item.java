package uet.oop.bomberman.entities.activeObject.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

/**
 * PowerUp Items.
 */
public abstract class Item extends Entity {

    // Xem item đó đã đc kích hoạt chưa.
    protected boolean active = false;
    public Item(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {

    }


}
