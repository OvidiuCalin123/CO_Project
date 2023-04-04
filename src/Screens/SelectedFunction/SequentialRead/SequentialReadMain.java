package Screens.SelectedFunction.SequentialRead;

import Shared.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class SequentialReadMain {
    public void setTitle(StackPane root, BorderPane pane, StackPane randomReadMainScreen){
        Image img = new Image("file:DesignFiles/Background/SequentialRead_Title.png");
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.3));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.1));

        imageView.setTranslateX(-180 * root.getWidth() / 600);
        imageView.setTranslateY(-140 * root.getHeight() / 350);

        // Reposition the button when the root pane dimensions change
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setTranslateX(-180 * newVal.doubleValue() / 600);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setTranslateY(-140 * newVal.doubleValue() / 350);
        });

        pane.setCenter(imageView);

        randomReadMainScreen.getChildren().add(imageView);
    }

    public SequentialReadMain(StackPane root, BorderPane pane){
        // Create a new pane for the new screen
        StackPane sequentialReadMainScreen = new StackPane();

        // Add the new pane to the stack pane
        root.getChildren().add(sequentialReadMainScreen);

        // Bring the new pane to the front
        sequentialReadMainScreen.toFront();

        // Set the background image
        new Background().setBackgroundImage(root,sequentialReadMainScreen, "monster4.png");
        // Add buttons
        new Buttons().addButtonsToScreen(sequentialReadMainScreen, pane);

        setTitle(root, pane, sequentialReadMainScreen);

    }

}