package org.una.application;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.una.controllers.MainController;
import org.una.data.mappers.AvailableSpaceMapper;
import org.una.data.mappers.StudentMapper;
import org.una.services.AvailableSpaceService;
import org.una.services.BlockService;
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
    public StudentService studentService(){return new StudentService();}

    @Bean
    public BlockService blockService(){return new BlockService();}

    @Bean
    public AvailableSpaceService availableSpaceService(){return new AvailableSpaceService();}

    @Bean
    public AvailableSpaceMapper availableSpaceMapper(){return new AvailableSpaceMapper();}

    @Bean
    public StudentMapper studentMapper(){return new StudentMapper();}

}