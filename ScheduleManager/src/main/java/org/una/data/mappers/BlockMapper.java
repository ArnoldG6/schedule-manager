package org.una.data.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.una.data.dtos.block.BlockDetails;
import org.una.data.entities.Block;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlockMapper {
    BlockMapper MAPPER = Mappers.getMapper(BlockMapper.class);
    public BlockDetails blockDetailsFromBlock(Block block);
    public List<BlockDetails> blockDetailsFromBlockList(List<Block> block);
    public Block blockFromBlockDetails(BlockDetails blockDetails);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void blockFromBlockDetails(BlockDetails dto, @MappingTarget Block block);
}
