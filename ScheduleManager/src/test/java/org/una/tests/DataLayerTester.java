package org.una.tests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.una.application.ScheduleManager;
import org.una.services.StudentService;
import org.una.services.YearService;


@SpringBootTest(classes= ScheduleManager.class)
@RunWith(SpringRunner.class)
public class DataLayerTester {

    @Autowired
    StudentService studentServiceImp;
    @Autowired
    YearService yearService;

    @Test
    public void testStudentServices(){

        try{
            System.out.println(studentServiceImp);
            System.out.println(studentServiceImp.findAll());
            System.out.println(yearService);
            System.out.println(yearService.findAll());
            
        }catch (Exception e){
            System.err.println(e);
        }

    }

}


