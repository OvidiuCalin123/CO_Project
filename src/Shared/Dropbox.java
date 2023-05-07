package Shared;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Dropbox {

    public static void dropbox(StackPane root, StackPane sequentialWriteMainScreen, String[] options){

        // Create a ComboBox
        ComboBox<String> dropdown = new ComboBox<>();

        dropdown.setStyle("-fx-background-image: url('file:DesignFiles/Buttons/templateButton.png');" +
                "-fx-background-position: center right;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: 100% 100%;" +
                "-fx-background-color: transparent;");

        // Add items to the ComboBox
        dropdown.getItems().addAll(options);

        // Set the preferred size of the ComboBox
        dropdown.setPrefSize(100, 20);

        // Bind the width and height of the ComboBox to the size of the root StackPane
        dropdown.prefWidthProperty().bind(root.widthProperty().multiply(0.25));
        dropdown.prefHeightProperty().bind(root.heightProperty().multiply(0.09));

        // Set the initial position of the ComboBox
        dropdown.setTranslateX(65 * root.getWidth() / 1280);
        dropdown.setTranslateY(-15 * root.getHeight() / 720);

        // Reposition the ComboBox when the root StackPane dimensions change
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            dropdown.setTranslateX(65 * newVal.doubleValue() / 1280);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            dropdown.setTranslateY(-15 * newVal.doubleValue() / 720);
        });


        dropdown.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {

                    Text text = new Text(item);
                    text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 25));
                    text.setStyle("-fx-fill: orange; -fx-stroke: black; -fx-stroke-width: 1px;");

                    text.fontProperty().bind(Bindings.createObjectBinding(() -> {
                        double fontSize = 0.03 * Math.min(root.getWidth()*0.65, root.getHeight()*1.25);
                        return Font.font("Snap ITC",fontSize);
                    }, root.widthProperty(), root.heightProperty()));

                    setGraphic(text);
                }
            }
        });

        dropdown.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("SELECT");
                } else {
                    Text text = new Text(item);
                    text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 30));
                    //setText(text.getText());
                    setFont(text.getFont());

                    text.setStyle("-fx-fill: orange; -fx-stroke: black; -fx-stroke-width: 1.25px;");

                    if(text.getText().equals("1 GB")){

                        text.fontProperty().bind(Bindings.createObjectBinding(() -> {
                            double fontSize = 0.04 * Math.min(root.getWidth()*1.1, root.getHeight()*1.25);
                            return Font.font("Snap ITC",fontSize);
                        }, root.widthProperty(), root.heightProperty()));

                    }else if(text.getText().equals("500 MB")){

                        text.fontProperty().bind(Bindings.createObjectBinding(() -> {
                            double fontSize = 0.04 * Math.min(root.getWidth()*0.7, root.getHeight()*1.25);
                            return Font.font("Snap ITC",fontSize);
                        }, root.widthProperty(), root.heightProperty()));

                    }else{

                        text.fontProperty().bind(Bindings.createObjectBinding(() -> {
                            double fontSize = 0.04 * Math.min(root.getWidth()*0.7, root.getHeight()*1.25);
                            return Font.font("Snap ITC",fontSize);
                        }, root.widthProperty(), root.heightProperty()));

                    }

                    setGraphic(text);
                }
            }
        });

        dropdown.setOnAction(event -> {
            String selectedItem = dropdown.getSelectionModel().getSelectedItem();
            System.out.println(selectedItem);
            // Set font, color, and stroke for selected item
            if (selectedItem != null) {

                Text text = new Text(selectedItem);
                text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 12));
                text.setFill(Color.ORANGE);

            }
        });

        dropdown.setMinWidth(Region.USE_COMPUTED_SIZE * 0.5);
        dropdown.setMinHeight(Region.USE_COMPUTED_SIZE * 0.5);
        dropdown.setMaxWidth(Region.USE_COMPUTED_SIZE);
        dropdown.setMaxHeight(Region.USE_COMPUTED_SIZE);

        // Add the ComboBox to the StackPane
        sequentialWriteMainScreen.getChildren().add(dropdown);
    }

}
