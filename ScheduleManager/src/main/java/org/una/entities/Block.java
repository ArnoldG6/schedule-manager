package org.una.entities;
import java.util.Set;

public class Block {

    Long id;
    String name;
    //falta Set <AvailableSpace>;
    Year year;

    Block(){
        id = 0L;
        name = "";
        //falta availableSpaces
        year = null;
    }

    public Block(Long id, String name, Year year){
        this.id = id;
        this.name = name;
        //falta availableSpaces
        this.year = year;
    }

    //SET-falta AvailableSpaces
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setYear(Year year) {
        this.year = year;
    }

    //GET-falta AvailableSpaces
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Year getYear() {
        return year;
    }

    //Falta método toString, método Equal y Hash
}