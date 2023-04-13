package Main;

import Screens.MainMenu.MainMenuMain;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        StackPane root = new StackPane();
        BorderPane pane = new BorderPane();

        primaryStage.setTitle("Ovi & The Mystery Team");

        new MainMenuMain(root, pane);

        primaryStage.setScene(new Scene(root, 1280, 720));

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
