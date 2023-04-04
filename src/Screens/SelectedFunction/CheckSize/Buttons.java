package Screens.SelectedFunction.CheckSize;

import Screens.Loading.LoadingMain;
import Screens.SelectedFunction.SequentialRead.SequentialReadMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Buttons {

    public void buttonBuilder(String buttonName, double xCoords, double yCoords, StackPane root, EventHandler<ActionEvent> event, BorderPane pane){

        Button b = new Button(buttonName);

        // Higher means smaller size, lower value means higher size
        int scaleToScreenValX = 400;
        int scaleToScreenValY = 200;

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

    public void back(StackPane checkSizeMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            checkSizeMainScreen.toBack();
        };

        buttonBuilder("Back", -165, 75, checkSizeMainScreen, event, pane);

    }

    public void quit(StackPane checkSizeMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            Platform.exit();
            System.exit(0);
        };

        buttonBuilder("Quit", 170, 75, checkSizeMainScreen, event, pane);
    }

    public void run(StackPane checkSizeMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            new LoadingMain(checkSizeMainScreen, pane);
            new CheckSizeLogic().run();
        };

        buttonBuilder("Run", 0, 0, checkSizeMainScreen, event, pane);
    }


    public void addButtonsToScreen(StackPane checkSizeMainScreen, BorderPane pane){

        back(checkSizeMainScreen, pane);
        quit(checkSizeMainScreen, pane);
        run(checkSizeMainScreen, pane);
    }

}
