module CO.Project {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens Screens.History to javafx.base;
    opens Main;
}