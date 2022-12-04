/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.data.dtos.data.block;

import lombok.Data;
import org.una.data.dtos.data.available_space.AvailableSpaceDetails;

import java.util.List;

@Data
public class BlockFullDetails extends BlockDetails {
    private List<AvailableSpaceDetails> availableSpaces;
}
