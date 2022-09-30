package org.una.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScheduleManager extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Starts MainView.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(ScheduleManager.class.getResource("/presentation/views/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}



