package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignUp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage.setTitle("Bugtracker Sign Up");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
