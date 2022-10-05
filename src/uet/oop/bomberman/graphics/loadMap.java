package uet.oop.bomberman.graphics;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.activeObject.Item.bombItem;
import uet.oop.bomberman.entities.activeObject.Brick;
import uet.oop.bomberman.entities.stillObject.Grass;
import uet.oop.bomberman.entities.activeObject.Portal;
import uet.oop.bomberman.entities.stillObject.Wall;
import uet.oop.bomberman.entities.activeObject.Character.Balloon;
import uet.oop.bomberman.entities.activeObject.Character.Oneal;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import static uet.oop.bomberman.BombermanGame.*;

/**
 * Load map từ file cấu hình Level.
 */

public class loadMap {
    public static char[][] map = new char[BombermanGame.HEIGHT][BombermanGame.WIDTH];
    public loadMap(String path) throws FileNotFoundException {
        FileReader file = new FileReader(path);
        Scanner in = new Scanner(file);
        try {
            level = in.nextInt();
            height = in.nextInt();
            width = in.nextInt();
            in.nextLine();
            for (int i = 0; i < height; i++) {
                String line = in.nextLine();
                for (int j = 0; j < width; j++) {
                    map[i][j] = line.charAt(j);
                    switch (map[i][j]) {
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
                        // Bomb Item
                        case 'b':
                            // Layer 1: Add grass
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            // Layer 2: Add powerup_bomb
                            activeObjects.add(new bombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                            break;
                        // Flame Item
                        case 'f':
                            // Layer 1: Add grass
                           stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            // Layer 2: Add powerup_bomb
                           activeObjects.add(new bombItem(j, i, Sprite.powerup_flamepass.getFxImage()));
                            break;
                        // Speed Item
                        case 's':
                            // Layer 1: Add grass
                           stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            // Layer 2: Add powerup_speed
                           activeObjects.add(new bombItem(j, i, Sprite.powerup_speed.getFxImage()));
                            break;
                        // Grass
                        default:
                            // Add grass
                           stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            break;
                    }
                }
            }
            in.close();
            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}