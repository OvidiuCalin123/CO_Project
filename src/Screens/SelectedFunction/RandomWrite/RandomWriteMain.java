package Screens.SelectedFunction.RandomWrite;

import Shared.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class RandomWriteMain {
    public void setTitle(StackPane root, BorderPane pane, StackPane randomReadMainScreen){
        Image img = new Image("file:DesignFiles/Background/RandomWrite_Title.png");
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

    public RandomWriteMain(StackPane root, BorderPane pane){
        // Create a new pane for the new screen
        StackPane randomWriteMainScreen = new StackPane();

        // Add the new pane to the stack pane
        root.getChildren().add(randomWriteMainScreen);

        // Bring the new pane to the front
        randomWriteMainScreen.toFront();

        // Set the background image
        new Background().setBackgroundImage(root,randomWriteMainScreen, "monster3.png");
        // Add buttons
        new Buttons().addButtonsToScreen(randomWriteMainScreen, pane);

        setTitle(root, pane, randomWriteMainScreen);

    }

}
