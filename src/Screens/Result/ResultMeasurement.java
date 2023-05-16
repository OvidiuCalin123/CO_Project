package Screens.Result;

import Screens.SelectedFunction.CheckSize.CheckSizeLogic;
import Screens.SelectedFunction.SelectedFunctionLogicHandle;
import javafx.beans.binding.Bindings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static Screens.SelectedFunction.CheckSize.CheckSizeLogic.printRemainingSpace;
import static Shared.ScaleToScreen.resizeToImage;
import static Shared.ScaleToScreen.resizeToScreen;

public class ResultMeasurement {

    public static void setScore(StackPane root, BorderPane pane, StackPane scoreImage, SelectedFunctionLogicHandle functionLogic) {
        Text text = new Text("");
        if (functionLogic.getClass() == CheckSizeLogic.class) {
            text = new Text(String.format("Size: %.2f GB", functionLogic.getScore()));
        } else {
            text = new Text(String.format("Score: %.2f MB/s", functionLogic.getScore()));
        }

        Image img = new Image("file:DesignFiles/Buttons/" + "templateButton" + ".png");
        text.setFill(Color.ORANGE);
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(1.5);
        text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 14));
        ImageView imageView = new ImageView(img);

        text.fontProperty().bind(Bindings.createObjectBinding(() -> {
            double fontSize = 0.05 * Math.min(root.getWidth()*0.65, root.getHeight()*1.25);
            return Font.font("Snap ITC",fontSize);
        }, root.widthProperty(), root.heightProperty()));

        resizeToScreen(imageView, root , -150, -65, 0.425);
        resizeToImage(text, root, -150, -65);

        pane.setCenter(imageView);

        scoreImage.getChildren().add(imageView);
        scoreImage.getChildren().add(text);

    }

    public static void setTime(StackPane root, BorderPane pane, StackPane scoreImage, SelectedFunctionLogicHandle functionLogic) {

        if (functionLogic.getClass() != CheckSizeLogic.class) {
            Text text = new Text(String.format("Time: %.2f s", functionLogic.getTime()));

            Image img = new Image("file:DesignFiles/Buttons/" + "templateButton" + ".png");
            text.setFill(Color.ORANGE);
            text.setStroke(Color.BLACK);
            text.setStrokeWidth(1.5);
            text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 14));
            ImageView imageView = new ImageView(img);

            text.fontProperty().bind(Bindings.createObjectBinding(() -> {
                double fontSize = 0.05 * Math.min(root.getWidth() * 0.7, root.getHeight() * 1.25);
                return Font.font("Snap ITC", fontSize);
            }, root.widthProperty(), root.heightProperty()));

            resizeToScreen(imageView, root, 150, -65, 0.3);
            resizeToImage(text, root, 150, -65);

            pane.setCenter(imageView);

            scoreImage.getChildren().add(imageView);
            scoreImage.getChildren().add(text);
        }
        else
        {
            Text text = new Text("Size left: "+printRemainingSpace()+" GB");

            Image img = new Image("file:DesignFiles/Buttons/" + "templateButton" + ".png");
            text.setFill(Color.ORANGE);
            text.setStroke(Color.BLACK);
            text.setStrokeWidth(1.5);
            text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 14));
            ImageView imageView = new ImageView(img);

            text.fontProperty().bind(Bindings.createObjectBinding(() -> {
                double fontSize = 0.05 * Math.min(root.getWidth() * 0.7, root.getHeight() * 1.25);
                return Font.font("Snap ITC", fontSize);
            }, root.widthProperty(), root.heightProperty()));

            resizeToScreen(imageView, root, 150, -65, 0.375);
            resizeToImage(text, root, 150, -65);

            pane.setCenter(imageView);

            scoreImage.getChildren().add(imageView);
            scoreImage.getChildren().add(text);
        }
    }

}
