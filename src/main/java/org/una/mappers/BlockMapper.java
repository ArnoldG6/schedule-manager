/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.una.data.dtos.data.available_space.AvailableSpaceDetails;
import org.una.data.dtos.data.block.BlockDetails;
import org.una.data.dtos.data.block.BlockFullDetails;
import org.una.data.entities.AvailableSpace;
import org.una.data.entities.Block;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlockMapper{

    @Autowired
    private AvailableSpaceMapper availableSpaceMapper;
    public BlockDetails blockDetailsFromBlock(Block block) {
        if ( block == null )
            return null;
        BlockDetails blockDetails = new BlockDetails();
        blockDetails.setYear(block.getYear().getYear());
        blockDetails.setName(block.getName());
        blockDetails.setId(block.getId());
        return blockDetails;
    }

    public List<BlockDetails> blockDetailsFromBlockList(List<Block> block) {
        if ( block == null )
            return null;
        List<BlockDetails> list = new ArrayList<BlockDetails>( block.size() );
        for ( Block block1 : block )
            list.add( blockDetailsFromBlock( block1 ) );

        return list;
    }

    public BlockFullDetails blockFullDetailsFromBlock(Block block){
        if ( block == null ) return null;
        BlockFullDetails blockFullDetails = new BlockFullDetails();
        blockFullDetails.setYear(block.getYear().getYear());
        blockFullDetails.setName(block.getName());
        blockFullDetails.setId(block.getId());
        if(block.getAvailableSpaces() != null) {
            blockFullDetails.setAvailableSpaces(new ArrayList<>(block.getAvailableSpaces().size()));
            for (AvailableSpace availableSpace : block.getAvailableSpaces()) {
                System.out.println(availableSpaceMapper);
                blockFullDetails.getAvailableSpaces().add(availableSpaceMapper.availableSpaceDetailsFromAvailableSpace(
                        availableSpace
                ));
            }
        }
        return blockFullDetails;

    }

    public List<BlockFullDetails> blockFullDetailsListFromBlockList(List<Block> block){
        if ( block == null )
            return null;
        List<BlockFullDetails> list = new ArrayList<>(block.size());
        for ( Block block1 : block )
            list.add( blockFullDetailsFromBlock( block1 ) );
        return list;
    }
}
