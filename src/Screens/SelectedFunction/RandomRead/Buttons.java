package Screens.SelectedFunction.RandomRead;

import Screens.Loading.LoadingMain;
import Screens.SelectedFunction.CheckSize.CheckSizeLogic;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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

    public void back(StackPane randomReadMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            randomReadMainScreen.toBack();
        };

        double xCoords = -1050;
        double yCoords = 550;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("back", randomReadMainScreen, event, pane);

        scaleButton(b,randomReadMainScreen,xScale,yScale, xCoords, yCoords);

    }

    public void quit(StackPane randomReadMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            Platform.exit();
            System.exit(0);
        };

        double xCoords = 950;
        double yCoords = 485;

        double xScale = 2300;
        double yScale = 1300;

        Button b = buttonBuilder("quit",  randomReadMainScreen, event, pane);

        scaleButton(b,randomReadMainScreen,xScale,yScale, xCoords, yCoords);
    }

    public void run(StackPane randomReadMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            new LoadingMain(randomReadMainScreen, pane);
            new CheckSizeLogic().run();
        };

        double xCoords = -700;  //daca cresti cu - il aduci catre stanga
        double yCoords = -100;

        double xScale = 2800;
        double yScale = 1800;

        Button b = buttonBuilder("run", randomReadMainScreen, event, pane);
        scaleButton(b,randomReadMainScreen,xScale,yScale, xCoords, yCoords);
    }


    public void addButtonsToScreen(StackPane randomReadMainScreen, BorderPane pane){

        back(randomReadMainScreen, pane);
        quit(randomReadMainScreen, pane);
        run(randomReadMainScreen, pane);

    }

}