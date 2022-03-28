module com.example.bugtracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bugtracker to javafx.fxml;
    exports com.example.bugtracker;
}