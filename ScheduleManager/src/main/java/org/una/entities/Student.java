package org.una.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String universityId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AvailableSpace> availableSpaces;

    public Student(){

        id = 0L;
        universityId = "";
        firstName = "";
        surname = "";
        phoneNumber = "";
        email = "";
        availableSpaces = new HashSet<>();

    }

    public Student(Long id, String universityId, String firstName, String surname, String phoneNumber, String email,
                   Set<AvailableSpace> availableSpaces){

        this.id = id;
        this.universityId = universityId;
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.availableSpaces=availableSpaces;
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
    public void setAvailableSpaces(Set<AvailableSpace> availableSpaces){
        this.availableSpaces=availableSpaces;
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

    public Set<AvailableSpace> getAvailableSpaces() {
        return availableSpaces;
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
                ", availableSpaces=" + availableSpaces +
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
