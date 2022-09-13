package org.una.entities;


import org.junit.jupiter.api.Test;
import org.una.dao.BlockDAO;
import org.una.dao.YearDAO;

import java.util.ArrayList;
import java.util.Objects;

public class EntityTester {

    @Test
    public void yearTest (){
        try{
            //====Adding years.
            Year year1 = new Year(2022,null);
            Year year2 = new Year(2023,null);
            Year year3 = new Year(2024,null);
            YearDAO.getInstance().add(year1);
            YearDAO.getInstance().add(year2);
            YearDAO.getInstance().add(year3);
            ArrayList<Year> years = YearDAO.getInstance().listAll();

            //====Deleting years.
            for (Year year: years) //This block deletes all the year items.
                YearDAO.getInstance().delete(year);
            assert(years.size() != 0); //Deletion failed if assertion happens.

            //====Adding repeated years.
            YearDAO.getInstance().add(year3); //adds year3 again
            Boolean repeated = false;

            try{
                YearDAO.getInstance().add(new Year(2024,null));
            }catch(Exception e){
                System.err.println(e);
                repeated = true;
            }
            assert (!repeated); //If entity is not repeated, test fails.
            //====Updating Year
            Integer originalYear = year3.getYear();
            System.err.printf("Original Year Object: %s.\n", year3);
            year3.setYear(2026);
            YearDAO.getInstance().update(year3);
            System.err.printf("Changed Year Object: %s.\n", year3);
            //Adding years again for next test.
            YearDAO.getInstance().add(new Year(2022,null));
            YearDAO.getInstance().add(new Year(2023,null));
            YearDAO.getInstance().add(new Year(2024,null));
        }catch (Exception e){
            System.err.println(e);
        }

    }

    @Test
    public void BlockTest(){
        ArrayList<Year> years = YearDAO.getInstance().listAll();

        Block b = new Block(1L,"Ciclo I",years.get(0),null);
        BlockDAO.getInstance().add(b);
        System.out.println(BlockDAO.getInstance().listAll());
        //(String name, Year year, Set <AvailableSpace> availableSpaces)
    }
}
