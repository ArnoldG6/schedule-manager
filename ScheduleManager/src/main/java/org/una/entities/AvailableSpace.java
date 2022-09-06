package org.una.entities;
import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name="t_available_space")
public class AvailableSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String initialHour;
    @Column(nullable = false)
    private String finalHour;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "block_id", nullable = false)
    private Block block;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @Column(nullable = false)
    private String day;

    public AvailableSpace(){
        id = 0L;
        initialHour = "";
        finalHour = "";
        student = null;
        day = "";
    }

    public AvailableSpace(Long id, String initialHour, String finalHour, Student student, String day, Block block){
        this.id = id;
        this.initialHour = initialHour;
        this.finalHour = finalHour;
        this.student = student;
        this.day = day;
        this.block = block;
    }

    //SET
    public void setId(Long id) {
        this.id = id;
    }
    public void setInitialHour(String initialHour) {
        this.initialHour = initialHour;
    }
    public void setFinalHour(String finalHour) {
        this.finalHour = finalHour;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public void setBlock(Block block) {
        this.block = block;
    }
    //GET
    public Long getId() {
        return id;
    }
    public String getInitialHour() {
        return initialHour;
    }
    public String getFinalHour() {
        return finalHour;
    }
    public Student getStudent() {
        return student;
    }
    public String getDay() {
        return day;
    }

    public Block getBlock() {
        return block;
    }


    @Override
    public String toString() { //Cambiar a StringBuilder
        return "AvailableSpace{" +
                "id=" + id +
                ", initialHour='" + initialHour + '\'' +
                ", finalHour='" + finalHour + '\'' +
                ", block=" + block +
                ", student=" + student +
                ", day='" + day + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvailableSpace)) return false;
        AvailableSpace that = (AvailableSpace) o;
        return Objects.equals(id, that.id) && Objects.equals(initialHour, that.initialHour) && Objects.equals(finalHour, that.finalHour) && Objects.equals(block, that.block) && Objects.equals(student, that.student) && Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, initialHour, finalHour, block, student, day);
    }
}
