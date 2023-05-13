package Screens.SelectedFunction.SequentialRead;

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

    public void addButtonsToScreen(StackPane root, StackPane sequentialReadMainScreen, BorderPane pane){

        back(root, sequentialReadMainScreen, pane);
        quit(root, sequentialReadMainScreen, pane);
        run(root, sequentialReadMainScreen, pane, "catchMonster4.png");


    }

    public void run(StackPane root, StackPane sequentialReadMainScreen, BorderPane pane, String screenName){
        EventHandler<ActionEvent> event = e -> {
            new LoadingMain(root, sequentialReadMainScreen, screenName, pane, new SequentialReadLogic(), "monster4.png");
            try {

                SequentialReadLogic s = new SequentialReadLogic();

                s.runWarmUp();
                s.run();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };

        double xCoords = -317.5;
        double yCoords = -120;

        double xScale = 2800;
        double yScale = 1937.5;

        Button b = buttonBuilder("run", sequentialReadMainScreen, event, pane);
        scaleButton(b,root,xScale,yScale, xCoords, yCoords);
    }

    public void back(StackPane root, StackPane sequentialReadMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> sequentialReadMainScreen.toBack();

        double xCoords = -1050;
        double yCoords = 550;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("back", sequentialReadMainScreen, event, pane);

        scaleButton(b,root,xScale,yScale, xCoords, yCoords);

    }

    public void quit(StackPane root, StackPane sequentialReadMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            Platform.exit();
            System.exit(0);
        };

        double xCoords = 950;
        double yCoords = 485;

        double xScale = 2300;
        double yScale = 1300;

        Button b = buttonBuilder("quit",  sequentialReadMainScreen, event, pane);

        scaleButton(b,root,xScale,yScale, xCoords, yCoords);
    }
}
