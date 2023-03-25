package Main;

import Screens.MainMenu.Buttons;
import Screens.MainMenu.Background;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        StackPane root = new StackPane();

        // Title
        primaryStage.setTitle("Ovi si Echipa Misterelor");

        // Set the background image
        new Background().setBackgroundImage(root);

        // Add buttons
        new Buttons().addButtonsToScreen(root);

        // Screen width and height
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
