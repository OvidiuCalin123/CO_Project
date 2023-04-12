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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import static Shared.ButtonsHelper.*;

public class Buttons {

    public void randomWrite(StackPane root, BorderPane pane){

        EventHandler<ActionEvent> event = e -> {

            playSoundClick("ButtonCloudPressed.wav", 3);
            new RandomWriteMain(root, pane);

        };

        double xCoords = -1000;
        double yCoords = -200;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("randomWrite", root, event, pane);

        scaleButton(b,root,xScale,yScale, xCoords, yCoords);
    }
    public void randomRead(StackPane root, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {

            playSoundClick("ButtonCloudPressed.wav", 3);
            new RandomReadMain(root, pane);

        };

        double xCoords = -400;
        double yCoords = -200;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("randomRead",  root, event, pane);

        scaleButton(b,root,xScale,yScale, xCoords, yCoords);
    }
    public void sequentialWrite(StackPane root, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {

            playSoundClick("ButtonCloudPressed.wav", 3);
            new SequentialWriteMain(root, pane);
        };

        double xCoords = -1000;
        double yCoords = 100;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("sequentialWrite",  root, event, pane);

        scaleButton(b,root,xScale,yScale, xCoords, yCoords);
    }
    public void sequentialRead(StackPane root, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {

            playSoundClick("ButtonCloudPressed.wav", 3);
            new SequentialReadMain(root, pane);

        };

        double xCoords = -400;
        double yCoords = 100;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("sequentialRead",  root, event, pane);

        scaleButton(b,root,xScale,yScale, xCoords, yCoords);
    }

    public void checkSize(StackPane root, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {

            playSoundClick("ButtonCloudPressed.wav", 3);
            new CheckSizeMain(root, pane);
        };

        double xCoords = -700;
        double yCoords = 400;

        double xScale = 2600;
        double yScale = 1600;

        Button b = buttonBuilder("checkSize",  root, event, pane);

        scaleButton(b,root,xScale,yScale, xCoords, yCoords);
    }

    public void quit(StackPane root, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {

            playSoundClick("ButtonCloudPressed.wav", 3);
            Platform.exit();
            System.exit(0);
        };

        double xCoords = -700;
        double yCoords = 800;

        double xScale = 2600;
        double yScale = 2000;

        Button b = buttonBuilder("quit",  root, event, pane);

        scaleButton(b,root,xScale,yScale, xCoords, yCoords);
    }

    public void addButtonsToScreen(StackPane root, BorderPane pane){

        randomWrite(root, pane);
        randomRead(root, pane);
        sequentialRead(root, pane);
        sequentialWrite(root, pane);
        checkSize(root, pane);
        quit(root, pane);

    }

}
