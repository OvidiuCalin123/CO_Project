package Screens.MainMenu.ImageLogic;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;

public class Background {

    public void setBackgroundImage(StackPane root){
        Image img = new Image("https://static.kino.de/49/f5/12/13a24538eed5b8b8a3c832e30e_ZmMgN2JmMWM0ZThmZmZmIDE2MDAgMTIwMAM0NTEyM2ViOGMyMA==_scooby-doo.jpg");
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
