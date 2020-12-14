package kursach;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Сервер");
        primaryStage.setScene(new Scene(root, 960, 776));
        primaryStage.show();
        MainController.stage = primaryStage;
        new Thread(new Runnable() { public void run() { MultiThreadServer.main(); }}).start();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
