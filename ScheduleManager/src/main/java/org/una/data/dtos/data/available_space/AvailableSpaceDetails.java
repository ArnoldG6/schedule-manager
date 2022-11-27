package org.una.data.dtos.data.available_space;

import lombok.Data;

@Data
public class AvailableSpaceDetails {
    private Long id;
    private String initialHour;
    private String finalHour;
    private Long blockID;
    private String studentUniversityId;
    private String day;
}
