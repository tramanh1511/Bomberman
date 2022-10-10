package uet.oop.bomberman.entities.activeObject.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Sprite;
import java.util.ArrayList;
import java.util.List;

public class Bomb extends activeEntity {
    protected int timeAfter = 30;
    public int powerFlames = 0;
    public int timeExplode = 90;
    public boolean added = false;

    public List<Flame> flameList = new ArrayList<>();

    public Bomb(int xUnit, int yUnit, Image img, int powerFlames) {
        super(xUnit, yUnit, img);
        this.powerFlames = powerFlames;
        active = true;
        delete = false;
        createFlame();
        System.out.println(powerFlames);
    }

    public void createFlame() {
        int[] direction = {0, 1, 2, 3};
        /*for (int i = 1; i <= powerFlames; i++) {
            int xBomb = getX() / Sprite.SCALED_SIZE;
            int yBomb = getY() / Sprite.SCALED_SIZE;
            for (int k = 0; k < 4; k++) {
                // Mảng dx, dy dùng để duyệt theo 4 hướng up, down, left, right.
                int[] dy = {-i, i, 0, 0};
                int[] dx = {0, 0, -i, i};
                char tile = loadBombermanGame.map[yBomb + dy[k]][xBomb + dx[k]];
                if (tile != '#') {
                    // Nếu là flame cuối hoặc gặp tường
                    if (i == powerFlames || tile == '*') {
                        flameList.add(new Flame(xBomb + dx[k], yBomb + dy[k], direction[k], true, Sprite.explosion_vertical.getFxImage()));
                        break;
                    } else {
                        flameList.add(new Flame(xBomb + dx[k], yBomb + dy[k], direction[k], false, Sprite.explosion_vertical.getFxImage()));
                    }
                } else {
                    break;
                }
            }
        }*/

        for (int i = 1; i <= powerFlames; i++) {
            int x = getX() / Sprite.SCALED_SIZE;
            int y = getY() / Sprite.SCALED_SIZE;
            char tile = BombermanGame.map[y - i][x];
            if (tile != '#') {
                // Nếu là flame cuối hoặc gặp tường
                if (i == powerFlames || tile == '*') {
                    flameList.add(new Flame(x, y - i, 0, true, Sprite.explosion_vertical.getFxImage()));
                    break;
                } else {
                    flameList.add(new Flame(x, y - i, 0, false, Sprite.explosion_vertical.getFxImage()));
                }
            } else {
                break;
            }
        }

        for (int i = 1; i <= powerFlames; i++) {
            int x = getX() / Sprite.SCALED_SIZE;
            int y = getY() / Sprite.SCALED_SIZE;
            char tile = BombermanGame.map[y + i][x];
            if (tile != '#') {
                // Nếu là flame cuối hoặc gặp tường
                if (i == powerFlames || tile == '*') {
                    flameList.add(new Flame(x, y + i, 1, true, Sprite.explosion_vertical.getFxImage()));
                    break;
                } else {
                    flameList.add(new Flame(x, y + i, 1, false, Sprite.explosion_vertical.getFxImage()));
                }
            } else {
                break;
            }
        }

        for (int i = 1; i <= powerFlames; i++) {
            int x = getX() / Sprite.SCALED_SIZE;
            int y = getY() / Sprite.SCALED_SIZE;
            char tile = BombermanGame.map[y][x - i];
            if (tile != '#') {
                // Nếu là flame cuối hoặc gặp tường
                if (i == powerFlames || tile == '*') {
                    flameList.add(new Flame(x - i, y, 2, true, Sprite.explosion_vertical.getFxImage()));
                    break;
                } else {
                    flameList.add(new Flame(x - i, y, 2, false, Sprite.explosion_vertical.getFxImage()));
                }
            } else {
                break;
            }
        }

        for (int i = 1; i <= powerFlames; i++) {
            int x = getX() / Sprite.SCALED_SIZE;
            int y = getY() / Sprite.SCALED_SIZE;
            char tile = BombermanGame.map[y][x + i];
            if (tile != '#') {
                // Nếu là flame cuối hoặc gặp tường
                if (i == powerFlames || tile == '*') {
                    flameList.add(new Flame(x + i, y, 3, true, Sprite.explosion_vertical.getFxImage()));
                    break;
                } else {
                    flameList.add(new Flame(x + i, y, 3, false, Sprite.explosion_vertical.getFxImage()));
                }
            } else {
                break;
            }
        }

    }

    public void update() {
        if (timeExplode > 0) { // Chưa nổ
            timeExplode--;
            setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timeExplode, 20).getFxImage());
        } else {
            if (!added) {
                BombermanGame.activeObjects.addAll(flameList);
                added = true;
            }
            timeAfter--; // Đếm ngược thời gian bom khi nổ
            if (timeAfter < 0) { // Nếu đã hết thời gian sau khi nổ
                delete = true;
                active = false;
            }
            // Animation bom nổ
            setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, timeAfter, 20).getFxImage());
            BombermanGame.bombSound.play(false, 0);
        }
    }

    @Override
    public void collide(activeEntity character) {

    }
}




