package org.una.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ScheduleManager extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Starts MainView.fxml
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/presentation/views/MainView.fxml")));
        stage.setScene(new Scene(root));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}



