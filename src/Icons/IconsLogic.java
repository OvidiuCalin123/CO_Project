package Icons;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class IconsLogic {

    public static void setAppIcon(Stage primaryStage){

        Image icon = new Image("file:DesignFiles/Icons/AppIcon.jpg");
        primaryStage.getIcons().add(icon);
    }

    private static Image normalImage;

    public static void setCursorImage(Scene scene) {

        normalImage = new Image("file:DesignFiles/Icons/CursorWannabe.png", 50, 50, true, true);

        ImageView imageView = new ImageView(normalImage);
        imageView.setScaleX(2.0);
        imageView.setScaleY(2.0);
        imageView.setStyle("-fx-background-color: transparent;");

        ImageCursor customCursor = new ImageCursor(imageView.snapshot(null, null), 50, 50);


        scene.setCursor(customCursor);
        }
}

