package Screens.SelectedFunction.RandomRead;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Buttons {

    public void buttonBuilder(String buttonName, double xCoords, double yCoords, StackPane randomReadMainScreen, EventHandler<ActionEvent> event, BorderPane pane){

        Button b = new Button(buttonName);

        // Higher means smaller size, lower value means higher size
        int scaleToScreenValX = 700;
        int scaleToScreenValY = 400;

        b.setOnAction(event);

        // Bind scale properties to root pane dimensions
        b.scaleXProperty().bind(randomReadMainScreen.widthProperty().divide(scaleToScreenValX));
        b.scaleYProperty().bind(randomReadMainScreen.heightProperty().divide(scaleToScreenValY));

        // Set the initial position of the button
        b.setTranslateX(xCoords * randomReadMainScreen.getWidth() / scaleToScreenValX);
        b.setTranslateY(yCoords * randomReadMainScreen.getHeight() / scaleToScreenValY);

        // Reposition the button when the root pane dimensions change
        randomReadMainScreen.widthProperty().addListener((obs, oldVal, newVal) -> {
            b.setTranslateX(xCoords * newVal.doubleValue() / scaleToScreenValX);
        });
        randomReadMainScreen.heightProperty().addListener((obs, oldVal, newVal) -> {
            b.setTranslateY(yCoords * newVal.doubleValue() / scaleToScreenValY);
        });

        pane.setCenter(b);
        randomReadMainScreen.getChildren().add(b);

    }

    public void back(StackPane randomReadMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            randomReadMainScreen.toBack();
        };

        buttonBuilder("Back", -280, -50, randomReadMainScreen, event, pane);

    }


    public void addButtonsToScreen(StackPane randomReadMainScreen, BorderPane pane){

        back(randomReadMainScreen, pane);


    }

}
