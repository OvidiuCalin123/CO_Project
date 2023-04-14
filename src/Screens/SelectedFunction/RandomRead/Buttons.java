package Screens.SelectedFunction.RandomRead;

import Screens.Loading.LoadingMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

import static Shared.ButtonsHelper.buttonBuilder;
import static Shared.ButtonsHelper.scaleButton;

public class Buttons {



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

    public void run(StackPane root, StackPane randomReadMainScreen, BorderPane pane, String screenName){
        EventHandler<ActionEvent> event = e -> {
            new LoadingMain(root, randomReadMainScreen, screenName, pane);
            try {
                new RandomReadLogic().run();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };

        double xCoords = -700;  //daca cresti cu - il aduci catre stanga
        double yCoords = -100;

        double xScale = 2800;
        double yScale = 1800;

        Button b = buttonBuilder("run", randomReadMainScreen, event, pane);
        scaleButton(b,randomReadMainScreen,xScale,yScale, xCoords, yCoords);
    }


    public void addButtonsToScreen(StackPane root, StackPane randomReadMainScreen, BorderPane pane){

        back(randomReadMainScreen, pane);
        quit(randomReadMainScreen, pane);
        run(root, randomReadMainScreen, pane, "catchMonster2.png");

    }

}
