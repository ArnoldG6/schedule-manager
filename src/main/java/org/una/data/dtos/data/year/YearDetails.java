package org.una.data.dtos.data.year;
import lombok.Data;
import org.una.data.dtos.data.block.BlockDetails;

import java.util.List;


@Data
public class YearDetails {
    private Long id;
    private Integer year;
    private List<BlockDetails> blocks;
}
