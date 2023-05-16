package Icons;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class IconsLogic {

    public static void setAppIcon(Stage primaryStage) {

        Image icon = new Image("file:DesignFiles/Icons/AppIconShadow.jpg");
        primaryStage.getIcons().add(icon);
    }
}

