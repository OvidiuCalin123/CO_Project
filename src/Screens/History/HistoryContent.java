package Screens.History;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static Shared.ButtonsHelper.buttonBuilder;
import static Shared.ButtonsHelper.scaleButton;

public class HistoryContent extends BorderPane {

    private TableView<HistoryModel> tableView;

    public HistoryContent(StackPane root, BorderPane pane) {
        // Set padding and background color
        setPadding(new Insets(20));
        setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        // Create the table columns
        TableColumn<HistoryModel, String> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreCol.setResizable(false);

        TableColumn<HistoryModel, String> runTimeCol = new TableColumn<>("Run Time");
        runTimeCol.setCellValueFactory(new PropertyValueFactory<>("run_time"));
        runTimeCol.setResizable(false);

        TableColumn<HistoryModel, String> hdd_ssdCol = new TableColumn<>("HDD/SSD");
        hdd_ssdCol.setCellValueFactory(new PropertyValueFactory<>("hdd_ssd"));
        hdd_ssdCol.setResizable(false);

        TableColumn<HistoryModel, String> testTimeCol = new TableColumn<>("Test Time");
        testTimeCol.setCellValueFactory(new PropertyValueFactory<>("test_time"));
        testTimeCol.setResizable(false);

        hdd_ssdCol.setPrefWidth(200);

        // Create the table view and add the columns
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(scoreCol, runTimeCol, hdd_ssdCol, testTimeCol);
        tableView.setPrefHeight(400);
        tableView.setOpacity(0.7);

        // Create the scroll pane and add the table view
        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // Create the title label
        Label titleLabel = new Label("History Table");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web("#0076a3"));

        // Add the title label and scroll pane to this border pane
        setTop(titleLabel);
        setAlignment(titleLabel, Pos.CENTER);
        setCenter(scrollPane);
        root.getChildren().add(tableView);

        back(root, pane);

        HistoryModel newPerson = new HistoryModel("ceva", "altceva", "dcs", "ceva5");
        addData(newPerson);

    }

    public void addData(HistoryModel person) {
        tableView.getItems().add(person);
    }


    public void back(StackPane sequentialReadMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            sequentialReadMainScreen.toBack();
            sequentialReadMainScreen.toBack();
            ObservableList<Node> children = sequentialReadMainScreen.getChildren();
            int index = children.size() - 4;
            sequentialReadMainScreen.getChildren().get(index).toBack();
            sequentialReadMainScreen.getChildren().get(index + 1).toBack();
            sequentialReadMainScreen.getChildren().get(index + 2).toBack();
            sequentialReadMainScreen.getChildren().get(index + 2).toBack();
            sequentialReadMainScreen.getChildren().get(index + 3).toBack();

        };

        double xCoords = -1400;
        double yCoords = 800;

        double xScale = 3000;
        double yScale = 1900;

        Button b = buttonBuilder(sequentialReadMainScreen, event, pane);

       // scaleButton(b,sequentialReadMainScreen,xScale,yScale, xCoords, yCoords);

        // Set the initial position of the button
        b.setTranslateX(xCoords * sequentialReadMainScreen.getWidth() / xScale);
        b.setTranslateY(yCoords * sequentialReadMainScreen.getHeight() / yScale);

        // Reposition the button when the root pane dimensions change
        sequentialReadMainScreen.widthProperty().addListener((obs, oldVal, newVal) -> {
            b.setTranslateX(xCoords * newVal.doubleValue() / xScale);
        });
        sequentialReadMainScreen.heightProperty().addListener((obs, oldVal, newVal) -> {
            b.setTranslateY(yCoords * newVal.doubleValue() / yScale);
        });

    }
    public void setData(ObservableList<HistoryModel> data) {
        // Set the data for the table view
        tableView.setItems(data);
    }
}



