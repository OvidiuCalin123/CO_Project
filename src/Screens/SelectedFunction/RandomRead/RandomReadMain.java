package Screens.SelectedFunction.RandomRead;

import Shared.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import static Shared.Dropbox.dropbox;

public class RandomReadMain {

    public void setTitle(StackPane root, BorderPane pane, StackPane randomReadMainScreen){
        Image img = new Image("file:DesignFiles/Buttons/randomReadTitle.png");
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.4));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.275));

        imageView.setTranslateX(0 * root.getWidth() / 600);
        imageView.setTranslateY(-130 * root.getHeight() / 350);

        root.widthProperty().addListener((obs, oldVal, newVal) -> imageView.setTranslateX(0 * newVal.doubleValue() / 600));
        root.heightProperty().addListener((obs, oldVal, newVal) -> imageView.setTranslateY(-130 * newVal.doubleValue() / 350));

        pane.setCenter(imageView);

        randomReadMainScreen.getChildren().add(imageView);
    }

    public RandomReadMain(StackPane root, BorderPane pane){

        StackPane randomReadMainScreen = new StackPane();

        root.getChildren().add(randomReadMainScreen);

        randomReadMainScreen.toFront();

        new Background().setBackgroundImage(root,randomReadMainScreen, "monster2.png");
        new Buttons().addButtonsToScreen(root, randomReadMainScreen, pane);

        dropbox(root, randomReadMainScreen, new String[]{"1 GB", "500 MB", "100 MB"}, -400, -30);

        setTitle(root, pane, randomReadMainScreen);
    }
}
