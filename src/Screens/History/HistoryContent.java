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

    private TableView<Person> tableView;

    public HistoryContent(StackPane root, BorderPane pane) {
        // Set padding and background color
        setPadding(new Insets(20));
        setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        // Create the table columns
        TableColumn<Person, String> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreCol.setResizable(false);

        TableColumn<Person, String> runTimeCol = new TableColumn<>("Run time");
        runTimeCol.setCellValueFactory(new PropertyValueFactory<>("run_time"));
        runTimeCol.setResizable(false);

        TableColumn<Person, String> hdd_ssdCol = new TableColumn<>("HDD/SDD");
        hdd_ssdCol.setCellValueFactory(new PropertyValueFactory<>("hdd_sdd"));
        hdd_ssdCol.setResizable(false);

        TableColumn<Person, String> testTimeCol = new TableColumn<>("Test time");
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

        Person newPerson = new Person("ceva", "altceva", "dcs", "ceva5");
        addData(newPerson);

    }

    public void addData(Person person) {
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
    public void setData(ObservableList<Person> data) {
        // Set the data for the table view
        tableView.setItems(data);
    }
}

class Person {
    private String score;
    private String run_time;
    private String hdd_ssd;
    private String test_time;

    public Person(String score, String run_time, String hdd_ssd, String test_time) {
        this.score = score;
        this.run_time = run_time;
        this.hdd_ssd = hdd_ssd;
        this.test_time = test_time;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRun_time() {
        return run_time;
    }

    public void setRun_time(String run_time) {
        this.run_time = run_time;
    }

    public String getHdd_ssd() {
        return hdd_ssd;
    }

    public void setHdd_ssd(String hdd_ssd) {
        this.hdd_ssd = hdd_ssd;
    }

    public String getTest_time() {
        return test_time;
    }

    public void setTest_time(String test_time) {
        this.test_time = test_time;
    }
}

