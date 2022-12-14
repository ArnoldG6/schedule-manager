/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 */
package org.una.application.spring;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.una.controllers.MainController;
import org.una.data.dtos.fxml.available_space.AvailableSpaceContainer;
import org.una.mappers.EntityMapper;
import org.una.services.AvailableSpaceService;
import org.una.services.BlockService;
import org.una.services.StudentService;
import org.una.services.YearService;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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
    public EntityMapper yearMapper(){return new EntityMapper();}
    @Bean
    public DataSource datasource() {
        String dbPath = getClass().getResource("/databases/schedule_manager.db").toString();
        return DataSourceBuilder.create()
                .driverClassName("org.sqlite.JDBC")
                .url("jdbc:sqlite::resource:"+dbPath)
                .username("admin")
                .password("admin")
                .build();
    }
}
