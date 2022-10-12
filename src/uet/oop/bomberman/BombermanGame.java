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
import uet.oop.bomberman.entities.activeObject.activeEntity;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
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

    public static boolean portalCheck = false; // Kiểm tra xem đã qua màn chưa
    public static int gameTime = 12000; // Mỗi màn chơi 200s
    public static int delayTime = 100;

    public static int playerCount = 0;
    public static String gameState = "newGame";

    private static GraphicsContext gc;
    private static Canvas canvas;

    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<activeEntity> activeObjects = new ArrayList<>();
    public static char[][] map;
    public static char[][] bombMap = new char[HEIGHT][WIDTH];
    public static Bomber bomber1, bomber2;


    //-------SOUND--------
    public static Sound bombSound = new Sound("bombSound");
    public static Sound deadSound = new Sound("deadSound");
    public static Sound powerUpSound = new Sound("powerup");
    public static Sound victorySound = new Sound("victorySound");
    public static Sound defeatSound = new Sound("defeatSound");
    public static Sound bacgroundSound = new Sound("start");
    //-----------------------------------------------

    public static Text time, Level;
    public static ImageView resumeView, pauseView, muteView, unMuteView;

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

        Image image = new Image(new File("res/textures/levelUp.png").toURI().toString());
        ImageView levelUp = new ImageView(image);
        levelUp.setVisible(false);

        Image pause = new Image(new File("res/textures/pause.png").toURI().toString());
        pauseView = new ImageView(pause);
        pauseView.setX(20);
        pauseView.setY(0);
        pauseView.setFitHeight(32);
        pauseView.setFitWidth(32);

        Image resume = new Image(new File("res/textures/resume.png").toURI().toString());
        resumeView = new ImageView(resume);
        resumeView.setX(20);
        resumeView.setY(0);
        resumeView.setFitHeight(32);
        resumeView.setFitWidth(32);
        resumeView.setVisible(false);

        Image mute = new Image(new File("res/textures/mute.png").toURI().toString());
        muteView = new ImageView(mute);
        muteView.setX(700);
        muteView.setY(0);
        muteView.setFitHeight(32);
        muteView.setFitWidth(32);

        Image unMute = new Image(new File("res/textures/unMute.png").toURI().toString());
        unMuteView = new ImageView(unMute);
        unMuteView.setX(700);
        unMuteView.setY(0);
        unMuteView.setFitHeight(32);
        unMuteView.setFitWidth(32);

        if (bacgroundSound.isPlaying()) {
            unMuteView.setVisible(false);
        } else muteView.setVisible(false);

        time = new Text("Times: " + gameTime);
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
        gameControl.getChildren().addAll(pauseView, resumeView, Level, time, muteView, unMuteView);
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
                    resetGame();
                    if (level == 0) level = 1;
                    if (playerCount > 0 && level > 0) {
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
                    bombSound.play(false, 0);
                    powerUpSound.play(false, 0);
                    render();
                    try {
                        update();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    if (delayTime-- == 0) {
                        level = 0;
                        defeatSound.play(true, 0);
                        stage.setScene(scene3); // Chuyển sang scene gameOver
                    }
                    return;
                }

                if (gameState.equals("levelUp")) {
                    powerUpSound.play(true, 0);
                    if (level > 3) return;
                    if (level == 3) { // Đã chơi qua hết tất cả level
                        gameState = "winning";
                        victorySound.play(true, 0);
                        stage.setScene(scene4); // Chuyển qua scene victory
                        return;
                    }
                    levelUp.setVisible(true);
                    if (delayTime-- == 0) {
                        powerUpSound.play(false, 0);
                        gameState = "newGame";
                        portalCheck = false;
                        level++;
                        levelUp.setVisible(false);
                    }
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
        Level.setText("Level: " + level);
        time.setText("Time: " + gameTime-- / 60);

        pauseView.setOnMouseClicked(mouseEvent -> {
            bacgroundSound.play(false, 0);
            bombSound.play(false, 0);
            deadSound.play(false, 0);
            powerUpSound.play(false, 0);
            pauseView.setVisible(false);
            resumeView.setVisible(true);
            gameState = "Pause";
        });

        resumeView.setOnMouseClicked(mouseEvent -> {
            bacgroundSound.play(true, 0);
            resumeView.setVisible(false);
            pauseView.setVisible(true);
            gameState = "Running";
        });

        muteView.setOnMouseClicked(mouseEvent -> {
            bacgroundSound.play(true, 0);
            muteView.setVisible(false);
            unMuteView.setVisible(true);
        });

        unMuteView.setOnMouseClicked(mouseEvent -> {
            bacgroundSound.play(false, 0);
          /*  bombSound.play(false, 0);
            deadSound.play(false, 0);
            powerUpSound.play(false, 0);*/
            unMuteView.setVisible(false);
            muteView.setVisible(true);

        });

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
            gameState = "gameOver";
            return;
        }

        if (portalCheck) {
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
        gameTime = 12000;
        delayTime = 100;
        victorySound.play(false, 0);
        defeatSound.play(false, 0);
        bombSound.play(false, 0);
        deadSound.play(false, 0);
        powerUpSound.play(false, 0);
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
        scene2.setOnKeyReleased(keyEvent -> bomber2.handleKeyEvent2(keyEvent));
    }
}
