package Screens.SelectedFunction.CheckSize;

import Shared.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class CheckSizeMain {
    public void setTitle(StackPane root, BorderPane pane, StackPane checkSizeMainScreen) {
        Image img = new Image("file:DesignFiles/Buttons/" + "checkSizeTitle" + ".png");
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.3));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.25));

        imageView.setTranslateX(0 * root.getWidth() / 600);
        imageView.setTranslateY(-125 * root.getHeight() / 350);

        root.widthProperty().addListener((obs, oldVal, newVal) -> imageView.setTranslateX(0 * newVal.doubleValue() / 600));
        root.heightProperty().addListener((obs, oldVal, newVal) -> imageView.setTranslateY(-125 * newVal.doubleValue() / 350));

        pane.setCenter(imageView);

        checkSizeMainScreen.getChildren().add(imageView);
    }

    public CheckSizeMain(StackPane root, BorderPane pane) {

        StackPane checkSizeMainScreen = new StackPane();

        root.getChildren().add(checkSizeMainScreen);

        checkSizeMainScreen.toFront();

        new Background().setBackgroundImage(root, checkSizeMainScreen, "monster1.jpg");
        new Buttons().addButtonsToScreen(root, checkSizeMainScreen, pane);

        setTitle(root, pane, checkSizeMainScreen);
    }
}
