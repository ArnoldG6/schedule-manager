package org.una.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"blocks"})
@Entity
@Table(name="t_year")
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, unique=true)
    private Integer year;

    @OneToMany(mappedBy = "year", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Block> blocks;

    public Year(){
        id = 0L;
        year = 0;
        blocks = new HashSet<>();
    }
    public Year(Integer year, Set<Block> blocks){
        this.year = year;
        this.blocks = blocks;
    }
    public Year(Long id, Integer year, Set<Block> blocks){
        this.id = id;
        this.year = year;
        this.blocks = blocks;
    }

    //SET
    public void setId(Long id) {
        this.id = id;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public void setBlocks(Set<Block> blocks){
        this.blocks = blocks;
    }
    //GET
    public Long getId() {
        return id;
    }
    public Integer getYear() {
        return year;
    }
    public Set<Block> getBlocks(){
        return blocks;
    }


}
