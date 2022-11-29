package org.una.data.dtos.data.available_space;

import lombok.Data;

@Data
public class AvailableSpaceDetails {
    private Long id;
    private String initialHour;
    private String finalHour;
    //SO: Block attributes.
    private Long blockID;
    private String blockName;
    private Integer year;
    //EO: Block attributes.
    private String studentUniversityId;
    private String day;

    public String listViewToString(){
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("-");
        sb.append(year).append("-");
        sb.append(blockName).append("-");
        sb.append(day).append("-");
        sb.append(initialHour).append("-");
        sb.append(finalHour);
        return sb.toString();
    }
}
