package org.una.data.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.una.data.dtos.block.BlockDetails;
import org.una.data.entities.Block;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BlockMapper {
    BlockMapper MAPPER = Mappers.getMapper(BlockMapper.class);
    public BlockDetails priorityDetailsFromPriority(Block priority);
    public List<BlockDetails> priorityDetailsFromPriorityList(List<Block> priority);
    public Block priorityFromPriorityDetails(BlockDetails priorityDetails);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void priorityFromPriorityDetails(BlockDetails dto, @MappingTarget Block priority);
}
