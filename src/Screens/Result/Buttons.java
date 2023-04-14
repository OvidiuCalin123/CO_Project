package Screens.Result;


import Screens.Loading.LoadingMain;
import Screens.SelectedFunction.CheckSize.CheckSizeMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import static Shared.ButtonsHelper.buttonBuilder;
import static Shared.ButtonsHelper.scaleButton;

public class Buttons {

    public void back(StackPane sequentialReadMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
           new CheckSizeMain(sequentialReadMainScreen, pane);
        };

        double xCoords = -1050;
        double yCoords = 550;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("back", sequentialReadMainScreen, event, pane);

        scaleButton(b,sequentialReadMainScreen,xScale,yScale, xCoords, yCoords);

    }

    public void quit(StackPane sequentialReadMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            Platform.exit();
            System.exit(0);
        };

        double xCoords = 950;
        double yCoords = 485;

        double xScale = 2300;
        double yScale = 1300;

        Button b = buttonBuilder("quit",  sequentialReadMainScreen, event, pane);

        scaleButton(b,sequentialReadMainScreen,xScale,yScale, xCoords, yCoords);
    }



    public void addButtonsToScreen(StackPane root, StackPane sequentialReadMainScreen, BorderPane pane){

        back(sequentialReadMainScreen, pane);
        quit(sequentialReadMainScreen, pane);



    }

}
