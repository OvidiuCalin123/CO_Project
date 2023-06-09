package Shared;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

import java.io.File;

import static Shared.Dropbox.getSelectedOption;

public class ButtonsHelper {
    public static void playSoundClick(String sound, double volumeLevel) {
        File file = new File("SoundFiles/" + sound);

        AudioClip audioClip = new AudioClip(file.toURI().toString());
        audioClip.setVolume(volumeLevel);
        audioClip.play();
    }

    public static void scaleButton(Button b, StackPane root, double xScale, double yScale, double xCoords, double yCoords) {

        b.scaleXProperty().bind(root.widthProperty().divide(xScale));
        b.scaleYProperty().bind(root.heightProperty().divide(yScale));

        b.setTranslateX(xCoords * root.getWidth() / xScale);
        b.setTranslateY(yCoords * root.getHeight() / yScale);

        root.widthProperty().addListener((obs, oldVal, newVal) -> b.setTranslateX(xCoords * newVal.doubleValue() / xScale));
        root.heightProperty().addListener((obs, oldVal, newVal) -> b.setTranslateY(yCoords * newVal.doubleValue() / yScale));
    }

    public static void buttonInteraction(Button b, ImageView imageView) {

        b.setStyle("-fx-cursor: hand; -fx-background-color: transparent;");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(20);
        dropShadow.setOffsetX(20);
        dropShadow.setOffsetY(20);
        dropShadow.setColor(Color.ORANGE);
        imageView.setEffect(dropShadow);

        File file = new File("SoundFiles/OnHoverSound.wav");

        AudioClip audioClip = new AudioClip(file.toURI().toString());
        audioClip.setVolume(3);
        audioClip.play();
    }

    public static Button buttonBuilder(String buttonName, StackPane root, EventHandler<ActionEvent> event, BorderPane pane) {

        Image img = new Image("file:DesignFiles/Buttons/" + buttonName + ".png");
        Button b = new Button();
        ImageView imageView = new ImageView(img);
        if (buttonName.equals("shadowRun")) {

            b.setOnAction(event);

            b.setOnMouseEntered(e -> {
                if (getSelectedOption() != null) {

                    buttonInteraction(b, imageView);
                }

            });

        } else {

            b.setOnAction(event);

            b.setOnMouseEntered(e -> buttonInteraction(b, imageView));

        }
        b.setOnMouseExited(e -> {
            b.setStyle("-fx-background-color: transparent;");
            imageView.setEffect(null);
        });

        b.setStyle("-fx-background-color: transparent;");
        b.setGraphic(imageView);
        pane.setCenter(b);
        root.getChildren().add(b);

        return b;
    }

    public static Button buttonBuilder(StackPane root, EventHandler<ActionEvent> event, BorderPane pane) {

        Button b = new Button(" Back ");

        b.setOnAction(event);

        b.setOnMouseEntered(e -> {

            if (getSelectedOption() != null) {
                File file = new File("SoundFiles/OnHoverSound.wav");

                AudioClip audioClip = new AudioClip(file.toURI().toString());
                audioClip.setVolume(3);
                audioClip.play();
            }
        });

        pane.setCenter(b);
        root.getChildren().add(b);

        return b;
    }
}
