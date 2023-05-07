package Screens.SelectedFunction.SequentialWrite;

import Shared.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import static Shared.Dropbox.dropbox;

public class SequentialWriteMain {
    public void setTitle(StackPane root, BorderPane pane, StackPane sequentialWriteMainScreen){
        Image img = new Image("file:DesignFiles/Buttons/sequentialWriteTitle.png");
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.3));
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

        sequentialWriteMainScreen.getChildren().add(imageView);
    }

    public SequentialWriteMain(StackPane root, BorderPane pane){
        // Create a new pane for the new screen
        StackPane sequentialWriteMainScreen = new StackPane();

        // Add the new pane to the stack pane
        root.getChildren().add(sequentialWriteMainScreen);

        // Bring the new pane to the front
        sequentialWriteMainScreen.toFront();

        // Set the background image
        new Background().setBackgroundImage(root,sequentialWriteMainScreen,"monster5.jpg");
        // Add buttons

        setTitle(root, pane, sequentialWriteMainScreen);

        dropbox(root, sequentialWriteMainScreen, new String[]{"1 GB", "500 MB", "100 MB"}, 65, -14);

        new Buttons().addButtonsToScreen(root, sequentialWriteMainScreen, pane);

    }

}
