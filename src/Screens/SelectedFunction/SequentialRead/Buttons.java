package Screens.SelectedFunction.SequentialRead;

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

    public void back(StackPane sequentialReadMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            sequentialReadMainScreen.toBack();
        };

        buttonBuilder("Back", -280, -50, sequentialReadMainScreen, event, pane);

    }


    public void addButtonsToScreen(StackPane sequentialReadMainScreen, BorderPane pane){

        back(sequentialReadMainScreen, pane);


    }

}
