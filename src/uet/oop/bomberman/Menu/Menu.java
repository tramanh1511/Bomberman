package uet.oop.bomberman.Menu;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.Sound.Sound;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * menu0 game chính.
 */
public class Menu {
    public static void createMenu(Stage stage, Group root, Scene scene) throws IOException {

        Gamemenu gamemenu = new Gamemenu();

        stage.setTitle("Bomberman ver 1.0");
        Pane pane = new Pane();
        pane.setPrefSize(800, 640);

       // InputStream path = Files.newInputStream(Paths.get("res/textures/menu.png"));
        Image image = new Image("C:\\Users\\TRAM ANH\\OneDrive - vnu.edu.vn\\Dai hoc\\Kì I (2022-2023)\\oop\\bomberman-starter-starter-2\\bomberman-starter-starter-2\\res\\textures\\menu.png");
        ImageView background = new ImageView(image);

        // Tao root container
        root.getChildren().addAll(background, gamemenu);

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                System.exit(0);
            }
        });

        // Them vao scene
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Xây dựng các button.
     */
    private static class Gamemenu extends Parent {
        public Gamemenu() throws IOException {
            // Cửa sổ chính chứa các button
            VBox menu0 = new VBox(10);
            VBox menu1 = new VBox(10);
            VBox menu2 = new VBox(10);
            VBox menu3 = new VBox(10);
            VBox menu4 = new VBox(10);

            menu0.setTranslateX(300);
            menu0.setTranslateY(350);

            menu1.setTranslateX(100);
            menu1.setTranslateY(250);

            menu2.setTranslateX(300);
            menu2.setTranslateY(350);

            menu3.setTranslateX(300);
            menu3.setTranslateY(350);

            menu4.setTranslateX(300);
            menu4.setTranslateY(350);

            final int offset = 400;

            menu1.setTranslateX(offset);
            menu2.setTranslateX(offset);
            menu3.setTranslateX(offset);
            menu4.setTranslateX(offset);

            // New game button
            menuButton newGameButton = new menuButton("NEW GAME");
            newGameButton.setOnMouseClicked(event -> {
                getChildren().add(menu4);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu4);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(event1 -> {
                    getChildren().remove(menu0);
                });
                try {
                    gameWindow menuBoard = new gameWindow();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            });

            // Resume button
            menuButton resumeButton = new menuButton("RESUME");
            resumeButton.setOnMouseClicked(event -> {
                FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.setOnFinished(evt -> setVisible(false));
                ft.play();
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

                tt.setOnFinished(event1 -> {
                    getChildren().remove(menu0);
                });
            });

            String tutorialName = "C:\\Users\\TRAM ANH\\OneDrive - vnu.edu.vn\\Dai hoc\\Kì I (2022-2023)\\oop\\bomberman-starter-starter-2\\bomberman-starter-starter-2\\res\\textures\\tutorial.png";
            textMenu tutorial = new textMenu(tutorialName, 300, 250);

            // Sound Button
           Sound bacgroundSound = new Sound("start");
           bacgroundSound.play(true);

            menuButton soundButton = new menuButton("SOUND");
            soundButton.setOnMouseClicked(event -> {
                getChildren().add(menu2);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu2);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(event1 -> {
                    getChildren().remove(menu0);
                });

            });

            // Mute Sound
            menuButton mute = new menuButton("MUTE");
            mute.setOnMouseClicked(event -> {
                bacgroundSound.play(false);
            });

            // Unmute Sound
            menuButton unMute = new menuButton("UNMUTE");
            unMute.setOnMouseClicked(event -> {
                bacgroundSound.play(true);
            });

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

                tt.setOnFinished(event1 -> {
                    getChildren().remove(menu2);
                });

            });

            // About button
            menuButton aboutButton = new menuButton("ABOUT");
            aboutButton.setOnMouseClicked(event -> {
                getChildren().add(menu3);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu3);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(event1 -> {
                    getChildren().remove(menu0);
                });
            });

            // Exit button
            menuButton exitButton = new menuButton("EXIT");
            exitButton.setOnMouseClicked(event -> {
                System.exit(0);
            });

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

                tt.setOnFinished(event1 -> {
                    getChildren().remove(menu1);
                });

            });

            // Thêm các button vào menu
            menu0.getChildren().addAll(newGameButton, tutorialButton,
                    aboutButton, soundButton, exitButton);

            menu1.getChildren().addAll(tutorial, backButton1);

            menu2.getChildren().addAll(mute, unMute, backButton2);

            //menu4.getChildren().addAll(gameWindow);

            Rectangle bg = new Rectangle(800, 640);
            bg.setFill(Color.GREY);
            bg.setOpacity(0.4);

            getChildren().addAll(bg, menu0);
        }
    }

    /**
     * Chứa các đặc điểm của 1 button.
     */
    private static class menuButton extends StackPane {
        public menuButton(String name) {
            Text text = new Text(name);
            text.setFont(text.getFont().font(20));
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
        public textMenu(String path, int width, int height) throws IOException {
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

}