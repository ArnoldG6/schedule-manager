package org.una.entities;

public class Year {

    Long id;
    int year;
                //falta arraylist Blocks

    Year(){
        id = 0L;
        year = 0;
                    //falta arraylist Blocks
    }
    Year(Long id, int year){
        this.id = id;
        this.year = year;
                            //falta arraylist Blocks
    }

    //SET-falta arraylist Blocks
    public void setId(Long id) {
        this.id = id;
    }
    public void setYear(int year) {
        this.year = year;
    }

    //GET-falta arraylist Blocks
    public Long getId() {
        return id;
    }
    public int getYear() {
        return year;
    }

    //Falta método toString, método Equal y Bash
}
