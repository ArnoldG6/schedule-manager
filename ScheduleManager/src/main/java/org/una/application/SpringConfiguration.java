package org.una.application;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.una.controllers.MainController;
import org.una.data.entities.Year;
import org.una.services.StudentService;
import org.una.services.YearService;

@Configuration
public class SpringConfiguration {
    @Bean
    public MainController mainController(){
        return new MainController();
    }



    @Bean
    public YearService yearService(){return new YearService();}
    @Bean
    public StudentService studentServiceImp(){return new StudentService();}


}
