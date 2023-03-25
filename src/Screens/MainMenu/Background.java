package Screens.MainMenu;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;

public class Background {

    public void setBackgroundImage(StackPane root){
        Image img = new Image("https://images.wallpapersden.com/image/download/the-mystery-machine-van-scooby-doo_a25uZW2UmZqaraWkpJRmZ21lrWxnZQ.jpg");
        ImageView imageView = new ImageView(img);

        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        javafx.scene.layout.Background bGround = new javafx.scene.layout.Background(bImg);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        imageView.setFitWidth(screenBounds.getWidth());
        imageView.setFitHeight(screenBounds.getHeight());

        root.getChildren().add(imageView);
        root.setBackground(bGround);
    }

}
