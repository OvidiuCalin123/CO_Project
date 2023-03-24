package Main;

import Screens.MainMenu.ImageLogic.Background;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane root = new StackPane();
        // Title
        primaryStage.setTitle("Ovi si Echipa Misterelor");

        // Set the background image
        Background background = new Background();
        background.setBackgroundImage(root);

        // Screen width and height
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
