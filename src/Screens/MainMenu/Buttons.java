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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Buttons {

    public void buttonBuilder(String buttonName, double xCoords, double yCoords, StackPane root, EventHandler<ActionEvent> event, BorderPane pane){

        Button b = new Button(buttonName);

        // Higher means smaller size, lower value means higher size
        int scaleToScreenValX = 700;
        int scaleToScreenValY = 400;

        b.setOnAction(event);

        // Bind scale properties to root pane dimensions
        b.scaleXProperty().bind(root.widthProperty().divide(scaleToScreenValX));
        b.scaleYProperty().bind(root.heightProperty().divide(scaleToScreenValY));

        // Set the initial position of the button
        b.setTranslateX(xCoords * root.getWidth() / scaleToScreenValX);
        b.setTranslateY(yCoords * root.getHeight() / scaleToScreenValY);

        // Reposition the button when the root pane dimensions change
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            b.setTranslateX(xCoords * newVal.doubleValue() / scaleToScreenValX);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            b.setTranslateY(yCoords * newVal.doubleValue() / scaleToScreenValY);
        });

        pane.setCenter(b);
        root.getChildren().add(b);

    }

    public void randomWrite(StackPane root, BorderPane pane){

        EventHandler<ActionEvent> event = e -> new RandomWriteMain(root, pane);

        buttonBuilder("Random Write", -280, -50, root, event, pane);

    }
    public void randomRead(StackPane root, BorderPane pane){
        EventHandler<ActionEvent> event = e -> new RandomReadMain(root, pane);

        buttonBuilder("Random Read", -150, -50, root, event, pane);
    }
    public void sequentialWrite(StackPane root, BorderPane pane){
        EventHandler<ActionEvent> event = e -> new SequentialWriteMain(root, pane);

        buttonBuilder("Sequential Write", -280, 0, root, event, pane);
    }
    public void sequentialRead(StackPane root, BorderPane pane){
        EventHandler<ActionEvent> event = e -> new SequentialReadMain(root, pane);

        buttonBuilder("Sequential Read", -150, 0, root, event, pane);
    }
    public void quit(StackPane root, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            Platform.exit();
            System.exit(0);
        };

        buttonBuilder("Quit", -220, 125, root, event, pane);
    }

    public void checkSize(StackPane root, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            new CheckSizeMain(root, pane);
        };

        buttonBuilder("Check Size", -220, 60, root, event, pane);
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
