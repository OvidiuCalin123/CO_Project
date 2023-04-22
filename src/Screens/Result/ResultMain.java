package Screens.Result;

import Screens.SelectedFunction.RandomRead.RandomReadLogic;
import Shared.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ResultMain {

    public static void setScore(StackPane root, BorderPane pane, StackPane scoreImage){
        RandomReadLogic r=new RandomReadLogic();
        Text text = new Text(String.format("Score: %.2f", r.getReadSpeed()));
        Image img = new Image("file:DesignFiles/Buttons/" + "templateButton" + ".png");
        text.setFill(Color.ORANGE);
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(2);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.35));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.10));
        double fontSize = root.getHeight() * 0.06; // adjust the scaling factor as needed

        // Set the font size
        text.setFont(Font.font("Snap ITC", fontSize));

        imageView.setTranslateX(-180 * root.getWidth() / 600);
        imageView.setTranslateY(-80 * root.getHeight() / 350);
        text.setTranslateX(-180 * root.getWidth() / 600);
        text.setTranslateY(-80 * root.getHeight() / 350);

        // Reposition the button when the root pane dimensions change
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setTranslateX(-180 * newVal.doubleValue() / 600);
            text.setTranslateX(-180 * newVal.doubleValue() / 600);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setTranslateY(-80 * newVal.doubleValue() / 350);
            text.setTranslateY(-80 * newVal.doubleValue() / 350);
        });

        pane.setCenter(imageView);

        scoreImage.getChildren().add(imageView);
        scoreImage.getChildren().add(text);
    }

    public static void setTime(StackPane root, BorderPane pane, StackPane scoreImage){
        RandomReadLogic r=new RandomReadLogic();
        Text text = new Text(String.format("Time: %.2f s", r.getTime()));
        Image img = new Image("file:DesignFiles/Buttons/" + "templateButton" + ".png");
        text.setFill(Color.ORANGE);
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(2);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.35));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.10));
        double fontSize = root.getHeight() * 0.06; // adjust the scaling factor as needed

        // Set the font size
        text.setFont(Font.font("Snap ITC", fontSize));

        imageView.setTranslateX(180 * root.getWidth() / 600);
        imageView.setTranslateY(-80 * root.getHeight() / 350);
        text.setTranslateX(180 * root.getWidth() / 600);
        text.setTranslateY(-80 * root.getHeight() / 350);

        // Reposition the button when the root pane dimensions change
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setTranslateX(-180 * newVal.doubleValue() / 600);
            text.setTranslateX(-180 * newVal.doubleValue() / 600);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setTranslateY(-80 * newVal.doubleValue() / 350);
            text.setTranslateY(-80 * newVal.doubleValue() / 350);
        });

        pane.setCenter(imageView);

        scoreImage.getChildren().add(imageView);
        scoreImage.getChildren().add(text);
    }

    public static void showResult(StackPane r, String screenName, BorderPane pane){

        StackPane resultMainScreen = new StackPane();

        r.getChildren().add(resultMainScreen);

        resultMainScreen.toFront();

        new Background().setBackgroundImage(r,resultMainScreen,screenName);
        new Buttons().addButtonsToScreen(r, resultMainScreen, pane);

        if(screenName.substring(0, screenName.lastIndexOf('.')).equals("catchMonster2")){
            setScore(r, pane, resultMainScreen);
            setTime(r, pane, resultMainScreen);
        }
    }
}