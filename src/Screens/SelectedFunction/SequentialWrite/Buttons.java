package Screens.SelectedFunction.SequentialWrite;

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
import static Shared.Dropbox.*;

public class Buttons {
    public void back(StackPane root, StackPane sequentialWriteMainScreen, BorderPane pane) {
        EventHandler<ActionEvent> event = e -> {

            resetSelectedOption();
            sequentialWriteMainScreen.toBack();

        };

        double xCoords = -1050;
        double yCoords = 550;

        double xScale = 2600;
        double yScale = 1500;

        Button b = buttonBuilder("back", sequentialWriteMainScreen, event, pane);

        scaleButton(b, root, xScale, yScale, xCoords, yCoords);
    }

    public void quit(StackPane root, StackPane sequentialWriteMainScreen, BorderPane pane) {
        EventHandler<ActionEvent> event = e -> {
            Platform.exit();
            System.exit(0);
        };

        double xCoords = 950;
        double yCoords = 485;

        double xScale = 2300;
        double yScale = 1300;

        Button b = buttonBuilder("quit", sequentialWriteMainScreen, event, pane);

        scaleButton(b, root, xScale, yScale, xCoords, yCoords);
    }

    public void run(StackPane root, StackPane sequentialWriteMainScreen, BorderPane pane, String screenName) {

        EventHandler<ActionEvent> event = e -> {

            if (getSelectedOption() != null) {

                new LoadingMain(root, sequentialWriteMainScreen, screenName, pane, new SequentialWriteLogic(), "monster5.jpg");
                try {

                    SequentialWriteLogic s = new SequentialWriteLogic();

                    s.runWarmUp();
                    s.run();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };

        double xCoords = 305;
        double yCoords = -35;

        double xScale = 2625;
        double yScale = 1920;

        Button b = buttonBuilder("shadowRun", sequentialWriteMainScreen, event, pane);
        dropbox(root, sequentialWriteMainScreen, new String[]{"1 GB", "500 MB", "100 MB"}, -115, -14.25, b);
        scaleButton(b, root, xScale, yScale, xCoords, yCoords);
    }

    public void addButtonsToScreen(StackPane root, StackPane sequentialWriteMainScreen, BorderPane pane) {

        back(root, sequentialWriteMainScreen, pane);
        quit(root, sequentialWriteMainScreen, pane);
        run(root, sequentialWriteMainScreen, pane, "catchMonster5.png");
    }
}
