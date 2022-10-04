package uet.oop.bomberman.entities.activeObject.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Character.Character;
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.loadMap;

import java.util.ArrayList;
import java.util.List;


public class Bomb extends activeEntity {

    protected int timeAfter = 40;
    public int powerFlames = 0;
    public static int timeExplode = 120;
    private boolean played = false;
    public boolean added = false;
    public boolean exploded = false;

    public static List<Flame> flameList = new ArrayList<>();

    public Bomb(int xUnit, int yUnit, Image img, int powerFlames) {
        super(xUnit, yUnit, img);
        this.powerFlames = powerFlames;
        this.exploded = false;
        this.delete = false;
        createFlame();
    }

    public void createFlame() {
        int direction[] = {0, 1, 2, 3};
        for (int i = 1; i <= powerFlames; i++) {
            int x = getY() / Sprite.SCALED_SIZE;
            int y = getX() / Sprite.SCALED_SIZE;
            for (int k = 0; k < 4; k++) {
                // Mảng dx, dy dùng để duyệt theo 4 góc
                int[] dx = {0, 0, -i, i};
                int[] dy = {-i, i, 0, 0};
                char tile = loadMap.map[x + dx[k]][y + dy[k]];
                if (tile != '#') {
                    // Nếu là flame cuối hoặc gặp tường
                    if (i == powerFlames || tile == '*') {
                        flameList.add(new Flame(x + dx[k], y + dy[k], direction[k], true, Sprite.explosion_vertical.getFxImage()));
                        break;
                    } else {
                        flameList.add(new Flame(x + dx[k], y + dy[k], direction[k], false, Sprite.explosion_vertical.getFxImage()));
                    }
                } else {
                    break;
                }
            }
        }

      /*  for (int i = 1; i <= powerFlames; i++) {
            int x = bomb.getX() / Sprite.SCALED_SIZE;
            int y = bomb.getY() / Sprite.SCALED_SIZE;
            char tile = loadMap.map[y + i][x];
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
            int x = bomb.getX() / Sprite.SCALED_SIZE;
            int y = bomb.getY() / Sprite.SCALED_SIZE;
            char tile = loadMap.map[y][x + i];
            if (tile != '#') {
                // Nếu là flame cuối hoặc gặp tường
                if (i == powerFlames || tile == '*') {
                    flameList.add(new Flame(x, y - i, 0, true, Sprite.explosion_vertical.getFxImage()));
                    break;
                } else {
                    flameList.add(new Flame(x, y - i, 0, true, Sprite.explosion_vertical.getFxImage()));
                }
            } else {
                break;
            }
        }*/
    }

    public void update() {
        if (timeExplode > 0) {
            timeExplode--;
            setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timeExplode, 20).getFxImage());
        } else {
            if (!played) {
                // new SoundManager("sound/boom.wav", "default");
                played = true;
            }

            if (!added) {
                BombermanGame.activeObjects.addAll(flameList);
                added = true;
            }
            exploded = true;
            timeAfter--; // Đếm ngược thời gian bom sau khi nổ
            if (timeAfter < 0) { // Nếu đã hết thời gian sau khi nổ
                delete = true;
            }
            // Animation bom nổ
            setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, timeAfter, 20).getFxImage());
        }
    }

    public void collide(Character character) {
        // Bom chưa nổ hoặc nhân vật chết rồi thì bỏ qua
        if(!exploded || !character.active) {
            return;
        }
        int x = getX() / Sprite.SCALED_SIZE;
        int y = getY() / Sprite.SCALED_SIZE;

        int xCharacter = character.getX() / Sprite.SCALED_SIZE;
        int yCharacter = character.getY() / Sprite.SCALED_SIZE;

        if (x == xCharacter && y == yCharacter) {
            character.active = false;
        }
    }
}




