package org.una.tests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.una.application.ScheduleManager;
import org.una.data.entities.Block;
import org.una.data.entities.Student;
import org.una.data.entities.Year;
import org.una.data.repository.AvailableSpaceRepository;
import org.una.data.repository.BlockRepository;
import org.una.data.repository.StudentRepository;
import org.una.data.repository.YearRepository;
import org.una.settings.UniversalSettings;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;


@SpringBootTest(classes= ScheduleManager.class)
@RunWith(SpringRunner.class)
public class DataLayerTester {
    @Autowired
    YearRepository yearRepository;
    @Autowired
    BlockRepository blockRepository;
    @Test
    public void initData(){

        try{

            //yearRepository.deleteAll();
            //studentRepository.deleteAll();
            ArrayList<Year> years = new ArrayList<>();
            Year year;
            HashSet<Block> yearBlocks;
            Block b1;
            Block b2;
            Block b3;
            for(int i = 2022; i<2122; i++){
                yearBlocks = new HashSet<>();
                year = new Year(null,i,null);
                b1 = new Block(null, UniversalSettings.BLOCK_1_ES.value, null,year);
                b2 = new Block(null, UniversalSettings.BLOCK_2_ES.value, null,year);
                b3 = new Block(null, UniversalSettings.BLOCK_3_ES.value, null,year);
                yearBlocks.add(b1);
                yearBlocks.add(b2);
                yearBlocks.add(b3);
                blockRepository.saveAllAndFlush(yearBlocks);
                year.setBlocks(yearBlocks);
                yearRepository.saveAndFlush(year);
            }
            System.out.println(yearRepository.findAll());
            //Block b4 = blockRepository.findByYearAndName(2022, "CICLO III");

            //Student arnold = new Student(null,"117620480","Arnoldo","González Quesada","6341003",
            //      "arnoldgq612@gmail.com",new Date(),null);
            //studentRepository.saveAndFlush(arnold);
        }catch (Exception e){
            System.err.println(e);
        }

    }

}


