/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.mappers;


import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.una.data.dtos.data.available_space.AvailableSpaceDetails;
import org.una.data.dtos.data.available_space.AvailableSpaceInput;
import org.una.data.dtos.data.block.BlockDetails;
import org.una.data.dtos.fxml.available_space.AvailableSpaceContainer;
import org.una.data.dtos.fxml.available_space.BlockFullDetails;
import org.una.data.dtos.data.student.StudentDetails;
import org.una.data.dtos.data.student.StudentInput;
import org.una.data.dtos.data.year.YearDetails;
import org.una.data.dtos.fxml.student.UpdateStudentInput;
import org.una.data.entities.AvailableSpace;
import org.una.data.entities.Block;
import org.una.data.entities.Student;
import org.una.data.entities.Year;
import org.una.data.repository.BlockRepository;
import org.una.data.repository.StudentRepository;

import java.util.*;


@Component
public class EntityMapper {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BlockRepository blockRepository;


    public AvailableSpaceContainer availableSpaceStackPaneFromAvailableSpace(AvailableSpace availableSpace){
        if ( availableSpace == null )
            return null;
        try{
            return new AvailableSpaceContainer(availableSpace);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<AvailableSpaceContainer> availableSpaceStackPaneListFromAvailableSpaceList(
            List<AvailableSpace> availableSpaces){
        if ( availableSpaces == null )
            return null;
        List<AvailableSpaceContainer> list = new ArrayList<>(availableSpaces.size());
        for (AvailableSpace availableSpace : availableSpaces)
            list.add(availableSpaceStackPaneFromAvailableSpace(availableSpace));
        return list;
    }
    public AvailableSpaceDetails availableSpaceDetailsFromAvailableSpace(AvailableSpace availableSpace) {
        if ( availableSpace == null ) {
            return null;
        }
        AvailableSpaceDetails availableSpaceDetails = new AvailableSpaceDetails();
        availableSpaceDetails.setId(availableSpace.getId());
        if(availableSpace.getStudent() != null){
            availableSpaceDetails.setStudentId(availableSpace.getStudent().getId());
        }

        availableSpaceDetails.setInitialHour(availableSpace.getInitialHour());
        availableSpaceDetails.setFinalHour(availableSpace.getFinalHour());
        availableSpaceDetails.setDay(availableSpace.getDay());
        if(availableSpace.getBlock() != null&& availableSpace.getBlock().getYear()!=null)
            availableSpaceDetails.setYear(availableSpace.getBlock().getYear().getYear());
        if(availableSpace.getBlock() != null)
            availableSpaceDetails.setBlockName(availableSpace.getBlock().getName());
        availableSpaceDetails.setBlockID(availableSpace.getBlock().getId());
        return availableSpaceDetails;
    }

    public List<AvailableSpaceDetails> availableSpaceDetailsFromAvailableSpaceList(List<AvailableSpace> availableSpace) {
        if ( availableSpace == null ) {
            return null;
        }

        List<AvailableSpaceDetails> list = new ArrayList<>(availableSpace.size());
        for ( AvailableSpace availableSpace1 : availableSpace ) {
            list.add( availableSpaceDetailsFromAvailableSpace( availableSpace1 ) );
        }

        return list;
    }



    public AvailableSpace availableSpaceFromAvailableSpaceInput(AvailableSpaceInput availableSpaceInput) {
        try{
            System.out.println(availableSpaceInput);
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
            ArrayList<AvailableSpaceContainer> availableSpaceContainers = new ArrayList<>(block.getAvailableSpaces().size());
            for (AvailableSpace availableSpace : block.getAvailableSpaces())
                availableSpaceContainers.add(availableSpaceStackPaneFromAvailableSpace(availableSpace));
            blockFullDetails.setAvailableSpaceContainerList(availableSpaceContainers);
        }
        return blockFullDetails;

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
            yearDetails.getBlocks().add(blockDetailsFromBlock(block));
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

    public StudentDetails studentDetailsFromStudent(Student student) {
        StudentDetails studentDetails = new StudentDetails();
        studentDetails.setId(student.getId());
        studentDetails.setUniversityId(student.getUniversityId());
        studentDetails.setFirstName(student.getFirstName());
        studentDetails.setSurname(student.getSurname());
        studentDetails.setPhoneNumber(student.getPhoneNumber());
        studentDetails.setEmail(student.getEmail());
        studentDetails.setEntryDate(student.getEntryDate());
        studentDetails.setAvailableSpaceDetailsList(new ArrayList<>());
        if(student.getAvailableSpaces()!=null && student.getAvailableSpaces().size() >0)
            for (AvailableSpace availableSpace: student.getAvailableSpaces())
                studentDetails.getAvailableSpaceDetailsList().add(availableSpaceDetailsFromAvailableSpace(availableSpace));
        return studentDetails;
    }
    /*
    =======================SO FXML-required mappings=======================
    */

    public UpdateStudentInput updateStudentInputFromStudentDetails(Student student) {
        StudentDetails studentDetails = studentDetailsFromStudent(student);
        UpdateStudentInput updateStudentInput = new UpdateStudentInput();
        updateStudentInput.setId(studentDetails.getId());
        updateStudentInput.setUniversityId(studentDetails.getUniversityId());
        updateStudentInput.setFirstName(studentDetails.getFirstName());
        updateStudentInput.setSurname(studentDetails.getSurname());
        updateStudentInput.setPhoneNumber(studentDetails.getPhoneNumber());
        updateStudentInput.setEmail(studentDetails.getEmail());
        updateStudentInput.setEntryDate(studentDetails.getEntryDate());
        updateStudentInput.setAvailableSpaceDetailsList(studentDetails.getAvailableSpaceDetailsList());
        updateStudentInput.setEditButton(new Button("Ver más"));
        updateStudentInput.setDeleteButton(new Button("Eliminar"));
        return updateStudentInput;
    }

    public Student studentFromUpdateStudentInput(UpdateStudentInput updateStudentInput){
        Student student = new Student();
        student.setId(updateStudentInput.getId());
        student.setUniversityId(updateStudentInput.getUniversityId());
        student.setFirstName(updateStudentInput.getFirstName());
        student.setSurname(updateStudentInput.getSurname());
        student.setPhoneNumber(updateStudentInput.getPhoneNumber());
        student.setEmail(updateStudentInput.getEmail());
        student.setEntryDate(updateStudentInput.getEntryDate());
        return student;
    }

    public List<UpdateStudentInput> updateStudentInputListFromStudentList(List<Student> student) {
        if ( student == null )
            return null;

        List<UpdateStudentInput> list = new ArrayList<UpdateStudentInput>( student.size() );
        for ( Student student1 : student ) {
            list.add( updateStudentInputFromStudentDetails( student1 ) );
        }

        return list;
    }


    /*
    =======================EO FXML-required mappings=======================
    */

    public List<StudentDetails> studentDetailsFromStudentList(List<Student> student) {
        if ( student == null ) {
            return null;
        }
        List<StudentDetails> list = new ArrayList<StudentDetails>( student.size() );
        for ( Student student1 : student ) {
            list.add( studentDetailsFromStudent( student1 ) );
        }

        return list;
    }

    public Student studentFromStudentInput(StudentInput studentInput) {
        if ( studentInput == null ) {
            return null;
        }

        Student student = new Student();

        student.setUniversityId( studentInput.getUniversityId() );
        student.setFirstName( studentInput.getFirstName() );
        student.setSurname( studentInput.getSurname() );
        student.setPhoneNumber( studentInput.getPhoneNumber() );
        student.setEmail( studentInput.getEmail() );
        student.setEntryDate( studentInput.getEntryDate() );

        return student;
    }





}