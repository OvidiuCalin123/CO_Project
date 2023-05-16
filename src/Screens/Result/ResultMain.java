package Screens.Result;

import Screens.SelectedFunction.CheckSize.CheckSizeLogic;
import Screens.SelectedFunction.RandomRead.RandomReadLogic;
import Screens.SelectedFunction.RandomWrite.RandomWriteLogic;
import Screens.SelectedFunction.SelectedFunctionLogicHandle;
import Screens.SelectedFunction.SequentialRead.SequentialReadLogic;
import Screens.SelectedFunction.SequentialWrite.SequentialWriteLogic;
import Shared.Background;
import javafx.beans.binding.Bindings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.HashMap;

import static Screens.Result.ResultMeasurement.setScore;
import static Screens.Result.ResultMeasurement.setTime;
import static Screens.SelectedFunction.CheckSize.CheckSizeLogic.printRemainingSpace;
import static Shared.ScaleToScreen.resizeToImage;
import static Shared.ScaleToScreen.resizeToScreen;

public class ResultMain {

    public static void fillScreenSelector(StackPane r, String screenName, BorderPane pane,
                                           StackPane resultMainScreen){

        HashMap<String, Pair<SelectedFunctionLogicHandle, String>> screenLogicMap = new HashMap<>();
        screenLogicMap.put("catchMonster3", new Pair<>(new RandomWriteLogic(), "randomWriteTitle"));
        screenLogicMap.put("catchMonster2", new Pair<>(new RandomReadLogic(), "randomReadTitle"));
        screenLogicMap.put("catchMonster5", new Pair<>(new SequentialWriteLogic(), "sequentialWriteTitle"));
        screenLogicMap.put("catchMonster4", new Pair<>(new SequentialReadLogic(), "sequentialReadTitle"));
        screenLogicMap.put("catchMonster1", new Pair<>(new CheckSizeLogic(), "checkSizeTitle"));

        String prefix = screenName.substring(0, screenName.lastIndexOf('.'));

        Pair<SelectedFunctionLogicHandle, String> logicAndTitle = screenLogicMap.get(prefix);

        if (logicAndTitle != null) {
            SelectedFunctionLogicHandle logic = logicAndTitle.getKey();
            String title = logicAndTitle.getValue();

            setScore(r, pane, resultMainScreen, logic);
            setTime(r, pane, resultMainScreen, logic);
            setTitle(r, pane, resultMainScreen, title);
        }

    }

    public static void setTitle(StackPane root, BorderPane pane, StackPane randomReadMainScreen, String screenTitle) {
        Image img = new Image("file:DesignFiles/Buttons/" + screenTitle + ".png");
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.3));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.25));

        imageView.setTranslateX(0 * root.getWidth() / 600);
        imageView.setTranslateY(-125 * root.getHeight() / 350);

        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setTranslateX(0 * newVal.doubleValue() / 600);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setTranslateY(-125 * newVal.doubleValue() / 350);
        });

        pane.setCenter(imageView);

        randomReadMainScreen.getChildren().add(imageView);
    }

    public static void showResult(StackPane r, String screenName, BorderPane pane, String historyBackgroundScreen) {

        StackPane resultMainScreen = new StackPane();

        r.getChildren().add(resultMainScreen);

        resultMainScreen.toFront();

        new Background().setBackgroundImage(r, resultMainScreen, screenName);
        new Buttons().addButtonsToScreen(r, pane, historyBackgroundScreen);

        fillScreenSelector(r, screenName, pane, resultMainScreen);
    }
}