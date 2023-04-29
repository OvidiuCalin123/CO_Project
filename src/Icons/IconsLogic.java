package Icons;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class IconsLogic {

    public static void setAppIcon(Stage primaryStage){

        Image icon = new Image("file:DesignFiles/Icons/AppIconShadow.jpg");
        primaryStage.getIcons().add(icon);
    }

}

