module com.example.bugtracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Controller to javafx.fxml;
    exports Controller;
    exports View;
    opens View to javafx.fxml;
}