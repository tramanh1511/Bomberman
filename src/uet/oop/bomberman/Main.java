package uet.oop.bomberman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Hàm main chính của Game, chứa các scene con
 */
public class Main extends Application {
    public static Stage stage;
    public static Scene scene1; // Scene chứa menu
    public static Scene scene2; // Scene chứa gameboard
    public static Scene scene3; // Scene win hay gameover

    @Override
    public void start(Stage primaryStage) throws IOException, URISyntaxException {
        stage = primaryStage;
        stage.setTitle("BOMBERMAN VER 1.0");

        scene1 = BombermanGame.createScene1();
        scene2 = BombermanGame.createScene2();

        stage.setScene(scene1);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(Main.class);
    }

}
