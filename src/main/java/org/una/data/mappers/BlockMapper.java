/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.data.mappers;

import org.una.data.dtos.data.block.BlockDetails;
import org.una.data.entities.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockMapper{
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
        for ( Block block1 : block ) {
            list.add( blockDetailsFromBlock( block1 ) );
        }
        return list;
    }

}
