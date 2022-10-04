package uet.oop.bomberman.entities.activeObject.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.activeObject.Character.Character;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.loadMap;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.bomber;

public class Bomb extends Entity {

    //
    protected int timeToExplode = 120;  // thời gian chờ trước khi nổ, 2s
    protected int _timeAfter = 40;
    public static int bombStatus = 0;
    public static int length = 0;
    public static int timeExplode = 0;
    private static long timeBomb;
    private boolean played = false;
    public boolean added = false;
    public boolean exploded = false;

    private static Entity bomb;

    public static List<Flame> flameList = new ArrayList<>();

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void putBomb() {
        bombStatus = 1;
        timeBomb = System.currentTimeMillis();

        int x = Math.round(bomber.getX());
        int y = Math.round(bomber.getY());

        bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
      // setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, 30, 20).getFxImage());
      //  createFlame(4);
    }

    public static void createFlame(int length) {
        for (int i = 1; i <= length; i++) {
            int x = bomb.getX() / Sprite.SCALED_SIZE;
            int y = bomb.getY() / Sprite.SCALED_SIZE;
            char tile = loadMap.map[y][x + i];
            if (tile != '#') {
                // Nếu là flame cuối hoặc gặp tường
                if (i == length || tile == '*') {
                    flameList.add(new Flame(x + i, y, 3, true, Sprite.explosion_vertical.getFxImage()));
                    // flameList.add(new Flame(x, y - i, 0, true, Sprite.explosion_vertical.getFxImage()));*/
                    break;
                } else {
                    flameList.add(new Flame(x + i, y, 3, false, Sprite.explosion_vertical.getFxImage()));
                  /*  flameList.add(new Flame(x, y + i, 1, false, Sprite.explosion_vertical.getFxImage()));
                    flameList.add(new Flame(x - i, y, 2, false, Sprite.explosion_vertical.getFxImage()));
                    flameList.add(new Flame(x, y - i, 0, true, Sprite.explosion_vertical.getFxImage()));*/
                }
            } else {
                break;
            }
        }

        for (int i = 1; i <= length; i++) {
            int x = bomb.getX() / Sprite.SCALED_SIZE;
            int y = bomb.getY() / Sprite.SCALED_SIZE;
            char tile = loadMap.map[y + i][x];
            if (tile != '#') {
                // Nếu là flame cuối hoặc gặp tường
                if (i == length || tile == '*') {
                    flameList.add(new Flame(x, y + i, 1, true, Sprite.explosion_vertical.getFxImage()));
                    break;
                } else {
                    flameList.add(new Flame(x, y + i, 1, false, Sprite.explosion_vertical.getFxImage()));
                }
            } else {
                break;
            }
        }

        for (int i = 1; i <= length; i++) {
            int x = bomb.getX() / Sprite.SCALED_SIZE;
            int y = bomb.getY() / Sprite.SCALED_SIZE;
            char tile = loadMap.map[y][x + i];
            if (tile != '#') {
                // Nếu là flame cuối hoặc gặp tường
                if (i == length || tile == '*') {
                    flameList.add(new Flame(x, y - i, 0, true, Sprite.explosion_vertical.getFxImage()));
                    break;
                } else {
                    flameList.add(new Flame(x, y - i, 0, true, Sprite.explosion_vertical.getFxImage()));
                }
            } else {
                break;
            }
        }
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
            _timeAfter--; // Đếm ngược thời gian bom sau khi nổ
            if (_timeAfter < 0) { // Nếu đã hết thời gian sau khi nổ
                loadMap.map[(getY() + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE][(getX() + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE] = ' ';
            }
            // Animation bom nổ
            setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, _timeAfter, 20).getFxImage());
        }
    }
}




