package uet.oop.bomberman.entities.activeObject;

import javafx.scene.image.Image;

public class Portal extends activeEntity {

    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    public void collide(activeEntity entity) {
    }
}
