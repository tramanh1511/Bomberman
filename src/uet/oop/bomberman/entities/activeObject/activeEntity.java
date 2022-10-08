package uet.oop.bomberman.entities.activeObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import java.awt.event.ActionEvent;

public abstract class activeEntity extends Entity {
    // Nếu ko còn hoạt động nữa thì xóa khỏi list entity
    public boolean delete = false;
    // Xem có còn đang hoạt động ko, nếu ko thì delete = true
    public boolean active = true;

    public boolean alive = true;

    public int animation = 0;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public activeEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void collide(activeEntity entity) {

    }

    public void update() {

    }
}
