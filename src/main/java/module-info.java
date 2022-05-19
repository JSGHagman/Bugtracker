module com.example.bugtracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.jfree.jfreechart;
    requires google.api.services.drive.v3.rev197;
    requires com.google.api.client.auth;
    requires google.api.client;
    requires com.google.api.client;
    requires com.google.api.client.json.gson;
    requires org.apache.commons.io;
    opens Controller to javafx.fxml;
    exports Controller;
    exports View.LogInView;
    exports View.OldTicketGui;
    opens View.OldTicketGui to javafx.fxml;
    exports View.MainView.MainFrame;
}