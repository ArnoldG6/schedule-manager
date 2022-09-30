package org.una.data.entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="t_student")
@EqualsAndHashCode(exclude = {"availableSpaces"})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, name ="university_id", unique=true)
    private String universityId;
    @Column(nullable = false, name ="first_name")
    private String firstName;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false, name ="phone_number", unique=true)
    private String phoneNumber;
    @Column(nullable = false, unique=true)
    private String email;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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


    public Student(String universityId, String firstName, String surname, String phoneNumber, String email,
                   Set<AvailableSpace> availableSpaces){
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



}
