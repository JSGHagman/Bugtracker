module com.example.bugtracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens Controller to javafx.fxml;
    exports Controller;
    exports View;
    opens View to javafx.fxml;
}