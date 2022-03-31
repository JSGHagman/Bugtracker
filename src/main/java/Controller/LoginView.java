package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//en klass för java Applikation /Login GUI
public class LoginView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        stage.setTitle("Bugtracker Sign Up");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
