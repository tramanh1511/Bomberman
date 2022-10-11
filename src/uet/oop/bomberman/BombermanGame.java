package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.Menu.Menu;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.activeObject.Character.Bomber;
import uet.oop.bomberman.entities.activeObject.Item.Item;
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.Main.*;


public class BombermanGame {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 19;

    public static int width = 0;
    public static int height = 0;
    public static int level = 1;

    public static boolean levelUp = false; // Kiểm tra xem đã qua màn chưa
    public static int gameTime = 7200; // Mỗi màn chơi 120s
    public static int delayTime = 60;

    public static int playerCount = 0;
    public static String gameState = "newGame";

    private static GraphicsContext gc;
    private static Canvas canvas;

    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<activeEntity> activeObjects = new ArrayList<>();
    public static char[][] map;
    public static Bomber bomber1, bomber2;

    /*
    -------SOUND--------
     */
    public static Sound bombSound = new Sound("bombSound");
    public static Sound deadSound = new Sound("deadSound");
    public static Sound powerUpSound = new Sound("powerup");

    public static Text time, Level;

    /**
     * Tạo scene của menu.
     */
    public static Scene createScene1() {
        return Menu.menu();
    }

    /**
     * Tạo scene của gameboard.
     */
    public static Scene createScene2() {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        Image image = new Image("E:\\bomberman-starter-starter-2\\bomberman-starter-starter-2\\res\\textures\\levelUp.png");
        ImageView levelUp = new ImageView(image);
        levelUp.setVisible(false);

        Image pause = new Image("E:\\bomberman-starter-starter-2\\bomberman-starter-starter-2\\res\\textures\\pause.png");
        Image resume = new Image("E:\\bomberman-starter-starter-2\\bomberman-starter-starter-2\\res\\textures\\resume.png");
        ImageView pauseView = new ImageView(pause);
        pauseView.setX(20);
        pauseView.setY(0);
        pauseView.setFitHeight(32);
        pauseView.setFitWidth(32);

        time = new Text("Times: 120");
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setFill(Color.WHITE);
        time.setX(200);
        time.setY(22);

        Level = new Text("Level: " + level);
        Level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Level.setFill(Color.WHITE);
        Level.setX(100);
        Level.setY(22);

        Pane gameControl = new Pane();
        gameControl.getChildren().addAll(pauseView, Level, time);
        gameControl.setMinSize(800, 640);
        gameControl.setStyle("-fx-background-color: #427235");

        // Tao root container
        Group root = new Group();
        root.getChildren().addAll(gameControl, canvas, levelUp);
        canvas.setTranslateY(32);

        // Tao scene
        scene2 = new Scene(root);

        resetGame();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (gameState.equals("newGame")) {
                    if (playerCount > 0) {
                        try {
                            map = Map.readMap("res/levels/Level" + level + ".txt");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Map.loadMap();
                        if (playerCount == 1) addOnePlayer();
                        else if (playerCount == 2) addTwoPlayer();
                    }
                }

                if (gameState.equals("Running")) {
                    render();
                    try {
                        update();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (gameState.equals("Pause")) {
                    render();
                }

                if (gameState.equals("gameOver")) {
                    render();
                    try {
                        update();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    if (delayTime-- == 0 )stage.setScene(scene3); // Chuyển sang scene gameOver
                    return;
                }

                if (gameState.equals("levelUp")) {
                    if (level == 3) { // Đã chơi qua hết tất cả level
                        stage.setScene(scene4); // Chuyển qua scene victory
                        return;
                    }
                    levelUp.setVisible(true);
                    level++;
                    resetGame();
                    try {
                        map = Map.readMap("res/levels/Level" + level + ".txt");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Map.loadMap();
                    if (playerCount == 1) {
                        addOnePlayer();
                    }
                    if (playerCount == 2) {
                        addTwoPlayer();
                    }
                    levelUp.setVisible(false);
                    gameState = "running";
                }
            }
        };
        timer.start();
        return scene2;
    }

    public static Scene createScene3() {
        return Menu.GAMEOVER();
    }

    public static Scene createScene4() {
        return Menu.VICTORY();
    }

    /**
     * Update 60s 1 lần
     */
    public static void update() throws FileNotFoundException {
        Level.setText("Level: " + BombermanGame.level);
        time.setText("Time: " + BombermanGame.gameTime-- / 60);

        for (Entity entity : stillObjects) {
            entity.update();
        }

        for (int i = 0; i < activeObjects.size(); i++) {
            activeObjects.get(i).update();
            for (activeEntity activeObject : activeObjects) {
                activeObjects.get(i).collide(activeObject);
            }
        }

        if (playerCount == 1 && !bomber1.active) {
            gameState = "gameOver";
            return;
        }

        if (playerCount == 2 && !bomber1.active && !bomber2.active) {
            gameState = "gameOver";
            return;
        }

        if (gameTime < 0) {
            gameState = "gameover";
            return;
        }

        if (levelUp) {
            gameState = "levelUp";
            return;
        }

        for (int i = 0; i < activeObjects.size(); i++) {
            if (activeObjects.get(i).delete) {
                activeObjects.remove(activeObjects.get(i));
            }
        }
    }

    /**
     * Render 60s 1 lần
     */
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

    private static void resetGame() {
        activeObjects.clear();
        stillObjects.clear();
        map = new char[WIDTH][HEIGHT];
        gameTime = 7000;
        level = 1;
    }

    private static void addOnePlayer() {
        bomber1 = new Bomber(1, 1, Sprite.player_right.getImage());
        activeObjects.add(bomber1);
        gameState = "Running";
        scene2.setOnKeyPressed(keyEvent -> bomber1.handleKeyEvent1(keyEvent));
    }

    private static void addTwoPlayer() {
        bomber1 = new Bomber(1, 1, Sprite.player_right.getImage());
        bomber2 = new Bomber(1, HEIGHT - 2, Sprite.player_right.getImage());
        activeObjects.add(bomber1);
        activeObjects.add(bomber2);
        gameState = "Running";
        scene2.setOnKeyPressed(keyEvent -> bomber1.handleKeyEvent1(keyEvent));
        scene2.setOnKeyPressed(keyEvent -> bomber2.handleKeyEvent2(keyEvent));
    }
}
