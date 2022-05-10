module com.example.bugtracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens Controller to javafx.fxml;
    exports Controller;
    exports View.LogInView;
    exports View.OldTicketGui;
    opens View.OldTicketGui to javafx.fxml;
    exports View.MainView.MainFrame;
}