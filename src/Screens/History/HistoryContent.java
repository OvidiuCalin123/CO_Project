package Screens.History;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HistoryContent extends BorderPane {

    private TableView<Person> tableView;

    public HistoryContent(StackPane root) {
        // Set padding and background color
        setPadding(new Insets(20));
        setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        // Create the table columns
        TableColumn<Person, String> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreCol.setResizable(false);

        TableColumn<Person, String> runTimeCol = new TableColumn<>("Run time");
        runTimeCol.setCellValueFactory(new PropertyValueFactory<>("run time"));
        runTimeCol.setResizable(false);

        TableColumn<Person, Integer> hdd_ssdCol = new TableColumn<>("HDD/SDD");
        hdd_ssdCol.setCellValueFactory(new PropertyValueFactory<>("hdd/sdd"));
        hdd_ssdCol.setResizable(false);

        TableColumn<Person, Integer> testTimeCol = new TableColumn<>("Test time");
        testTimeCol.setCellValueFactory(new PropertyValueFactory<>("test time"));
        testTimeCol.setResizable(false);

        hdd_ssdCol.setPrefWidth(200);

        // Create the table view and add the columns
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(scoreCol, runTimeCol, hdd_ssdCol, testTimeCol);
        tableView.setPrefHeight(400);

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
    }

    public void setData(ObservableList<Person> data) {
        // Set the data for the table view
        tableView.setItems(data);
    }
}

class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
