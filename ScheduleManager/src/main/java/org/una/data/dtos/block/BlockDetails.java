package org.una.data.dtos.block;

import lombok.Data;
import org.una.data.dtos.year.YearDetails;
import org.una.data.entities.Year;

import java.util.List;


@Data
public class BlockDetails {
    private Long id;
    private String name;
    private List<Long> availableSpacesIds;
    private YearDetails year;
}
