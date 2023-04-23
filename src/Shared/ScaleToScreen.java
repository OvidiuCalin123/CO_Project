package Shared;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ScaleToScreen {

    public static void resizeToScreen(ImageView image, StackPane root, double xScale, double yScale, double xCoords, double yCoords){
        // Bind scale properties to root pane dimensions
        image.scaleXProperty().bind(root.widthProperty().divide(xScale));
        image.scaleYProperty().bind(root.heightProperty().divide(yScale));

        // Set the initial position of the button
        image.setTranslateX(xCoords * root.getWidth() / xScale);
        image.setTranslateY(yCoords * root.getHeight() / yScale);

        // Reposition the button when the root pane dimensions change
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            image.setTranslateX(xCoords * newVal.doubleValue() / xScale);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            image.setTranslateY(yCoords * newVal.doubleValue() / yScale);
        });
    }

    public static void resizeToImage(Text text, StackPane root, double xScale, double yScale, double xCoords, double yCoords) {
        text.scaleXProperty().bind(root.widthProperty().divide(xScale));
        text.scaleYProperty().bind(root.heightProperty().divide(yScale));

        // Set the initial position of the button
        text.setTranslateX(xCoords * root.getWidth() / xScale);
        text.setTranslateY(yCoords * root.getHeight() / yScale);

        // Reposition the button when the root pane dimensions change
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            text.setTranslateX(xCoords * newVal.doubleValue() / xScale);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            text.setTranslateY(yCoords * newVal.doubleValue() / yScale);
        });
    }


}
