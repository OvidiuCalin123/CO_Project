package Screens.Result;

import Shared.Background;
import javafx.scene.layout.StackPane;

public class ResultMain {

    public static void showResult(StackPane r, String screenName){

        StackPane resultMainScreen = new StackPane();

        r.getChildren().add(resultMainScreen);

        resultMainScreen.toFront();

        new Background().setBackgroundImage(r,resultMainScreen,screenName);
    }
}
