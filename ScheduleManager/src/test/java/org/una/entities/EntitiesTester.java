package org.una.entities;


import org.junit.jupiter.api.Test;
import org.una.dao.YearDAO;

public class EntitiesTester {



    @Test
    public void generateDB() {
        // Dummy test.
        assert(1==1);
    }

    @Test
    public void entitiesGenerationTest (){
        Year year1 = new Year(0L,2022,null);
        Year year2 = new Year(0L,2023,null);
        Year year3 = new Year(0L,2024,null);
        YearDAO yd = new YearDAO();
        yd.add(year1);
        yd.add(year2);
        yd.add(year3);
        try{
            System.out.println(yd.listAll());
        }catch (Exception e){
            System.err.println(e);
        }

    }
}
