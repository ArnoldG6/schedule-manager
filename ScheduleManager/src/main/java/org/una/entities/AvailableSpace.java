package org.una.entities;

import java.util.Objects;

public class AvailableSpace {

    Long id;
    String initialHour;
    String finalHour;
    Student student;
    String day;

    AvailableSpace(){
        id = 0L;
        initialHour = "";
        finalHour = "";
        student = null;
        day = "";
    }

    AvailableSpace(Long id, String initialHour, String finalHour, Student student, String day){
        this.id = id;
        this.initialHour = initialHour;
        this.finalHour = finalHour;
        this.student = student;
        this.day = day;
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

    @Override
    public String toString() {
        return "AvailableSpace{" +
                "id=" + id +
                ", initialHour='" + initialHour + '\'' +
                ", finalHour='" + finalHour + '\'' +
                ", student=" + student +
                ", day='" + day + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableSpace that = (AvailableSpace) o;
        return id.equals(that.id) && initialHour.equals(that.initialHour) && finalHour.equals(that.finalHour) && student.equals(that.student) && day.equals(that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, initialHour, finalHour, student, day);
    }
}
