package org.una.data.dtos.data.block;

import lombok.Data;
import org.una.data.dtos.data.year.YearDetails;

import java.util.List;


@Data
public class BlockDetails {
    private Long id;
    private String name;
    private List<Long> availableSpacesIds;
    private YearDetails year;
}
