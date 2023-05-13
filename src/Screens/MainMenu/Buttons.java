package Screens.MainMenu;

import Screens.SelectedFunction.CheckSize.CheckSizeMain;
import Screens.SelectedFunction.RandomRead.RandomReadMain;
import Screens.SelectedFunction.RandomWrite.RandomWriteMain;
import Screens.SelectedFunction.SequentialRead.SequentialReadMain;
import Screens.SelectedFunction.SequentialWrite.SequentialWriteMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.converter.NumberStringConverter;

import java.io.File;
import java.text.DecimalFormat;

import static Screens.MainMenu.SoundButton.setEvent;
import static Shared.ButtonsHelper.*;

public class Buttons {

    private boolean isVolumeSliderOnScreen = false;

    public void randomWrite(StackPane root, BorderPane pane) {

        EventHandler<ActionEvent> event = e -> {

            playSoundClick("ButtonCloudPressed.wav", 3);
            new RandomWriteMain(root, pane);

        };

        double xCoords = -1000;
        double yCoords = -200;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("randomWrite", root, event, pane);

        scaleButton(b, root, xScale, yScale, xCoords, yCoords);
    }

    public void randomRead(StackPane root, BorderPane pane) {
        EventHandler<ActionEvent> event = e -> {

            playSoundClick("ButtonCloudPressed.wav", 3);
            new RandomReadMain(root, pane);

        };

        double xCoords = -400;
        double yCoords = -200;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("randomRead", root, event, pane);

        scaleButton(b, root, xScale, yScale, xCoords, yCoords);
    }

    public void sequentialWrite(StackPane root, BorderPane pane) {
        EventHandler<ActionEvent> event = e -> {

            playSoundClick("ButtonCloudPressed.wav", 3);
            new SequentialWriteMain(root, pane);
        };

        double xCoords = -1000;
        double yCoords = 100;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("sequentialWrite", root, event, pane);

        scaleButton(b, root, xScale, yScale, xCoords, yCoords);
    }

    public void sequentialRead(StackPane root, BorderPane pane) {
        EventHandler<ActionEvent> event = e -> {

            playSoundClick("ButtonCloudPressed.wav", 3);
            new SequentialReadMain(root, pane);

        };

        double xCoords = -400;
        double yCoords = 100;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("sequentialRead", root, event, pane);

        scaleButton(b, root, xScale, yScale, xCoords, yCoords);
    }

    public void checkSize(StackPane root, BorderPane pane) {
        EventHandler<ActionEvent> event = e -> {

            playSoundClick("ButtonCloudPressed.wav", 3);
            new CheckSizeMain(root, pane);
        };

        double xCoords = -700;
        double yCoords = 400;

        double xScale = 2600;
        double yScale = 1600;

        Button b = buttonBuilder("checkSize", root, event, pane);

        scaleButton(b, root, xScale, yScale, xCoords, yCoords);
    }

    public void quit(StackPane root, BorderPane pane) {
        EventHandler<ActionEvent> event = e -> {

            playSoundClick("ButtonCloudPressed.wav", 3);
            Platform.exit();
            System.exit(0);
        };

        double xCoords = -700;
        double yCoords = 625;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("quit", root, event, pane);

        scaleButton(b, root, xScale, yScale, xCoords, yCoords);
    }

    public void volumeButton(StackPane root, BorderPane pane) {

        File file = new File("SoundFiles/ScoobyDooMusic.wav");
        Media media = new Media(file.toURI().toString());
        MediaPlayer audioClip = new MediaPlayer(media);

        audioClip.setVolume(0.3);
        audioClip.setCycleCount(MediaPlayer.INDEFINITE);
        audioClip.play();

        double xCoords = 1260;
        double yCoords = -730;

        double xScale = 2800;
        double yScale = 1700;


        Slider volumeSlider = new Slider(0, 100, 1);


        StackPane volumePane = new StackPane(volumeSlider);

        Button highButton = SoundButton.buttonBuilder("soundMid", root, pane);

        scaleButton(highButton, root, xScale, yScale, xCoords, yCoords);

        EventHandler<ActionEvent> event = e -> {

            playSoundClick("ButtonCloudPressed.wav", 3);

            Label volumeLabel = new Label();
            volumeSlider.setMajorTickUnit(1);
            volumeSlider.setMinorTickCount(0);

            volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                double volume = newValue.intValue();
                audioClip.setVolume(volume / 100);
                if (volume >= 70) {
                    highButton.setGraphic(new ImageView(new Image("file:DesignFiles/Buttons/soundHigh.png", 200, 200, true, true)));
                } else if (volume < 70 && volume >= 30) {
                    highButton.setGraphic(new ImageView(new Image("file:DesignFiles/Buttons/soundMid.png", 200, 200, true, true)));
                } else if (volume < 30 && volume > 1) {
                    highButton.setGraphic(new ImageView(new Image("file:DesignFiles/Buttons/soundLow.png", 200, 200, true, true)));
                } else {
                    highButton.setGraphic(new ImageView(new Image("file:DesignFiles/Buttons/soundMute.png", 200, 200, true, true)));
                }

            });

            volumeLabel.textProperty().bindBidirectional(volumeSlider.valueProperty(), new NumberStringConverter(new DecimalFormat("#0")));

            volumePane.setTranslateX(180 * root.getWidth() / 600);
            volumePane.setTranslateY(-150 * root.getHeight() / 350);

            // Reposition the button when the root pane dimensions change
            root.widthProperty().addListener((obs, oldVal, newVal) -> {
                volumePane.setTranslateX(180 * newVal.doubleValue() / 600);
                volumeLabel.setTranslateX(180 * newVal.doubleValue() / 600);
            });
            root.heightProperty().addListener((obs, oldVal, newVal) -> {
                volumePane.setTranslateY(-150 * newVal.doubleValue() / 350);
                volumeLabel.setTranslateY(-150 * newVal.doubleValue() / 350);
            });

            volumeLabel.setTranslateX(180 * root.getWidth() / 600);
            volumeLabel.setTranslateY(-150 * root.getHeight() / 350);

            volumePane.setScaleX(0.2);
            volumePane.setScaleY(4);

            if (!isVolumeSliderOnScreen) {

                root.getChildren().add(volumePane);
                root.getChildren().add(volumeLabel);
                isVolumeSliderOnScreen = true;
            } else {

                root.getChildren().remove(volumePane);
                root.getChildren().remove(volumeLabel);
                isVolumeSliderOnScreen = false;
            }
        };
        setEvent(event);
    }

    public void addButtonsToScreen(StackPane root, BorderPane pane) {

        randomWrite(root, pane);
        randomRead(root, pane);
        sequentialRead(root, pane);
        sequentialWrite(root, pane);
        checkSize(root, pane);
        quit(root, pane);
        volumeButton(root, pane);
    }
}
