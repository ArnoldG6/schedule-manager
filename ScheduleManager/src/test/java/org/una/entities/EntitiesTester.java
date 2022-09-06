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
        //Year year1 = new Year(0L,2022,null);
        //Year year2 = new Year(0L,2023,null);
        //Year year3 = new Year(0L,2024,null);
        //YearDAO.getInstance().add(year1);
        //YearDAO.getInstance().add(year2);
        //YearDAO.getInstance().add(year3);
        YearDAO yd = new YearDAO();
        System.out.println(yd.listAll());
    }
}
