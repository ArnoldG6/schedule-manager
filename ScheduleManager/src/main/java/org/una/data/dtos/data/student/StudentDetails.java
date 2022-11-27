package org.una.data.dtos.data.student;
import lombok.Data;
import org.una.data.dtos.data.available_space.AvailableSpaceDetails;

import java.util.Date;
import java.util.List;

@Data
public class StudentDetails {
    protected Long id;
    protected String universityId;
    protected String firstName;
    protected String surname;
    protected String phoneNumber;
    protected String email;
    protected Date entryDate;
    protected List<AvailableSpaceDetails> availableSpaceDetailsList;
}
