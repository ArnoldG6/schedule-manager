/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.data.dtos.fxml.available_space;

import lombok.Data;
import org.una.data.dtos.data.block.BlockDetails;

import java.util.List;

@Data
public class BlockFullDetails extends BlockDetails {
    private List<AvailableSpaceStackPane> availableSpaceStackPaneList;
}
