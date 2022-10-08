package uet.oop.bomberman.entities.activeObject.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.activeObject.activeEntity;

/**
 * PowerUp Items.
 */
public abstract class Item extends activeEntity {

    public Item(int x, int y, Image img) {
        super(x, y, img);
        active = false;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void update() {
    }

}
