package org.una.application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.una.settings.UniversalSettings;

import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
@EnableJpaRepositories("org.una.data.repository")
@EntityScan("org.una.data.entities")
public class ScheduleManager extends Application {
    private ConfigurableApplicationContext applicationContext;
    private Parent rootNode;
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        stage.setScene(new Scene(rootNode));
        stage.setMinHeight(768);
        stage.setMinWidth(1024);
        stage.setTitle(UniversalSettings.APP_TITLE_ES.value);
        stage.getIcons().add(new Image(
                Objects.requireNonNull(getClass().getResourceAsStream("/presentation/views/images/schedule-manager-icon.png")))
        );
        stage.show();
    }
    public void init() throws IOException {
        applicationContext = SpringApplication.run(ScheduleManager.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/presentation/views/fxml/MainView.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        rootNode = fxmlLoader.load();
    }
    public void stop() throws Exception{
        applicationContext.close();
    }

}