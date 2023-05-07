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
import static Shared.Dropbox.getSelectedOption;

public class Buttons {

    public void back(StackPane root, StackPane checkSizeMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            checkSizeMainScreen.toBack();
        };

        double xCoords = -1150;
        double yCoords = 715;

        double xScale = 2800;
        double yScale = 1700;

        Button b = buttonBuilder("back",checkSizeMainScreen, event, pane);

        scaleButton(b,root,xScale,yScale, xCoords, yCoords);

    }

    public void quit(StackPane root, StackPane checkSizeMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            Platform.exit();
            System.exit(0);
        };

        double xCoords = 1100;
        double yCoords = 650;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("Quit", checkSizeMainScreen, event, pane);
        scaleButton(b,root,xScale,yScale, xCoords, yCoords);
    }

    public void run(StackPane root, StackPane checkSizeMainScreen, BorderPane pane, String screenName){
        EventHandler<ActionEvent> event = e -> {
            if(getSelectedOption()!=null){
                new LoadingMain(root, checkSizeMainScreen, screenName, pane, new CheckSizeLogic(), "monster1.jpg");
                new CheckSizeLogic().run();
            }
        };

        double xCoords = -900;
        double yCoords = -37.5;

        double xScale = 2625;
        double yScale = 1915;

//        if(getSelectedOption()==null) {
//            Button b = buttonBuilder("deadRun2", checkSizeMainScreen, event, pane);
//            scaleButton(b, root, xScale, yScale, xCoords, yCoords);
//        }
//        else{
            Button b = buttonBuilder("run", checkSizeMainScreen, event, pane);
            scaleButton(b, root, xScale, yScale, xCoords, yCoords);
//        }
    }


    public void addButtonsToScreen(StackPane root, StackPane checkSizeMainScreen, BorderPane pane){

        back(root, checkSizeMainScreen, pane);
        quit(root, checkSizeMainScreen, pane);
        run(root, checkSizeMainScreen, pane, "catchMonster1.jpg");
    }

}
