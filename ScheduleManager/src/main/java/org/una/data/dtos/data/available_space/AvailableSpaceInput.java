package org.una.data.dtos.data.available_space;

import lombok.Data;

@Data
public class AvailableSpaceInput {
    private Long id;
    private String initialHour;
    private String finalHour;
    //private BlockDetails block;
    //private StudentDetails student;
    private String day;
}
