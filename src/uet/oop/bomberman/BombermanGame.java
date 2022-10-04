package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.activeObject.Character.Balloon;
import uet.oop.bomberman.entities.activeObject.Character.Bomber;
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.loadMap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 25;
    public static final int HEIGHT = 20;

    public static int width = 0;
    public static int height = 0;
    public static int level = 1;

    private GraphicsContext gc;
    private Canvas canvas;

    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<activeEntity> activeObjects = new ArrayList<>();

    public static Bomber bomber;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        stage.setTitle("Bomberman ver 1.0");
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();

        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Tạo menu game
        //Menu.createMenu(stage, root, scene);

        new loadMap("C:\\Users\\TRAM ANH\\OneDrive - vnu.edu.vn\\Dai hoc\\Kì I (2022-2023)\\oop\\bomberman-starter-starter-2\\bomberman-starter-starter-2\\res\\levels\\Level1.txt");

        bomber = new Bomber(1, 1, Sprite.player_right.getImage());
        activeObjects.add(bomber);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                bomber.handleKeyEvent(keyEvent);
            }
        });

        // Thêm vào scene
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

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

    }

    public void update() throws FileNotFoundException {
        for (Entity entity : stillObjects) {
            entity.update();
        }

        for (activeEntity entity : activeObjects) {
            entity.update();
        }

        for (activeEntity entity : activeObjects) {
           if (entity.delete) {
               activeObjects.remove(entity);
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        for (activeEntity entity : activeObjects) {
            if (entity.active) {
                entity.render(gc);
            }
        }
    }
}
