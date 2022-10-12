package uet.oop.bomberman.graphics;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Brick;
import uet.oop.bomberman.entities.activeObject.Character.Balloon;
import uet.oop.bomberman.entities.activeObject.Character.Doll;
import uet.oop.bomberman.entities.activeObject.Character.Minvo;
import uet.oop.bomberman.entities.activeObject.Character.Oneal;
import uet.oop.bomberman.entities.activeObject.Item.bombItem;
import uet.oop.bomberman.entities.activeObject.Item.flameItem;
import uet.oop.bomberman.entities.activeObject.Item.bombPass;
import uet.oop.bomberman.entities.activeObject.Item.wallItem;
import uet.oop.bomberman.entities.activeObject.Item.Portal;
import uet.oop.bomberman.entities.stillObject.Grass;
import uet.oop.bomberman.entities.stillObject.Wall;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static uet.oop.bomberman.BombermanGame.*;

/**
 * Load map từ file cấu hình Level.
 */
public final class Map {
    public static char[][] readMap(String path) throws IOException {
        char[][] map = new char[BombermanGame.HEIGHT][BombermanGame.WIDTH];
        File reader = new File(path);
        Scanner in = new Scanner(reader);

        try {
            level = in.nextInt();
            height = in.nextInt();
            width = in.nextInt();
            in.nextLine();
            for (int i = 0; i < height; i++) {
                String line = in.nextLine();
                for (int j = 0; j < width; j++) {
                    map[i][j] = line.charAt(j);
                    bombMap[i][j] = ' ';
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return map;
    }

    public static void loadMap() {
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                switch (BombermanGame.map[i][j]) {
                    // Wall
                    case '#':
                        // Add wall
                        stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                        break;
                    // Brick
                    case '*':
                        // Layer 1: Add grass
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        // Layer 2: Add brick
                        activeObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    // Portal
                    case 'x':
                        BombermanGame.map[i][j] = '*';
                        // Layer 1: Add grass
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        // Layer 2: Add portal
                        activeObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        // Layer 3: Add brick
                        activeObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    // Balloon
                    case '1':
                        // Layer 1: Add grass
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        // Layer 2: Add balloon
                        activeObjects.add(new Balloon(j, i, Sprite.balloon_right1.getFxImage()));
                        break;
                    // Oneal
                    case '2':
                        // Layer 1: Add grass
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        // Layer 2: Add balloon
                        activeObjects.add(new Oneal(j, i, Sprite.oneal_right1.getFxImage()));
                        break;
                    // Doll
                    case '3':
                        // Layer 1: Add grass
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        // Layer 2: Add doll
                        activeObjects.add(new Doll(j, i, Sprite.doll_right1.getFxImage()));
                        break;
                    // Minvo
                    case '4':
                        // Layer 1: Add grass
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        // Layer 2: Add minvo
                        activeObjects.add(new Minvo(j, i, Sprite.minvo_right1.getFxImage()));
                        break;
                    // Bomb Item
                    case 'b':
                        BombermanGame.map[i][j] = '*';
                        // Layer 1: Add grass
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        // Layer 2: Add powerup_bomb
                        activeObjects.add(new bombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        // Layer 3: Add brick
                        activeObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    // Flame Item
                    case 'f':
                        BombermanGame.map[i][j] = '*';
                        // Layer 1: Add grass
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        // Layer 2: Add powerup_flame
                        activeObjects.add(new flameItem(j, i, Sprite.powerup_flamepass.getFxImage()));
                        // Layer 3: Add brick
                        activeObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    // BombPass Item
                    case 'o':
                        BombermanGame.map[i][j] = '*';
                        // Layer 1: Add grass
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        // Layer 2: Add powerup_speed
                        activeObjects.add(new bombPass(j, i, Sprite.powerup_bombpass.getFxImage()));
                        // Layer 3: Add brick
                        activeObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    // Wall item
                    case 'w':
                        BombermanGame.map[i][j] = '*';
                        // Layer 1: Add grass
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        // Layer 2: Add powerup_speed
                        activeObjects.add(new wallItem(j, i, Sprite.powerup_wallpass.getFxImage()));
                        // Layer 3: Add brick
                        activeObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    // Grass
                    default:
                        // Add grass
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        break;
                }
            }
        }
    }
}