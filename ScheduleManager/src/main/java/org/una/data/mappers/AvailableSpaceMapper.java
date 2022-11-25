package org.una.data.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.una.data.dtos.data.available_space.AvailableSpaceDetails;
import org.una.data.dtos.data.available_space.AvailableSpaceInput;
import org.una.data.entities.AvailableSpace;
import org.una.data.entities.Block;
import org.una.data.entities.Student;
import org.una.data.repository.BlockRepository;
import org.una.data.repository.StudentRepository;
import org.una.services.BlockService;
import org.una.services.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AvailableSpaceMapper {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BlockRepository blockRepository;

    StudentMapper studentMapper;
    public AvailableSpaceMapper(){
        this.studentMapper = new StudentMapper();
    }

    public AvailableSpaceDetails availableSpaceDetailsFromAvailableSpace(AvailableSpace availableSpace) {
        if ( availableSpace == null ) {
            return null;
        }
        AvailableSpaceDetails availableSpaceDetails = new AvailableSpaceDetails();
        availableSpaceDetails.setId(availableSpaceDetails.getId());
        availableSpaceDetails.setStudent(studentMapper.studentDetailsFromStudent(availableSpace.getStudent()));
        availableSpaceDetails.setInitialHour(availableSpaceDetails.getInitialHour());
        availableSpaceDetails.setFinalHour(availableSpaceDetails.getFinalHour());
        availableSpaceDetails.setDay(availableSpaceDetails.getDay());
        availableSpaceDetails.setBlockID(availableSpaceDetails.getBlockID());
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

            availableSpace.setId(availableSpaceInput.getId());
            availableSpace.setBlock(block.get());
            availableSpace.setStudent(student.get());
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