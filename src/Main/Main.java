package Main;

import Screens.MainMenu.MainMenuMain;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        StackPane root = new StackPane();
        BorderPane pane = new BorderPane();

        primaryStage.setTitle("Ovi & The Mystery Team");

        // set app icon
        Image icon = new Image("file:DesignFiles/Background/Car.png");
        primaryStage.getIcons().add(icon);

        new MainMenuMain(root, pane);

        primaryStage.setScene(new Scene(root, 1280, 720));

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
