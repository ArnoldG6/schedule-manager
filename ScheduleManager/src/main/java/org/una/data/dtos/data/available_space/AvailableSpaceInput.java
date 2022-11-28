package org.una.data.dtos.data.available_space;

import lombok.Data;

@Data
public class AvailableSpaceInput {
    private Long id;
    private String initialHour;
    private String finalHour;
    private Long blockID;
    private String blockName;
    private Integer year;
    private Long studentID;
    private String day;
}
