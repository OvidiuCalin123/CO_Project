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

public class Buttons {

    public void scaleButton(Button b, StackPane root, double xScale, double yScale, double xCoords, double yCoords){

        // Bind scale properties to root pane dimensions
        b.scaleXProperty().bind(root.widthProperty().divide(xScale));
        b.scaleYProperty().bind(root.heightProperty().divide(yScale));

        // Set the initial position of the button
        b.setTranslateX(xCoords * root.getWidth() / xScale);
        b.setTranslateY(yCoords * root.getHeight() / yScale);

        // Reposition the button when the root pane dimensions change
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            b.setTranslateX(xCoords * newVal.doubleValue() / xScale);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            b.setTranslateY(yCoords * newVal.doubleValue() / yScale);
        });

    }

    public void playSoundOnHover(){

    }

    public Button buttonBuilder(String buttonName, StackPane root, EventHandler<ActionEvent> event, BorderPane pane){

        Image img = new Image("file:DesignFiles/Buttons/" + buttonName + ".png");
        ImageView imageView = new ImageView(img);
        Button b = new Button();

        b.setOnAction(event);

        b.setOnMouseEntered(e -> {
            b.setStyle("-fx-cursor: hand; -fx-background-color: transparent;");
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(20);
            dropShadow.setOffsetX(20);
            dropShadow.setOffsetY(20);
            dropShadow.setColor(Color.ORANGE);
            imageView.setEffect(dropShadow);
        });

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

    public void randomWrite(StackPane root, BorderPane pane){

        EventHandler<ActionEvent> event = e -> new RandomWriteMain(root, pane);

        double xCoords = -1000;
        double yCoords = -200;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("randomWrite", root, event, pane);

        scaleButton(b,root,xScale,yScale, xCoords, yCoords);
    }
    public void randomRead(StackPane root, BorderPane pane){
        EventHandler<ActionEvent> event = e -> new RandomReadMain(root, pane);

        double xCoords = -400;
        double yCoords = -200;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("randomRead",  root, event, pane);

        scaleButton(b,root,xScale,yScale, xCoords, yCoords);
    }
    public void sequentialWrite(StackPane root, BorderPane pane){
        EventHandler<ActionEvent> event = e -> new SequentialWriteMain(root, pane);

        double xCoords = -1000;
        double yCoords = 100;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("sequentialWrite",  root, event, pane);

        scaleButton(b,root,xScale,yScale, xCoords, yCoords);
    }
    public void sequentialRead(StackPane root, BorderPane pane){
        EventHandler<ActionEvent> event = e -> new SequentialReadMain(root, pane);

        double xCoords = -400;
        double yCoords = 100;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("sequentialRead",  root, event, pane);

        scaleButton(b,root,xScale,yScale, xCoords, yCoords);
    }

    public void checkSize(StackPane root, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
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
