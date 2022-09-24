package uet.oop.bomberman.graphics;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import static uet.oop.bomberman.BombermanGame.*;

/**
 * Load map từ fiel cấu hình Level.
 */

public class loadMap {
    public loadMap(String Level) throws FileNotFoundException {
        FileReader file = new FileReader(Level);
        Scanner in = new Scanner(file);
        try {
            level = in.nextInt();
            height = in.nextInt();
            width = in.nextInt();
            in.nextLine();
            for (int i = 0; i < height; i++) {
                String line = in.nextLine();
                for (int j = 0; j < width; j++) {
                    char input = line.charAt(j);
                    switch (input) {
                        // Wall
                        case '#':
                            // Add wall
                            BombermanGame.stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                            break;
                        // Brick
                        case '*':
                            // Layer 1: Add grass
                            BombermanGame.stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            // Layer 2: Add brick
                            BombermanGame.activeObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        // Portal
                        case 'x':
                            // Layer 1: Add grass
                            BombermanGame.stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            // Layer 2: Add portal
                            BombermanGame.stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                            // Layer 3: Add brick
                            BombermanGame.stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        // Bomber
                        case 'p':
                            // Layer 1: Add grass
                            BombermanGame.stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            // Layer 2: Add bomber
                            BombermanGame.activeObjects.add(new Bomber(j, i, Sprite.player_right_2.getFxImage()));
                            break;
                        // Balloon
                        case '1':
                            // Layer 1: Add grass
                            BombermanGame.stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            // Layer 2: Add balloon
                            BombermanGame.activeObjects.add(new Balloon(j, i, Sprite.balloon_left1.getFxImage()));
                            break;
                        // Oneal
                        case '2':
                            // Layer 1: Add grass
                            BombermanGame.stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            // Layer 2: Add balloon
                            BombermanGame.activeObjects.add(new Oneal(j, i, Sprite.oneal_right1.getFxImage()));
                            break;
                        // Bomb Item
                        case 'b':
                            // Layer 1: Add grass
                            BombermanGame.stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            // Layer 2: Add powerup_bomb
                            BombermanGame.activeObjects.add(new powerup_bomb(j, i, Sprite.powerup_bombs.getFxImage()));
                            break;
                        // Flame Item
                        case 'f':
                            // Layer 1: Add grass
                            BombermanGame.stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            // Layer 2: Add powerup_bomb
                            BombermanGame.activeObjects.add(new powerup_bomb(j, i, Sprite.powerup_flamepass.getFxImage()));
                            break;
                        // Speed Item
                        case 's':
                            // Layer 1: Add grass
                            BombermanGame.stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            // Layer 2: Add powerup_speed
                            BombermanGame.activeObjects.add(new powerup_bomb(j, i, Sprite.powerup_speed.getFxImage()));
                            break;
                        // Grass
                        default:
                            // Add grass
                            BombermanGame.stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
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