package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.loadMap;

import java.awt.*;
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
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> activeObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Thêm vào scene
        stage.setScene(scene);
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

        // Load map từ file cấu hình
        try {
            new loadMap("res/levels/Level1.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void update() throws FileNotFoundException {
        for (Entity entity : entities) {
            entity.update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        activeObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
