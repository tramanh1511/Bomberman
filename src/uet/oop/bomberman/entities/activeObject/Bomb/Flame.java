package uet.oop.bomberman.entities.activeObject.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.activeObject.Character.Character;
import uet.oop.bomberman.entities.activeObject.Item.Item;
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends activeEntity {
    public int direction;
    public boolean last; // xem có là flame cuối hay ko (last = true)
    public static int powerFlames = 1;

    public static int timeExplode = 0;    // Thời gian đếm ngược trc khi xuất hiên flame

    public int timeAfter = 30; // Thời gian xuất hiện flame

    /**
     * Tạo flame.
     *
     * @param xUnit     tọa độ hàng trên map
     * @param yUnit     tọa độ cột trên map
     * @param direction hướng của flame
     * @param last      xem có là flame cuối hay ko
     * @param img       ảnh flame
     */
    public Flame(int xUnit, int yUnit, int direction, boolean last, Image img) {
        super(xUnit, yUnit, img);
        this.direction = direction;
        this.last = last;
        delete = false;
        active = false;
    }

    /**
     * Cho flame xuất hiện theo hướng direction.
     */
    @Override
    public void update() {
        animation = 20;

        if (timeExplode > 0) { // chưa nổ
            timeExplode--;  // Đếm ngược thời gian xuất hiện flame
        } else {
            active = true; // Flame hoạt động
            timeAfter--; // Đếm ngược thời gian flame hiện thị

            if (timeAfter < 0) { // Hết thời gian hiện thị
                delete = true; // xóa flame
                active = false;
            }

            // Xuất hiện theo hàng dọc - up - down - not last
            if (direction == 0 || direction == 1 && !last) {
                setImg(Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animation, 20).getFxImage());
            }

            // Xuất hiện theo hàng dọc - up - last
            if (direction == 0 && last) {
                setImg(Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, animation, 20).getFxImage());
            }

            // Xuất hiện theo hàng dọc - down - last
            if (direction == 1 && last) {
                setImg(Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animation, 20).getFxImage());
            }

            // Xuất hiện theo hàng ngang - not last
            if (direction == 2 || direction == 3 && !last) {
                setImg(Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animation, 20).getFxImage());
            }

            // Xuất hiện theo hàng ngang - left - last
            if (direction == 2 && last) {
                setImg(Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, animation, 20).getFxImage());
            }

            // Xuất hiện theo hàng ngang - right - last
            if (direction == 3 && last) {
                setImg(Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, 10, 20).getFxImage());
            }
        }
    }

    /**
     * Xử lý va chạm flame với các entity khác.
     *
     * @param entity đối tượng va chạm với flame
     */
    @Override
    public void collide(activeEntity entity) {
        // Nếu flame chưa kích hoạt || entity chết
        // hoặc va chạm với flame khác || powerup thì ko làm gì cả
        if (!active || !entity.alive || entity instanceof Flame || entity instanceof Item) {
            return;
        }

        int xEntity = entity.getX() / Sprite.SCALED_SIZE;
        int yEntity = entity.getY() / Sprite.SCALED_SIZE;

        int xFlame = getX() / Sprite.SCALED_SIZE;
        int yFlame = getY() / Sprite.SCALED_SIZE;

        // Va chạm với các entity khác.
        if (xEntity == xFlame && yEntity == yFlame) {
            // Nếu gặp bomb thì cho nổ
            if (entity instanceof Bomb) {
                Bomb bomb = (Bomb) entity;
                bomb.timeExplode = 0; // Cho nổ luôn
                return;
            }

            // Gặp các entity khác
            if (entity instanceof activeEntity) {
                entity.alive = false;
            }
        }
    }
}
