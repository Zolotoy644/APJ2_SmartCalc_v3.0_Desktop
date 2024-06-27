package edu.school21.smartcalc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SmartCalc extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SmartCalc.class.getResource("calc-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 406, 393);
        stage.setTitle("SmartCalc");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}