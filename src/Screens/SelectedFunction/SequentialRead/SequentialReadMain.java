package Screens.SelectedFunction.SequentialRead;

import Shared.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class SequentialReadMain {
    public void setTitle(StackPane root, BorderPane pane, StackPane sequentialReadMainScreen){
        Image img = new Image("file:DesignFiles/Buttons/SequentialReadTitle.png");
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.3));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.275));

        imageView.setTranslateX(0 * root.getWidth() / 600);
        imageView.setTranslateY(-130 * root.getHeight() / 350);

        root.widthProperty().addListener((obs, oldVal, newVal) -> imageView.setTranslateX(0 * newVal.doubleValue() / 600));
        root.heightProperty().addListener((obs, oldVal, newVal) -> imageView.setTranslateY(-130 * newVal.doubleValue() / 350));

        pane.setCenter(imageView);

        sequentialReadMainScreen.getChildren().add(imageView);
    }

    public SequentialReadMain(StackPane root, BorderPane pane){

        StackPane sequentialReadMainScreen = new StackPane();

        root.getChildren().add(sequentialReadMainScreen);

        sequentialReadMainScreen.toFront();

        new Background().setBackgroundImage(root,sequentialReadMainScreen, "monster4.png");
        new Buttons().addButtonsToScreen(root, sequentialReadMainScreen, pane);

        setTitle(root, pane, sequentialReadMainScreen);
    }
}
