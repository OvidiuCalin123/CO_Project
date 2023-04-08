package Screens.SelectedFunction.CheckSize;

import Screens.Loading.LoadingMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import static Shared.ButtonsHelper.buttonBuilder;
import static Shared.ButtonsHelper.scaleButton;

public class Buttons {

    public void back(StackPane checkSizeMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            checkSizeMainScreen.toBack();
        };

        double xCoords = -1150;
        double yCoords = 715;

        double xScale = 2800;
        double yScale = 1700;

        Button b = buttonBuilder("back",checkSizeMainScreen, event, pane);

        scaleButton(b,checkSizeMainScreen,xScale,yScale, xCoords, yCoords);

    }

    public void quit(StackPane checkSizeMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            Platform.exit();
            System.exit(0);
        };

        double xCoords = 1100;
        double yCoords = 650;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("Quit", checkSizeMainScreen, event, pane);
        scaleButton(b,checkSizeMainScreen,xScale,yScale, xCoords, yCoords);
    }

    public void run(StackPane root, StackPane checkSizeMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            new LoadingMain(root, checkSizeMainScreen);
            new CheckSizeLogic().run();
        };

        double xCoords = -400;
        double yCoords = 200;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("run", checkSizeMainScreen, event, pane);
        scaleButton(b,checkSizeMainScreen,xScale,yScale, xCoords, yCoords);
    }


    public void addButtonsToScreen(StackPane root, StackPane checkSizeMainScreen, BorderPane pane){

        back(checkSizeMainScreen, pane);
        quit(checkSizeMainScreen, pane);
        run(root, checkSizeMainScreen, pane);
    }

}
