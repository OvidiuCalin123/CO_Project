package Screens.MainMenu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Buttons {

    public void buttonBuilder(String buttonName, double xCoords, double yCoords, StackPane root, EventHandler<ActionEvent> event){

        Button b = new Button(buttonName);

        b.setScaleX(2);
        b.setScaleY(2);
        b.setTranslateX(xCoords);
        b.setTranslateY(yCoords);

        b.setOnAction(event);

        root.getChildren().add(b);
    }

    public void randomWrite(StackPane root){

        EventHandler<ActionEvent> event = e -> {
            // On Click do something
        };

        buttonBuilder("Random Write", -500, -300, root, event);

    }
    public void randomRead(StackPane root){
        EventHandler<ActionEvent> event = e -> {
            // On Click do something
        };

        buttonBuilder("Random Read", -500, -200, root, event);
    }
    public void sequentialWrite(StackPane root){
        EventHandler<ActionEvent> event = e -> {
            // On Click do something
        };

        buttonBuilder("Sequential Write", -500, -100, root, event);
    }
    public void sequentialRead(StackPane root){
        EventHandler<ActionEvent> event = e -> {
            // On Click do something
        };

        buttonBuilder("Sequential Read", -500, 0, root, event);
    }
    public void quit(StackPane root){
        EventHandler<ActionEvent> event = e -> {
            // On Click do something
        };

        buttonBuilder("Quit", -500, 100, root, event);
    }

    public void addButtonsToScreen(StackPane root){

        randomWrite(root);
        randomRead(root);
        sequentialRead(root);
        sequentialWrite(root);
        quit(root);

    }

}
