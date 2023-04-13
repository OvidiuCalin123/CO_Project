package Main;

import Screens.MainMenu.MainMenuMain;
import Screens.SelectedFunction.SequentialRead.SequentialReadMain;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.io.File;

import static Shared.ButtonsHelper.*;

public class Main extends Application {

    private AudioClip audioClip;

    public void playMainMenuMusic(){
        File file = new File("SoundFiles/ScoobyDooMusic.wav");
        audioClip = new AudioClip(file.toURI().toString());
        audioClip.setVolume(0.3);
        audioClip.play();
    }

    public void volumeButton(StackPane root, BorderPane pane){


        double xCoords = 1150;
        double yCoords = -600;

        double xScale = 2600;
        double yScale = 1500;

        EventHandler<ActionEvent> event = e -> {
            playSoundClick("ButtonCloudPressed.wav", 3);Slider volumeSlider = new Slider(0, 1, 0.3);
            volumeSlider.setShowTickMarks(true);
            volumeSlider.setShowTickLabels(true);
            volumeSlider.setMajorTickUnit(0.1);
            volumeSlider.setMinorTickCount(0);
            volumeSlider.setSnapToTicks(true);

            Label volumeLabel = new Label("Volume: 0.3");
            volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                double volume = newValue.doubleValue();
                audioClip.setVolume(volume);
                volumeLabel.setText("Volume: " + String.format("%.1f", volume));
            });

            StackPane volumePane = new StackPane(volumeSlider, volumeLabel);
            volumePane.setAlignment(Pos.TOP_RIGHT);

            pane.setTop(volumePane);

        };

         Button b = buttonBuilder("sequentialRead",  root, event, pane);
         //Replace with a Slider
         scaleButton(b,root,xScale,yScale, xCoords, yCoords);

    }

    @Override
    public void stop() {
        audioClip.stop();
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

        volumeButton(root, pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
