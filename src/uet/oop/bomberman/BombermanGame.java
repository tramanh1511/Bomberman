package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Menu.Menu;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.activeObject.Character.Bomber;
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.Main.*;


public class BombermanGame {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 20;

    public static int width = 0;
    public static int height = 0;
    public static int level = 1;

    public static boolean levelUp = false;

    private static GraphicsContext gc;
    private static Canvas canvas;

    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<activeEntity> activeObjects = new ArrayList<>();
    public static char[][] map;
    public static Bomber bomber;

    /*
    -------SOUND--------
     */
    public static Sound bombSound = new Sound("bombSound");
    public static Sound deadSound = new Sound("deadSound");
    public static Sound powerUpSound = new Sound("powerup");

    /**
     * Tạo scene của menu.
     */
    public static Scene createScene1() throws IOException, URISyntaxException {
        return Menu.menu();
    }

    /**
     * Tạo scene của gameboard.
     */
    public static Scene createScene2() throws IOException {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();

        root.getChildren().add(canvas);

        // Tao scene
        scene2 = new Scene(root);

       map = Map.readMap("C:\\Users\\TRAM ANH\\OneDrive - vnu.edu.vn\\Dai hoc\\Kì I (2022-2023)\\oop\\bomberman-starter-starter-2\\bomberman-starter-starter-2\\res\\levels\\Level1.txt");
        //map = Map.readMap("Level1.txt");
        Map.loadMap();

        bomber = new Bomber(1, 1, Sprite.player_right.getImage());
        activeObjects.add(bomber);
        scene2.setOnKeyPressed(keyEvent -> {
            bomber.handleKeyEvent(keyEvent);
        });

        //File file = new File(".");
      //  for(String fileNames : file.list()) System.out.println(fileNames);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                try {
                    update();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.start();
        return scene2;
    }

    public static Scene createScene3() {
        Group root = new Group();
        scene3 = new Scene(root);

        return scene3;

    }

    public static void update() throws FileNotFoundException {
        for (Entity entity : stillObjects) {
            entity.update();
        }

        for (int i = 0; i < activeObjects.size(); i++) {
            activeObjects.get(i).update();
            for (activeEntity activeObject : activeObjects) {
                activeObjects.get(i).collide(activeObject);
            }
        }

        for (int i = 0; i < activeObjects.size(); i++) {
            if (activeObjects.get(i).delete) {
                activeObjects.remove(activeObjects.get(i));
            }
        }
    }

    public static void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        for (activeEntity entity : activeObjects) {
            if (!entity.delete) {
                powerUpSound.play(false, 0);
                entity.render(gc);
            }
        }
    }
}
