package Screens.Result;

import Screens.SelectedFunction.RandomRead.RandomReadLogic;
import Shared.Background;
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

    public static void setScore(StackPane root, BorderPane pane, StackPane scoreImage) {
        RandomReadLogic r = new RandomReadLogic();
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


        // Create an anchor pane to hold the image and text
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(imageView, text);

        // Set the position of the text relative to the image using anchor points
        AnchorPane.setTopAnchor(text, 10.0);
        AnchorPane.setLeftAnchor(text, 10.0);

        // Bind the size of the anchor pane to the size of the image view
        anchorPane.prefWidthProperty().bind(imageView.fitWidthProperty());
        anchorPane.prefHeightProperty().bind(imageView.fitHeightProperty());

        // Set up a listener to update the font size of the text when the image scales
        imageView.fitWidthProperty().addListener((observable, oldValue, newValue) -> {
            double scaleFactor = newValue.doubleValue() / oldValue.doubleValue();
            text.setFont(Font.font(text.getFont().getFamily(), text.getFont().getSize() * scaleFactor));
        });

        imageView.fitHeightProperty().addListener((observable, oldValue, newValue) -> {
            double scaleFactor = newValue.doubleValue() / oldValue.doubleValue();
            text.setFont(Font.font(text.getFont().getFamily(), text.getFont().getSize() * scaleFactor));
        });

        resizeToScreen(imageView, root, 1300, 730, -350, -167.5);
        resizeToImage(text, root, 1300, 730, -350, -167.5);

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



        // Create an anchor pane to hold the image and text
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(imageView, text);

        // Set the position of the text relative to the image using anchor points
        AnchorPane.setTopAnchor(text, 10.0);
        AnchorPane.setLeftAnchor(text, 10.0);

        // Bind the size of the anchor pane to the size of the image view
        anchorPane.prefWidthProperty().bind(imageView.fitWidthProperty());
        anchorPane.prefHeightProperty().bind(imageView.fitHeightProperty());

        // Set up a listener to update the font size of the text when the image scales
        imageView.fitWidthProperty().addListener((observable, oldValue, newValue) -> {
            double scaleFactor = newValue.doubleValue() / oldValue.doubleValue();
            text.setFont(Font.font(text.getFont().getFamily(), text.getFont().getSize() * scaleFactor));
        });

        imageView.fitHeightProperty().addListener((observable, oldValue, newValue) -> {
            double scaleFactor = newValue.doubleValue() / oldValue.doubleValue();
            text.setFont(Font.font(text.getFont().getFamily(), text.getFont().getSize() * scaleFactor));
        });

         resizeToScreen(imageView, root, 1300, 730, 350, -167.5);
         resizeToImage(text, root,1300, 730, 350, -167.5);

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