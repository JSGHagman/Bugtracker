package Controller;
import View.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import View.MainView;

public class Main extends Application {

    public static void main(String[] args) {
        new Controller();
        launch(args);
    }

    /**
     * This method creates and shows the frame for creating a user.
     * It retrieves the fxml file from "resources" through the getResource-method.
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage.setTitle("Bugtracker Sign Up");
        Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
