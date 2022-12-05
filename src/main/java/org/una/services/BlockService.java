/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.una.data.dtos.data.block.BlockDetails;
import org.una.data.dtos.data.block.BlockFullDetails;
import org.una.data.dtos.data.block.BlockInput;
import org.una.data.entities.Block;
import org.una.data.repository.BlockRepository;
import org.una.mappers.EntityMapper;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

public final class BlockService {

    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private BlockRepository blockRepository;


    public List<BlockDetails> findAll() {
        return entityMapper.blockDetailsFromBlockList(blockRepository.findAll());
    }

    public BlockFullDetails findBlockFullDetailsById(BlockInput blockInput) throws Exception {
        Optional<Block> block = blockRepository.findById(blockInput.getId());
        if (!block.isPresent())
            throw new Exception(String.format("The Block with the id: %s not found!", blockInput.getId()));
        return entityMapper.blockFullDetailsFromBlock(block.get());
    }

    public BlockDetails findById(Long id) throws Exception {
        Optional<Block> block = blockRepository.findById(id);
        if (!block.isPresent())
            throw new Exception(String.format("The Block with the id: %s not found!", id));
        return entityMapper.blockDetailsFromBlock(block.get());
    }

    public void deleteById(Long id) throws Exception {
        if (!blockRepository.findById(id).isPresent()) {
            throw new Exception(String.format("The Block with the id: %s not found!", id));
        } else {
            blockRepository.deleteById(id);
        }
    }
}
