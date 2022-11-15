package org.una.data.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="t_student")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date entryDate;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AvailableSpace> availableSpaces;
}
