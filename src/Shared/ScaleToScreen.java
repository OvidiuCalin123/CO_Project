package Shared;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ScaleToScreen {

    public static void resizeToScreen(ImageView image, StackPane root, double xScale, double yScale, double xCoords, double yCoords){
        image.fitWidthProperty().bind(root.widthProperty().multiply(0.3));
        image.fitHeightProperty().bind(root.heightProperty().multiply(0.10));

        image.setTranslateX(xCoords * root.getWidth() / 600);
        image.setTranslateY(yCoords * root.getHeight() / 350);

        // Reposition the button when the root pane dimensions change
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            image.setTranslateX(xCoords * newVal.doubleValue() / 600);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            image.setTranslateY(yCoords * newVal.doubleValue() / 350);
        });

        //pane.setCenter(image);
    }

    public static void resizeToImage(Text text, StackPane root, double xScale, double yScale, double xCoords, double yCoords) {

        text.setTranslateX(xCoords * root.getWidth() / 600);
        text.setTranslateY(yCoords * root.getHeight() / 350);

        // Reposition the button when the root pane dimensions change
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            text.setTranslateX(xCoords * newVal.doubleValue() / 600);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            text.setTranslateY(yCoords * newVal.doubleValue() / 350);
        });
    }


}
