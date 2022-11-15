package org.una.data.dtos.student;
import lombok.Data;

import java.util.List;

@Data
public class StudentDetails {
    private Long id;
    private String universityId;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private String email;
    private List<Long> availableSpacesIds;
}
