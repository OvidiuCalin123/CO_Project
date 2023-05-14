package Screens.SelectedFunction.RandomWrite;

import Shared.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import static Shared.Dropbox.dropbox;

public class RandomWriteMain {
    public void setTitle(StackPane root, BorderPane pane, StackPane randomWriteMainScreen){
        Image img = new Image("file:DesignFiles/Buttons/randomWriteTitle.png");
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.3));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.275));

        imageView.setTranslateX(0 * root.getWidth() / 600);
        imageView.setTranslateY(-130 * root.getHeight() / 350);

        root.widthProperty().addListener((obs, oldVal, newVal) -> imageView.setTranslateX(0 * newVal.doubleValue() / 600));
        root.heightProperty().addListener((obs, oldVal, newVal) -> imageView.setTranslateY(-130 * newVal.doubleValue() / 350));

        pane.setCenter(imageView);

        randomWriteMainScreen.getChildren().add(imageView);
    }

    public RandomWriteMain(StackPane root, BorderPane pane){

        StackPane randomWriteMainScreen = new StackPane();

        root.getChildren().add(randomWriteMainScreen);

        randomWriteMainScreen.toFront();

        new Background().setBackgroundImage(root,randomWriteMainScreen, "monster3.png");
        new Buttons().addButtonsToScreen(root, randomWriteMainScreen, pane);

        setTitle(root, pane, randomWriteMainScreen);
    }
}
