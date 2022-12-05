/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.data.dtos.data.available_space;

import lombok.Data;

@Data
public class AvailableSpaceDetails {
    protected Long id;
    protected String initialHour;
    protected String finalHour;
    //SO: Block attributes. This must be changed into another Block-simplified-DTO.
    protected Long blockID;
    protected String blockName;
    protected Integer year;
    //EO: Block attributes.
    //SO: Student attributes.
    protected Long studentId;
    //EO: Student attributes
    protected String day;

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
