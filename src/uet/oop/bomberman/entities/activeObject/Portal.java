package uet.oop.bomberman.entities.activeObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends activeEntity {

    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    public void collide(activeEntity entity) {
    }
}
