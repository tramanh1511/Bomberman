package uet.oop.bomberman.entities.activeObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;


public abstract class activeEntity extends Entity {
    public boolean delete = false; // Nếu ko còn hoạt động nữa thì xóa khỏi list
    public boolean active = true;  // Xem có còn đang hoạt động ko, nếu ko thì delete = true

    public int animation = 0;

    public activeEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void update() {

    }

    /**
     * Kiểm tra sự va chạm với entity khác.
     *
     * @param entity vật bị va chạm
     */

    public void collide(activeEntity entity) {

    }

}
