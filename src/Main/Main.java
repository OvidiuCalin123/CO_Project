package Main;

import Screens.MainMenu.MainMenuMain;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public void playMainMenuMusic(){

        File file = new File("SoundFiles/ScoobyDooMusic.wav");
        AudioClip audioClip = new AudioClip(file.toURI().toString());
        audioClip.setVolume(0.3);
        audioClip.play();

    }

    @Override
    public void start(Stage primaryStage){
        StackPane root = new StackPane();
        BorderPane pane = new BorderPane();

        primaryStage.setTitle("Ovi & The Mystery Team");

        new MainMenuMain(root, pane);

        primaryStage.setScene(new Scene(root, 1280, 720));

        primaryStage.show();

        playMainMenuMusic();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
