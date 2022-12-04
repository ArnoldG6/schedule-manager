/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.mappers;


import org.una.data.dtos.data.block.BlockDetails;
import org.una.data.dtos.data.year.YearDetails;
import org.una.data.entities.Block;
import org.una.data.entities.Year;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class YearMapper{

    private BlockMapper blockMapper;
    public YearMapper(){
        this.blockMapper = new BlockMapper();
    }
    public YearDetails yearDetailsFromYear(Year year) {
        if ( year == null ) {
            return null;
        }

        YearDetails yearDetails = new YearDetails();
        yearDetails.setYear(year.getYear());
        yearDetails.setId(year.getId());
        yearDetails.setBlocks(new ArrayList<>());
        for(Block block: year.getBlocks())
            yearDetails.getBlocks().add(blockMapper.blockDetailsFromBlock(block));
        yearDetails.getBlocks().sort(new Comparator<BlockDetails>(){
            public int compare(BlockDetails bd1, BlockDetails bd2)
            {
                return bd1.getName().compareTo(bd2.getName());  // it can also return 0, and 1
            }
        });
        return yearDetails;
    }

    public List<YearDetails> yearDetailsFromYearList(List<Year> year) {
        if ( year == null ) {
            return null;
        }

        List<YearDetails> list = new ArrayList<YearDetails>( year.size() );
        for ( Year year1 : year ) {
            list.add( yearDetailsFromYear( year1 ) );
        }

        return list;
    }

    public Year yearFromYearDetails(YearDetails yearDetails) {
        if ( yearDetails == null ) {
            return null;
        }

        Year year = new Year();

        return year;
    }

}