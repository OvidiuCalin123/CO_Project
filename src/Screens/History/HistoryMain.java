package Screens.History;

import Shared.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class HistoryMain {
    public HistoryMain(StackPane root, BorderPane pane){
        StackPane historyMainScreen = new StackPane();

        // Add the new pane to the stack pane
        root.getChildren().add(historyMainScreen);

        // Bring the new pane to the front
        historyMainScreen.toFront();

        // Set the background image
        new Background().setBackgroundImage(root,historyMainScreen,"monster5.jpg");
        new HistoryContent(historyMainScreen);

    }
}