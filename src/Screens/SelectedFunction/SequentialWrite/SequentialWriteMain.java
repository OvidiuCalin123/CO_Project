package Screens.SelectedFunction.SequentialWrite;

import Shared.Background;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class SequentialWriteMain {
    public void setTitle(StackPane root, BorderPane pane, StackPane sequentialWriteMainScreen) {
        Image img = new Image("file:DesignFiles/Buttons/sequentialWriteTitle.png");
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.3));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.275));

        imageView.setTranslateX(0 * root.getWidth() / 600);
        imageView.setTranslateY(-130 * root.getHeight() / 350);

        root.widthProperty().addListener((obs, oldVal, newVal) -> imageView.setTranslateX(0 * newVal.doubleValue() / 600));
        root.heightProperty().addListener((obs, oldVal, newVal) -> imageView.setTranslateY(-130 * newVal.doubleValue() / 350));

        pane.setCenter(imageView);

        sequentialWriteMainScreen.getChildren().add(imageView);
    }

    public SequentialWriteMain(StackPane root, BorderPane pane) {

        StackPane sequentialWriteMainScreen = new StackPane();

        root.getChildren().add(sequentialWriteMainScreen);

        sequentialWriteMainScreen.toFront();

        new Background().setBackgroundImage(root, sequentialWriteMainScreen, "monster5.jpg");

        setTitle(root, pane, sequentialWriteMainScreen);
        Button b = new Button();

        new Buttons().addButtonsToScreen(root, sequentialWriteMainScreen, pane);
    }
}
