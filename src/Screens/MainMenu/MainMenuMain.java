package Screens.MainMenu;

import Shared.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainMenuMain {

    public void setTitle(StackPane root, BorderPane pane) {
        Image img = new Image("file:DesignFiles/Buttons/mainMenuTitle.png");
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.38));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.3));

        imageView.setTranslateX(-167.5 * root.getWidth() / 600);
        imageView.setTranslateY(-120 * root.getHeight() / 350);

        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setTranslateX(-167.5 * newVal.doubleValue() / 600);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setTranslateY(-120 * newVal.doubleValue() / 350);
        });

        pane.setCenter(imageView);

        root.getChildren().add(imageView);
    }

    public MainMenuMain(StackPane root, BorderPane pane) {

        new Background().setBackgroundImage(root, "menu.jpg");

        new Buttons().addButtonsToScreen(root, pane);

        setTitle(root, pane);
    }
}
