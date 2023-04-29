package Main;

import Screens.MainMenu.MainMenuMain;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Arrays;

import static Icons.IconsLogic.setAppIcon;
import static Icons.IconsLogic.setCursorImage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        StackPane root = new StackPane();
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Ovi & The Mystery Team");

        setAppIcon(primaryStage);
        setCursorImage(scene);
        new MainMenuMain(root, pane);

        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
