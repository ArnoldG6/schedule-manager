package org.una.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.una.data.dtos.data.block.BlockDetails;
import org.una.data.entities.Block;
import org.una.data.mappers.BlockMapper;
import org.una.data.repository.BlockRepository;

import java.util.List;
import java.util.Optional;

public final class BlockService {

    @Autowired
    BlockRepository blockRepository;

    public List<BlockDetails> findAll() {
        return BlockMapper.MAPPER.blockDetailsFromBlockList(blockRepository.findAll());
    }




    public BlockDetails findById(Long id) throws Exception {
        Optional<Block> block = blockRepository.findById(id);
        if (!block.isPresent()) {
            throw new Exception(String.format("The Block with the id: %s not found!", id));
        }
        return BlockMapper.MAPPER.blockDetailsFromBlock(block.get());
    }


    public BlockDetails create(BlockDetails blockDetails) {
        Block block = BlockMapper.MAPPER.blockFromBlockDetails(blockDetails);
        return BlockMapper.MAPPER.blockDetailsFromBlock(blockRepository.saveAndFlush(block));
    }


    public BlockDetails update(BlockDetails blockDetails) throws Exception {
        Optional<Block> block = blockRepository.findById(blockDetails.getId());
        if (!block.isPresent()) {
            throw new Exception(String.format("The Block with the id: %s not found!", blockDetails.getId()));
        }
        Block blockUpdated = block.get();
        BlockMapper.MAPPER.blockFromBlockDetails(blockDetails, blockUpdated);
        return BlockMapper.MAPPER.blockDetailsFromBlock(blockRepository.save(blockUpdated));
    }



    public void deleteById(Long id) throws Exception {
        if (!blockRepository.findById(id).isPresent()) {
            throw new Exception(String.format("The Block with the id: %s not found!", id));
        } else {
            blockRepository.deleteById(id);
        }
    }
}
