package org.una.data.dtos.data.student;
import lombok.Data;
import org.una.data.dtos.data.available_space.AvailableSpaceDetails;

import java.util.Date;
import java.util.List;

@Data
public class StudentDetails {
    private Long id;
    private String universityId;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private String email;
    private Date entryDate;
    private List<AvailableSpaceDetails> availableSpaceDetailsList;
}
