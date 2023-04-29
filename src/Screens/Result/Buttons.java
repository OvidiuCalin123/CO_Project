package Screens.Result;


import Screens.History.HistoryMain;
import Screens.Loading.LoadingMain;
import Screens.SelectedFunction.CheckSize.CheckSizeMain;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import static Shared.ButtonsHelper.buttonBuilder;
import static Shared.ButtonsHelper.scaleButton;

public class Buttons {

    public void back(StackPane sequentialReadMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            sequentialReadMainScreen.toBack();
            sequentialReadMainScreen.toBack();
            ObservableList<Node> children = sequentialReadMainScreen.getChildren();
            int index = children.size() - 4;
            sequentialReadMainScreen.getChildren().get(index).toBack();
            sequentialReadMainScreen.getChildren().get(index + 1).toBack();
            sequentialReadMainScreen.getChildren().get(index + 2).toBack();
            sequentialReadMainScreen.getChildren().get(index + 2).toBack();
            sequentialReadMainScreen.getChildren().get(index + 3).toBack();

        };

        double xCoords = -975;
        double yCoords = 605;

        double xScale = 2400;
        double yScale = 1650;

        Button b = buttonBuilder("backToHome", sequentialReadMainScreen, event, pane);

        scaleButton(b,sequentialReadMainScreen,xScale,yScale, xCoords, yCoords);

    }

    public void history(StackPane sequentialReadMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            new HistoryMain(sequentialReadMainScreen, pane);
        };

        double xCoords = 0;
        double yCoords = 750;

        double xScale = 3250;
        double yScale = 2000;

        Button b = buttonBuilder("history",  sequentialReadMainScreen, event, pane);

        scaleButton(b,sequentialReadMainScreen,xScale,yScale, xCoords, yCoords);
    }

    public void quit(StackPane sequentialReadMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            Platform.exit();
            System.exit(0);
        };

        double xCoords = 950;
        double yCoords = 485;

        double xScale = 2400;
        double yScale = 1300;

        Button b = buttonBuilder("quit",  sequentialReadMainScreen, event, pane);

        scaleButton(b,sequentialReadMainScreen,xScale,yScale, xCoords, yCoords);
    }

    public void addButtonsToScreen(StackPane root, StackPane sequentialReadMainScreen, BorderPane pane){

        back(root, pane);
        quit(root, pane);
        history(root, pane);

    }

}
