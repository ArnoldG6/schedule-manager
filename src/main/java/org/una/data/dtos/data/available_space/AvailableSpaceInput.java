/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
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
