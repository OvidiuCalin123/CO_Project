package Screens.SelectedFunction.RandomRead;

import Shared.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class RandomReadMain {
    public void setTitle(StackPane root, BorderPane pane, StackPane randomReadMainScreen){
        Image img = new Image("file:DesignFiles/Buttons/randomReadTitle.png");
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.4));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.275));

        imageView.setTranslateX(0 * root.getWidth() / 600);
        imageView.setTranslateY(-130 * root.getHeight() / 350);

        // Reposition the button when the root pane dimensions change
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setTranslateX(0 * newVal.doubleValue() / 600);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setTranslateY(-130 * newVal.doubleValue() / 350);
        });

        pane.setCenter(imageView);

        randomReadMainScreen.getChildren().add(imageView);
    }

    public RandomReadMain(StackPane root, BorderPane pane){
        // Create a new pane for the new screen
        StackPane randomReadMainScreen = new StackPane();

        // Add the new pane to the stack pane
        root.getChildren().add(randomReadMainScreen);

        // Bring the new pane to the front
        randomReadMainScreen.toFront();

        // Set the background image
        new Background().setBackgroundImage(root,randomReadMainScreen, "monster2.png");
        // Add buttons
        new Buttons().addButtonsToScreen(root, randomReadMainScreen, pane);

        setTitle(root, pane, randomReadMainScreen);

    }

}
