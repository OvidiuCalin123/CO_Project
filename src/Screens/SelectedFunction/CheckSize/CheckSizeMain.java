package Screens.SelectedFunction.CheckSize;

import Shared.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.File;

import static Shared.Dropbox.dropbox;

public class CheckSizeMain {
    public void setTitle(StackPane root, BorderPane pane, StackPane checkSizeMainScreen){
        Image img = new Image("file:DesignFiles/Buttons/" + "checkSizeTitle" + ".png");
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.3));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(0.25));

        imageView.setTranslateX(0 * root.getWidth() / 600);
        imageView.setTranslateY(-125 * root.getHeight() / 350);

        // Reposition the button when the root pane dimensions change
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setTranslateX(0 * newVal.doubleValue() / 600);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setTranslateY(-125 * newVal.doubleValue() / 350);
        });

        pane.setCenter(imageView);

        checkSizeMainScreen.getChildren().add(imageView);
    }

    public String[] getPCPartition() {
        // Get a list of partitions
        File[] partitions = File.listRoots();

        // Create a String array to hold partition names
        String[] partitionNames = new String[partitions.length];

        // Iterate over the list of partitions and get the partition name for each one
        for (int i = 0; i < partitions.length; i++) {
            partitionNames[i] = partitions[i].getAbsolutePath();
        }

        // Return the array of partition names
        return partitionNames;
    }

    public CheckSizeMain(StackPane root, BorderPane pane){

        StackPane checkSizeMainScreen = new StackPane();

        root.getChildren().add(checkSizeMainScreen);

        checkSizeMainScreen.toFront();

        new Background().setBackgroundImage(root,checkSizeMainScreen,"monster1.jpg");
        new Buttons().addButtonsToScreen(root, checkSizeMainScreen, pane);

        setTitle(root, pane, checkSizeMainScreen);

    }

}
