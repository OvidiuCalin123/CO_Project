package Shared;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class Background {
    public void setBackgroundImage(StackPane root, String backgroundImagePath) {

        BorderPane pane = new BorderPane();
        Image img = new Image("file:DesignFiles/Background/" + backgroundImagePath);
        ImageView imageView = new ImageView(img);

        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        javafx.scene.layout.Background bGround = new javafx.scene.layout.Background(bImg);

        imageView.fitWidthProperty().bind(root.widthProperty());
        imageView.fitHeightProperty().bind(root.heightProperty());

        pane.setCenter(imageView);

        root.getChildren().add(imageView);
        root.setBackground(bGround);
    }

    public void setBackgroundImage(StackPane root, StackPane currentScreenPane, String backgroundImagePath) {

        BorderPane pane = new BorderPane();
        Image img = new Image("file:DesignFiles/Background/" + backgroundImagePath);
        ImageView imageView = new ImageView(img);

        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        javafx.scene.layout.Background bGround = new javafx.scene.layout.Background(bImg);

        imageView.fitWidthProperty().bind(root.widthProperty());
        imageView.fitHeightProperty().bind(root.heightProperty());

        pane.setCenter(imageView);

        currentScreenPane.getChildren().add(imageView);
        currentScreenPane.setBackground(bGround);
    }
}
