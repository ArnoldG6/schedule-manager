/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.una.data.dtos.data.block.BlockDetails;
import org.una.data.entities.Block;
import org.una.mappers.BlockMapper;
import org.una.data.repository.BlockRepository;

import java.util.List;
import java.util.Optional;

public final class BlockService {


    private BlockMapper blockMapper;
    @Autowired
    private BlockRepository blockRepository;


    public BlockService(){
        this.blockMapper = new BlockMapper();
    }

    public List<BlockDetails> findAll() {
        return blockMapper.blockDetailsFromBlockList(blockRepository.findAll());
    }


    public BlockDetails findById(Long id) throws Exception {
        Optional<Block> block = blockRepository.findById(id);
        if (!block.isPresent())
            throw new Exception(String.format("The Block with the id: %s not found!", id));
        return blockMapper.blockDetailsFromBlock(block.get());
    }

    public void deleteById(Long id) throws Exception {
        if (!blockRepository.findById(id).isPresent()) {
            throw new Exception(String.format("The Block with the id: %s not found!", id));
        } else {
            blockRepository.deleteById(id);
        }
    }
}
