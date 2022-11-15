package org.una.data.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.una.data.dtos.block.BlockDetails;
import org.una.data.entities.Block;

import java.util.List;

@Mapper
public interface BlockMapper {
    BlockMapper MAPPER = Mappers.getMapper(BlockMapper.class);
    public BlockDetails priorityDetailsFromPriority(Block priority);
    public List<BlockDetails> priorityDetailsFromPriorityList(List<Block> priority);
    public Block priorityFromPriorityDetails(BlockDetails priorityDetails);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void priorityFromPriorityDetails(BlockDetails dto, @MappingTarget Block priority);
}
