package org.una.entities;
import java.util.HashSet;
import java.util.Set;

public class Year {

    private Long id;
    private int year;

    private Set<Block> blocks;

    public Year(){
        id = 0L;
        year = 0;
        blocks = new HashSet<>();
    }
    public Year(Long id, int year){
        this.id = id;
        this.year = year;
        blocks = new HashSet<>();
    }

    //SET
    public void setId(Long id) {
        this.id = id;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setBlocks(Set<Block> blocks){
        this.blocks = new HashSet<>(blocks);
    }
    //GET
    public Long getId() {
        return id;
    }
    public int getYear() {
        return year;
    }
    public Set<Block> getBlocks(){
        return blocks;
    }
}
