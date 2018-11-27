package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.company.DataFrame;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("My Gorgeous DataFrame Manager");
        primaryStage.setScene(new Scene(root, 850, 650));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            launch(args);
            Controller controller = new Controller();
        } catch (Exception e) {
            System.out.println("42");
        }


    }

}
