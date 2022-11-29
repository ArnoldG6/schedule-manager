package org.una.data.dtos.data.block;

import lombok.Data;


@Data
public class BlockDetails {
    private Long id;
    private String name;
    //private List<Long> availableSpacesIds;
    private Integer year;
}
