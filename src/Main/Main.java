package Main;

import Screens.MainMenu.MainMenuMain;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static Icons.IconsLogic.setAppIcon;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(root, 1285, 655);

        primaryStage.setTitle("Ovi & The Mystery Team");

        setAppIcon(primaryStage);
        new MainMenuMain(root, pane);

        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);
    }
}
