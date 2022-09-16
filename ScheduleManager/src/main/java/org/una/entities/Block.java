package org.una.entities;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="t_block")
@EqualsAndHashCode(exclude = {"year","availableSpaces"})
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set <AvailableSpace> availableSpaces;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "year_id", nullable = false)
    private Year year;

    public Block(){
        id = 0L;
        name = "";
        year = null;
        availableSpaces = new HashSet<>();
    }

    public Block(Long id, String name, Year year, Set <AvailableSpace> availableSpaces){
        this.id = id;
        this.name = name;
        this.year = year;
        this.availableSpaces = availableSpaces;
    }

    public Block(String name, Year year, Set <AvailableSpace> availableSpaces){
        this.name = name;
        this.year = year;
        this.availableSpaces = availableSpaces;
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
    public void setAvailableSpaces(Set<AvailableSpace> availableSpaces) {
        this.availableSpaces = availableSpaces;
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
    public Set<AvailableSpace> getAvailableSpaces() {
        return availableSpaces;
    }

   /* @Override
    public String toString() { //Cambiar a StringBuilder

        return "Block{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", availableSpaces=" + availableSpaces +
                ", year=" + year.getYear() +
                '}';
    }*/

}