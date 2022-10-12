package uet.oop.bomberman.Menu;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;

import java.io.File;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.Main.*;

/**
 * menu0 game chính.
 */
public class Menu {
    public static Scene menu() {
        Pane pane = new Pane();
        pane.setPrefSize(800, 640);

        Group root = new Group();
        scene1 = new Scene(root);

        Gamemenu gamemenu = new Gamemenu();

        Image image = new Image(new File("res/textures/menu.png").toURI().toString());
        ImageView background = new ImageView(image);
        // Tao root container
        root.getChildren().addAll(background, gamemenu);

        scene1.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                System.exit(0);
            }
        });

        return scene1;
    }

    public static Scene GAMEOVER() {
        Pane pane = new Pane();
        pane.setPrefSize(800, 608);

        Group root = new Group();
        gameStatus gameOver = new gameStatus();
        scene3 = new Scene(root);

        Image image = new Image(new File("res/textures/gameOver.png").toURI().toString());
        ImageView background = new ImageView(image);

        root.getChildren().addAll(background, gameOver);

        return scene3;
    }

    public static Scene VICTORY() {
        Pane pane = new Pane();
        pane.setPrefSize(800, 608);

        Group root = new Group();
        gameStatus gameOver = new gameStatus();
        scene4 = new Scene(root);

        Image image = new Image(new File("res/textures/winGame.png").toURI().toString());
        ImageView background = new ImageView(image);

        root.getChildren().addAll(background, gameOver);

        return scene4;
    }

    /**
     * Xây dựng các button.
     */
    private static class Gamemenu extends Parent {
        public Gamemenu() {
            // Cửa sổ chính chứa các button
            VBox menu0 = new VBox(10);
            VBox menu1 = new VBox(10);
            VBox menu2 = new VBox(10);

            menu0.setTranslateX(300);
            menu0.setTranslateY(350);
            menu1.setTranslateX(100);
            menu1.setTranslateY(250);
            menu2.setTranslateX(300);
            menu2.setTranslateY(350);

            final int offset = 400;

            menu1.setTranslateX(offset);
            menu2.setTranslateX(offset);

            // One player button
            menuButton onePlayerButton = new menuButton("ONE PLAYER");
            onePlayerButton.setOnMouseClicked(event -> {
                BombermanGame.playerCount = 1;
                BombermanGame.gameState = "newGame";
                stage.setScene(scene2);
            });

            // Two player button
            menuButton twoPlayerButton = new menuButton("TWO PLAYER");
            twoPlayerButton.setOnMouseClicked(event -> {
                BombermanGame.playerCount = 2;
                BombermanGame.gameState = "newGame";
                stage.setScene(scene2);
            });

            // tutorial Button
            menuButton tutorialButton = new menuButton("TUTORIAL");
            tutorialButton.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(event1 -> getChildren().remove(menu0));
            });

            String tutorialName = new File("res/textures/tutorial.png").toURI().toString();
            textMenu tutorial = new textMenu(tutorialName, 300, 250);

            // Sound Button
            bacgroundSound.play(true, 0);

            menuButton soundButton = new menuButton("SOUND");
            soundButton.setOnMouseClicked(event -> {
                getChildren().add(menu2);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu2);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(event1 -> getChildren().remove(menu0));

            });

            // Mute Sound
            menuButton mute = new menuButton("MUTE");
            mute.setOnMouseClicked(event -> bacgroundSound.play(false, 0));

            // Unmute Sound
            menuButton unMute = new menuButton("UNMUTE");
            unMute.setOnMouseClicked(event -> bacgroundSound.play(true, 0));

            // Back from menu 2 to menu 0
            menuButton backButton2 = new menuButton("BACK");
            backButton2.setOnMouseClicked(event -> {
                getChildren().add(menu0);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu2);
                tt.setToX(menu2.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(menu2.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(event1 -> getChildren().remove(menu2));
            });

            // Exit button
            menuButton exitButton = new menuButton("EXIT");
            exitButton.setOnMouseClicked(event -> System.exit(0));

            // Back button
            menuButton backButton1 = new menuButton("BACK");
            backButton1.setOnMouseClicked(event -> {
                getChildren().add(menu0);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(event1 -> getChildren().remove(menu1));

            });

            // Thêm các button vào menu
            menu0.getChildren().addAll(onePlayerButton, twoPlayerButton, tutorialButton,
                    soundButton, exitButton);
            menu1.getChildren().addAll(tutorial, backButton1);
            menu2.getChildren().addAll(mute, unMute, backButton2);

            Rectangle bg = new Rectangle(800, 640);
            bg.setFill(Color.GREY);
            bg.setOpacity(0.2);

            getChildren().addAll(bg, menu0);
        }
    }

    /**
     * Chứa các đặc điểm của 1 button.
     */
    private static class menuButton extends StackPane {
        public menuButton(String name) {
            Text text = new Text(name);
            text.setFont(Font.font(20));
            text.setFill(Color.WHITE);
            text.setTextAlignment(TextAlignment.CENTER);

            Rectangle background = new Rectangle(250, 30);
            background.setOpacity(0.6);
            background.setFill(Color.BLACK);
            background.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER);
            setRotate(-0.5);
            getChildren().addAll(background, text);

            setOnMouseEntered(event -> {
                background.setTranslateX(10);
                text.setTranslateX(10);
                background.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });

            setOnMouseExited(event -> {
                background.setTranslateX(0);
                text.setTranslateX(0);
                background.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });

            DropShadow dropShadow = new DropShadow(50, Color.WHITE);
            dropShadow.setInput(new Glow());

            setOnMousePressed(event -> setEffect(dropShadow));
            setOnMouseReleased(event -> setEffect(null));
        }
    }

    /**
     * Load tutorial.
     */
    private static class textMenu extends StackPane {
        public textMenu(String path, int width, int height) {
            // Load tutorial image
            Image img = new Image(path);
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);

            // Load background
            Rectangle background = new Rectangle(width, height);
            background.setOpacity(0.6);
            background.setFill(Color.WHITE);
            background.setEffect(new GaussianBlur(3.5));

            // Thêm vào root
            getChildren().addAll(background, imageView);
        }
    }

    private static class gameStatus extends Parent {
        public gameStatus() {
            VBox menu0 = new VBox(10);
            menu0.setTranslateX(300);
            menu0.setTranslateY(450);

            //Replay button
            menuButton replayButton = new menuButton("REPLAY");
            replayButton.setOnMouseClicked(event -> {
                BombermanGame.gameState = "newGame";
                defeatSound.play(false, 0);
                victorySound.play(false, 0);
                stage.setScene(scene2);
            });

            // Exit button
            menuButton exitButton = new menuButton("EXIT");
            exitButton.setOnMouseClicked(event -> System.exit(0));

            // Main menu button
            menuButton mainMenuButton = new menuButton("MAIN MENU");
            mainMenuButton.setOnMouseClicked(event -> {

                defeatSound.play(false, 0);
                victorySound.play(false, 0);
                stage.setScene(scene1);
            });

            menu0.getChildren().addAll(replayButton, mainMenuButton, exitButton);
            getChildren().add(menu0);
        }
    }
}