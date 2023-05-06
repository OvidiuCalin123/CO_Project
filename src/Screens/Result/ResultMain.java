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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;

import static Shared.ScaleToScreen.resizeToImage;
import static Shared.ScaleToScreen.resizeToScreen;

public class ResultMain {

    public static void setScore(StackPane root, BorderPane pane, StackPane scoreImage, SelectedFunctionLogicHandle functionLogic) {
        Text text = new Text("");
        if (functionLogic.getClass() == CheckSizeLogic.class) {
            text = new Text("Score: " + (int) functionLogic.getScore() + " GB");
        } else {
            text = new Text(String.format("Score: %.2f", functionLogic.getScore()));
        }

        Image img = new Image("file:DesignFiles/Buttons/" + "templateButton" + ".png");
        text.setFill(Color.ORANGE);
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(1.5);
        text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 14));
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.35));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.10));
        // Set the font size
        text.fontProperty().bind(Bindings.createObjectBinding(() -> {
            double fontSize = 0.05 * Math.min(root.getWidth()*0.65, root.getHeight()*1.25);
            return Font.font("Snap ITC",fontSize);
        }, root.widthProperty(), root.heightProperty()));



        // Create an anchor pane to hold the image and text
//        AnchorPane anchorPane = new AnchorPane();
//        anchorPane.getChildren().addAll(imageView, text);
//
//        // Set the position of the text relative to the image using anchor points
//        AnchorPane.setTopAnchor(text, 1.0);
//        AnchorPane.setLeftAnchor(text, 1.0);
//
//        // Bind the size of the anchor pane to the size of the image view
//        anchorPane.prefWidthProperty().bind(imageView.fitWidthProperty());
//        anchorPane.prefHeightProperty().bind(imageView.fitHeightProperty());

        // Set up a listener to update the font size of the text when the image scales
//        Text finalText = text;
//        imageView.fitWidthProperty().addListener((observable, oldValue, newValue) -> {
//            double scaleFactor = newValue.doubleValue() / oldValue.doubleValue();
//            finalText.setFont(Font.font(finalText.getFont().getFamily(), finalText.getFont().getSize() * scaleFactor));
//        });
//
//        Text finalText1 = text;
//        imageView.fitHeightProperty().addListener((observable, oldValue, newValue) -> {
//            double scaleFactor = newValue.doubleValue() / oldValue.doubleValue();
//            finalText1.setFont(Font.font(finalText1.getFont().getFamily(), finalText1.getFont().getSize() * scaleFactor));
//        });

        resizeToScreen(imageView, root, 1300, 730, -150, -65);
        resizeToImage(text, root, 1300, 730, -150, -65);

        pane.setCenter(imageView);

        scoreImage.getChildren().add(imageView);
        scoreImage.getChildren().add(text);

    }

    public static void setTime(StackPane root, BorderPane pane, StackPane scoreImage, SelectedFunctionLogicHandle functionLogic) {
        Text text = new Text(String.format("Time: %.2f s", functionLogic.getTime()));
        Image img = new Image("file:DesignFiles/Buttons/" + "templateButton" + ".png");
        text.setFill(Color.ORANGE);
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(1.5);
        text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 14));
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.35));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.10));

        // Set the font size
        text.fontProperty().bind(Bindings.createObjectBinding(() -> {
            double fontSize = 0.05 * Math.min(root.getWidth()*0.7, root.getHeight()*1.25);
            return Font.font("Snap ITC",fontSize);
        }, root.widthProperty(), root.heightProperty()));

//        // Create an anchor pane to hold the image and text
//        AnchorPane anchorPane = new AnchorPane();
//        anchorPane.getChildren().addAll(imageView, text);
//
//        // Set the position of the text relative to the image using anchor points
//        AnchorPane.setTopAnchor(text, 10.0);
//        AnchorPane.setLeftAnchor(text, 10.0);
//
//        // Bind the size of the anchor pane to the size of the image view
//        anchorPane.prefWidthProperty().bind(imageView.fitWidthProperty());
//        anchorPane.prefHeightProperty().bind(imageView.fitHeightProperty());

        // Set up a listener to update the font size of the text when the image scales
//        imageView.fitWidthProperty().addListener((observable, oldValue, newValue) -> {
//            double scaleFactor = newValue.doubleValue() / oldValue.doubleValue();
//            text.setFont(Font.font(text.getFont().getFamily(), text.getFont().getSize() * scaleFactor));
//        });
//
//        imageView.fitHeightProperty().addListener((observable, oldValue, newValue) -> {
//            double scaleFactor = newValue.doubleValue() / oldValue.doubleValue();
//            text.setFont(Font.font(text.getFont().getFamily(), text.getFont().getSize() * scaleFactor));
//        });

        resizeToScreen(imageView, root, 1300, 730, 150, -65);
        resizeToImage(text, root, 1300, 730, 150, -65);

        pane.setCenter(imageView);

        scoreImage.getChildren().add(imageView);
        scoreImage.getChildren().add(text);
    }

    public static void setTitle(StackPane root, BorderPane pane, StackPane randomReadMainScreen, String screenTitle) {
        Image img = new Image("file:DesignFiles/Buttons/" + screenTitle + ".png");
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.3));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.25));

        imageView.setTranslateX(0 * root.getWidth() / 600);
        imageView.setTranslateY(-125 * root.getHeight() / 350);

        // Reposition the button when the root pane dimensions change
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
        new Buttons().addButtonsToScreen(r, resultMainScreen, pane, historyBackgroundScreen);
        System.out.println("acasvsavsav");
        if (screenName.substring(0, screenName.lastIndexOf('.')).equals("catchMonster3")) {
            setScore(r, pane, resultMainScreen, new RandomWriteLogic());
            setTime(r, pane, resultMainScreen, new RandomWriteLogic());
            setTitle(r, pane, resultMainScreen, "randomWriteTitle");

        } else if (screenName.substring(0, screenName.lastIndexOf('.')).equals("catchMonster2")) {
            setScore(r, pane, resultMainScreen, new RandomReadLogic());
            setTime(r, pane, resultMainScreen, new RandomReadLogic());
            setTitle(r, pane, resultMainScreen, "randomReadTitle");

        } else if (screenName.substring(0, screenName.lastIndexOf('.')).equals("catchMonster5")) {
            setScore(r, pane, resultMainScreen, new SequentialWriteLogic());
            setTime(r, pane, resultMainScreen, new SequentialWriteLogic());
            setTitle(r, pane, resultMainScreen, "sequentialWriteTitle");

        } else if (screenName.substring(0, screenName.lastIndexOf('.')).equals("catchMonster4")) {
            setScore(r, pane, resultMainScreen, new SequentialReadLogic());
            setTime(r, pane, resultMainScreen, new SequentialReadLogic());
            setTitle(r, pane, resultMainScreen, "sequentialReadTitle");

        } else if (screenName.substring(0, screenName.lastIndexOf('.')).equals("catchMonster1")) {
            setScore(r, pane, resultMainScreen, new CheckSizeLogic());
            setTime(r, pane, resultMainScreen, new CheckSizeLogic());
            setTitle(r, pane, resultMainScreen, "checkSizeTitle");
        }
    }
}