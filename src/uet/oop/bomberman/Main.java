package uet.oop.bomberman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

/**
 * Hàm main chính của Game, chứa các scene con
 */
public class Main extends Application {
    public static Stage stage;
    public static Scene scene1; // Scene chứa menu
    public static Scene scene2; // Scene chứa gameboard
    public static Scene scene3; // Scene gameover
    public static Scene scene4; // Scene victory

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("BOMBERMAN GAME ẢO MA CANADA");

        scene1 = BombermanGame.createScene1();
        scene2 = BombermanGame.createScene2();
        scene3 = BombermanGame.createScene3();
        scene4 = BombermanGame.createScene4();

        stage.setScene(scene1);
        Image icon = new Image(new File("res/textures/Bomberman-icon.png").toURI().toString());
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(Main.class);
    }

}
