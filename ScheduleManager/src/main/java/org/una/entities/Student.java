package org.una.entities;

import java.util.Objects;

public class Student {

    private Long id;
    private String universityId;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private String email;

    Student(){

        id = 0L;
        universityId = "";
        firstName = "";
        surname = "";
        phoneNumber = "";
        email = "";

    }

    public Student(Long id, String universityId, String firstName, String surname, String phoneNumber, String email){

        this.id = id;
        this.universityId = universityId;
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }

    //SET
    public void setId(Long id){
        this.id = id;
    }
    public void setUniversityId(String universityId){
        this.universityId = universityId;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email){
        this.email = email;
    }

    //GET
    public Long getId(){
        return this.id;
    }
    public String getUniversityId(){
        return this.universityId;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getSurname(){
        return this.surname;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String getEmail(){
        return this.email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", universityId='" + universityId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id) && universityId.equals(student.universityId) && firstName.equals(student.firstName) && surname.equals(student.surname) && phoneNumber.equals(student.phoneNumber) && email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, universityId, firstName, surname, phoneNumber, email);
    }
}
