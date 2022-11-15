package org.una.data.dtos.year;
import lombok.Data;
import org.una.data.dtos.block.BlockDetails;

import java.util.List;


@Data
public class YearDetails {
    private Long id;
    private Integer year;
    private List<BlockDetails> blocks;
}
