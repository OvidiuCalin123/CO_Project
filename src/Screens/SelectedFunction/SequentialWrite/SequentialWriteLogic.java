package Screens.SelectedFunction.SequentialWrite;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URLEncoder;

public class SequentialWriteLogic {
    public void dropbox(StackPane sequentialWriteMainScreen, BorderPane pane){
        // Create a ComboBox with some sample items
        ComboBox<String> dropdown = new ComboBox<>();

        dropdown.setStyle("-fx-background-image: url('file:DesignFiles/Buttons/selectSize.png');" +
                "-fx-background-position: center;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-padding: 0 350 50 20;" +
                "-fx-background-size: 100% 100%;" +
                "-fx-background-color: transparent;");

        dropdown.getItems().addAll("1 GB", "500 MB", "100 MB");

        // Set a listener to execute an action when an item is selected
        dropdown.setOnAction(event -> {
            String selectedItem = dropdown.getSelectionModel().getSelectedItem();
            System.out.println("Selected: " + selectedItem);
            // TODO: execute action based on selected item
        });

        // Create a layout and add the ComboBox to it

        VBox dropboxRoot = new VBox(dropdown);

        dropdown.setCellFactory(param -> new ListCell<String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item);
//                    imageView.setImage(new Image("file:DesignFiles/Buttons/sequentialWriteTitle.png"));
//                    imageView.fitWidthProperty().bind(sequentialWriteMainScreen.widthProperty().multiply(0.1));
//                    imageView.fitHeightProperty().bind(sequentialWriteMainScreen.heightProperty().multiply(0.1));
//                    setGraphic(imageView);
                }
            }
        });
        dropboxRoot.setStyle("-fx-background-color: transparent;");
        dropboxRoot.setTranslateX(250 * sequentialWriteMainScreen.getWidth() / 600);
        dropboxRoot.setTranslateY(150 * sequentialWriteMainScreen.getHeight() / 350);

        // Reposition the button when the root pane dimensions change
        sequentialWriteMainScreen.widthProperty().addListener((obs, oldVal, newVal) -> {
            dropboxRoot.setTranslateX(250 * newVal.doubleValue() / 600);
        });
        sequentialWriteMainScreen.heightProperty().addListener((obs, oldVal, newVal) -> {
            dropboxRoot.setTranslateY(150 * newVal.doubleValue() / 350);
        });

        //pane.setCenter(root);
        sequentialWriteMainScreen.getChildren().add(dropboxRoot);
    }
}
