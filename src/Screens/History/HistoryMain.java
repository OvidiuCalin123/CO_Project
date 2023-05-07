package Screens.History;

import Shared.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class HistoryMain {

    public HistoryMain(StackPane root, BorderPane pane, String historyBackgroundScreen){
        StackPane historyMainScreen = new StackPane();

        root.getChildren().add(historyMainScreen);

        historyMainScreen.toFront();

        new Background().setBackgroundImage(root,historyMainScreen, historyBackgroundScreen);
        new HistoryContent(root, historyMainScreen, pane, historyBackgroundScreen);
    }
}
