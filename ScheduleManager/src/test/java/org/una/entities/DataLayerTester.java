package org.una.entities;


import org.junit.jupiter.api.Test;
import org.una.dao.AvailableSpaceDAO;
import org.una.dao.BlockDAO;
import org.una.dao.StudentDAO;
import org.una.dao.YearDAO;
import org.una.schemagenerator.SchemaGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DataLayerTester {

    @Test
    public void dbGenerationTest(){
        SchemaGenerator.main(null);
    }

    @Test
    public void yearTest (){
        try{
            ArrayList<Year> years = YearDAO.getInstance().listAll();
            //====Adding years.
            Year year1 = new Year(2022,null);
            Year year2 = new Year(2023,null);
            Year year3 = new Year(2024,null);
            years.add(year1);
            years.add(year2);
            years.add(year3);
            YearDAO.getInstance().add(year1);
            YearDAO.getInstance().add(year2);
            YearDAO.getInstance().add(year3);

            //====Deleting years.
            for (Year year: years) //This block deletes all the year items.
                YearDAO.getInstance().delete(year);
            assert(years.size() != 0); //Deletion failed if assertion happens.

            //====Adding repeated years.
            YearDAO.getInstance().add(year3); //adds year3 again
            boolean repeated = false;

            try{
                YearDAO.getInstance().add(new Year(2024,null));
            }catch(Exception e){
                System.err.println(e);
                repeated = true;
            }
            assert (!repeated); //If entity is not repeated, test fails.
            //====Updating Year
            Integer originalYear = year3.getYear();
            System.out.printf("Original Year Object: %s.\n", year3);
            year3.setYear(2026);
            YearDAO.getInstance().update(year3);
            System.out.printf("Changed Year Object: %s.\n", year3);
            //Adding years again for next test.
            YearDAO.getInstance().add(new Year(2022,null));
            YearDAO.getInstance().add(new Year(2023,null));
            YearDAO.getInstance().add(new Year(2024,null));
            System.out.println(YearDAO.getInstance().listAll());

        }catch (Exception e){
            System.err.println(e);
        }

    }

    @Test
    public void BlockTest(){
        ArrayList<Year> years = YearDAO.getInstance().listAll();
        Block b = new Block(1L,"Ciclo I",years.get(0),null);
        BlockDAO.getInstance().add(b);
    }

    @Test
    public void AvailableSpaceTest(){


        ArrayList<Block> blockArrayList = BlockDAO.getInstance().listAll();
        Block sampleBlock = blockArrayList.get(0); //First element of arraylist.
        Student s = new Student("117620480","Arnold","González",
                "6341003","arnoldgq612@gmail.com",null);
        //public AvailableSpace(String initialHour, String finalHour, Student student, String day, Block block){
        AvailableSpace space = new AvailableSpace("08:00","12:00",s,"L",sampleBlock);
        Set<AvailableSpace> availableSpaces = new HashSet<AvailableSpace>();
        availableSpaces.add(space);
        s.setAvailableSpaces(availableSpaces);
        //Add the records in the DB.
        StudentDAO.getInstance().add(s);
        AvailableSpaceDAO.getInstance().add(space);


        //Prints all the records
        System.out.println(YearDAO.getInstance().listAll());
        System.out.println(blockArrayList);
        System.out.println(StudentDAO.getInstance().listAll());
        System.out.println(AvailableSpaceDAO.getInstance().listAll());
    }
    @Test
    public void yearTest2 () {
        try {
            System.out.println(YearDAO.getInstance().searchEntitiesByField("year",2022));
            System.out.println(YearDAO.getInstance().searchEntityByField("year",2022));
        }catch (Exception e){
            System.err.println(e);
        }

    }

}
