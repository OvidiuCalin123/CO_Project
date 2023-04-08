package Screens.SelectedFunction.SequentialWrite;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URLEncoder;

public class SequentialWriteLogic {
    public void dropbox(StackPane sequentialWriteMainScreen, BorderPane pane){
        // Create a ComboBox with some sample items
        ComboBox<String> dropdown = new ComboBox<>();
        dropdown.getItems().addAll("Option 1", "Option 2", "Option 3");

        // Set a listener to execute an action when an item is selected
        dropdown.setOnAction(event -> {
            String selectedItem = dropdown.getSelectionModel().getSelectedItem();
            System.out.println("Selected: " + selectedItem);
            // TODO: execute action based on selected item
        });

        // Create a layout and add the ComboBox to it

        VBox root = new VBox(dropdown);

        //dropdown.setScaleX(2);
        //dropdown.setScaleY(2);

        //dropdown.setStyle("-fx-font-size: 25px; -fx-background-image: url(\"C:\\Users\lenovo\Desktop\proiecte\CO--project\DesignFiles\Buttons\sequentialWriteTitle.png\");");
        String stylesheet = ".list-cell { -fx-opacity: 0.5; }";

        try {
            // Add the CSS stylesheet to the scene
            sequentialWriteMainScreen.getStylesheets().add("data:text/css," + URLEncoder.encode(stylesheet, "UTF-8"));
        }catch(Exception e){

        };
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
                    imageView.setImage(new Image("file:DesignFiles/Buttons/sequentialWriteTitle.png"));
                    setGraphic(imageView);
                }
            }
        });

        root.setTranslateX(250 * sequentialWriteMainScreen.getWidth() / 600);
        root.setTranslateY(150 * sequentialWriteMainScreen.getHeight() / 350);

        // Reposition the button when the root pane dimensions change
        sequentialWriteMainScreen.widthProperty().addListener((obs, oldVal, newVal) -> {
            root.setTranslateX(250 * newVal.doubleValue() / 600);
        });
        sequentialWriteMainScreen.heightProperty().addListener((obs, oldVal, newVal) -> {
            root.setTranslateY(150 * newVal.doubleValue() / 350);
        });

        //pane.setCenter(root);
        sequentialWriteMainScreen.getChildren().add(root);
    }
}
