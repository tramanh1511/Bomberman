package uet.oop.bomberman.entities.activeObject.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Item.Item;
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Flame extends activeEntity {
    public int direction;
    public boolean last; // xem có là flame cuối hay ko (last = true)
    public static int powerFlames = 1;
    public List<Flame> flameList = new ArrayList<>();

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

    public static void setPowerFlames(int x) {
        powerFlames = x;
    }

    public void createFlame() {
        int[] direction = {0, 1, 2, 3};

        // Duyệt theo 4 hướng
        for (int k = 0; k < 4; k++) {
            int xBomb = getXMap();
            int yBomb = getYMap();
            for (int i = 1; i <= powerFlames; i++) {
                // Mảng dx, dy dùng để tạo flame theo 4 hướng up, down, left, right.
                int[] dy = {-i, i, 0, 0};
                int[] dx = {0, 0, -i, i};
                char tile = BombermanGame.map[yBomb + dy[k]][xBomb + dx[k]];
                if (tile != '#') {
                    if (i == powerFlames || tile == '*') { // Nếu là flame cuối hoặc gặp vật cản
                        flameList.add(new Flame(xBomb + dx[k], yBomb + dy[k], direction[k], true, Sprite.explosion_vertical.getFxImage()));
                        break;
                    } else { // Flame bình thường
                        flameList.add(new Flame(xBomb + dx[k], yBomb + dy[k], direction[k], false, Sprite.explosion_vertical.getFxImage()));
                    }
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Cho flame xuất hiện theo hướng direction.
     */
    @Override
    public void update() {
        animation = 20;

       if (Bomb.timeExplode <= 0) {
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
        if (!active || !entity.active || entity instanceof Flame || entity instanceof Item) {
            return;
        }

        int xFlame = getXMap();
        int yFlame = getYMap();
        int xEntity = entity.getXMap();
        int yEntity = entity.getYMap();

        // Va chạm với các entity khác.
        if (xEntity == xFlame && yEntity == yFlame) {
            // Nếu gặp bomb thì cho nổ
            if (entity instanceof Bomb) {
                Bomb bomb = (Bomb) entity;
                bomb.timeExplode = 0; // Cho nổ luôn
                return;
            }
            // Gặp các entity khác
            entity.active = false;
        }
    }
}
