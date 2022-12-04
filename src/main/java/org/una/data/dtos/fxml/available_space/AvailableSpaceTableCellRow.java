/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.data.dtos.fxml.available_space;

import lombok.Data;

@Data
public class AvailableSpaceTableCellRow {
    String hour;
    Object d1;
    Object d2;
    Object d3;
    Object d4;
    Object d5;
    public AvailableSpaceTableCellRow(String hour){
        this.hour = hour;
    }
}
