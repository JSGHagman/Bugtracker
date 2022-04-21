/**
 * Mainview Class
 * Used for some JavaFX stuff I guess idk.
 */

package View;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MainView {
    public MainView(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 700, Color.CORAL);

        Text text = new Text();
        text.setText("Tickets!");
        text.setX(225);
        text.setY(145);
        text.setFont(Font.font("Times-new-roman", 50));
        text.setFill(Color.GREEN);

        Button allTickets = new Button();
        allTickets.setText("All tickets");
        allTickets.setLayoutX(130);
        allTickets.setLayoutY(0);
        allTickets.setScaleX(1);
        allTickets.setScaleY(1);
        allTickets.setFont(Font.font("Times-new-Roman", 20));

        Button newTickets = new Button();
        newTickets.setText("New tickets");
        newTickets.setLayoutX(0);
        newTickets.setLayoutY(0);
        newTickets.setScaleX(1);
        newTickets.setScaleY(1);
        newTickets.setFont(Font.font("Times-new-Roman", 20));

        Button yourTickets = new Button();
        yourTickets.setText("Your tickets");
        yourTickets.setLayoutY(0);
        yourTickets.setLayoutX(245);
        yourTickets.setFont(Font.font("Times-new-Roman", 20));

        TextArea textArea = new TextArea();
        textArea.setLayoutX(50);
        textArea.setLayoutY(300);

        Button profile = new Button();
        profile.setText("Profile");
        profile.setLayoutY(0);
        profile.setLayoutX(510);
        profile.setFont(Font.font("Times-new-Roman", 20));

        Rectangle rectangle = new Rectangle();
        rectangle.setX(50);
        rectangle.setY(50);
        rectangle.setHeight(150);
        rectangle.setWidth(500);
        rectangle.setFill(Color.WHITE);
        rectangle.setStrokeWidth(5);
        rectangle.setStroke(Color.BLACK);

        Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
        stage.getIcons().add(icon);

        //Image image = new Image("https://media.istockphoto.com/vectors/slice-of-pepperoni-pizza-isolated-vector-illustration-vector-id1320470406?b=1&k=20&m=1320470406&s=170667a&w=0&h=MrBUVVZ-ZubzLWHaqPpjc0osiZvpagiWcZkU-s_GGc0=");
        //ImageView imageView = new ImageView(image);
        //imageView.setX(100);
        //imageView.setY(250);

        stage.setTitle("Bugtracker");
        stage.setResizable(false);
        stage.setX(50);
        stage.setY(50);
        //stage.setFullScreen(true);
        //stage.setFullScreenExitHint("Du trodde");
        //stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));

        root.getChildren().add(rectangle);
        root.getChildren().add(textArea);
        root.getChildren().add(text);
        root.getChildren().add(allTickets);
        root.getChildren().add(yourTickets);
        root.getChildren().add(newTickets);
        root.getChildren().add(profile);
        //root.getChildren().add(imageView);
        stage.setScene(scene);
        stage.show();
    }

}
