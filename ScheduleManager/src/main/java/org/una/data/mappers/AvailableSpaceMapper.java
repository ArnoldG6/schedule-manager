package org.una.data.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.una.data.dtos.data.available_space.AvailableSpaceDetails;
import org.una.data.dtos.data.available_space.AvailableSpaceInput;
import org.una.data.entities.AvailableSpace;
import org.una.data.entities.Block;
import org.una.data.entities.Student;
import org.una.data.repository.BlockRepository;
import org.una.data.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AvailableSpaceMapper {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BlockRepository blockRepository;


    public AvailableSpaceDetails availableSpaceDetailsFromAvailableSpace(AvailableSpace availableSpace) {
        if ( availableSpace == null ) {
            return null;
        }
        AvailableSpaceDetails availableSpaceDetails = new AvailableSpaceDetails();
        availableSpaceDetails.setId(availableSpace.getId());
        if(availableSpace.getStudent() != null)
            availableSpaceDetails.setStudentUniversityId(availableSpace.getStudent().getUniversityId());
        availableSpaceDetails.setInitialHour(availableSpace.getInitialHour());
        availableSpaceDetails.setFinalHour(availableSpace.getFinalHour());
        availableSpaceDetails.setDay(availableSpace.getDay());
        availableSpaceDetails.setBlockID(availableSpace.getBlock().getId());
        return availableSpaceDetails;
    }

    public List<AvailableSpaceDetails> availableSpaceDetailsFromAvailableSpaceList(List<AvailableSpace> availableSpace) {
        if ( availableSpace == null ) {
            return null;
        }

        List<AvailableSpaceDetails> list = new ArrayList<AvailableSpaceDetails>( availableSpace.size() );
        for ( AvailableSpace availableSpace1 : availableSpace ) {
            list.add( availableSpaceDetailsFromAvailableSpace( availableSpace1 ) );
        }

        return list;
    }



    public AvailableSpace availableSpaceFromAvailableSpaceInput(AvailableSpaceInput availableSpaceInput) {
        try{
            if ( availableSpaceInput == null )
                return null;
            AvailableSpace availableSpace = new AvailableSpace();

            Optional<Block> block = blockRepository.findById(availableSpaceInput.getBlockID());
            if (!block.isPresent())
                throw new Exception(String.format("The Block with the id: %s not found!", availableSpaceInput.getBlockID()));

            Optional<Student> student = studentRepository.findById(availableSpaceInput.getStudentID());
            if (!student.isPresent())
                throw new Exception(String.format("The Student with the id: %s not found!", availableSpaceInput.getStudentID()));

            availableSpace.setBlock(block.get());
            availableSpace.setStudent(student.get());

            availableSpace.setId(availableSpaceInput.getId());
            availableSpace.setDay(availableSpaceInput.getDay());
            availableSpace.setFinalHour(availableSpaceInput.getFinalHour());
            availableSpace.setInitialHour(availableSpaceInput.getInitialHour());
            return availableSpace;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}