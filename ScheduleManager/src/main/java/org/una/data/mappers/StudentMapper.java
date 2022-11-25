package org.una.data.mappers;


import javafx.scene.control.Button;
import org.una.data.dtos.data.student.StudentDetails;
import org.una.data.dtos.data.student.StudentInput;
import org.una.data.dtos.fxml.UpdateStudentInput;
import org.una.data.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {
    public StudentDetails studentDetailsFromStudent(Student student) {
        StudentDetails studentDetails = new StudentDetails();
        studentDetails.setId(student.getId());
        studentDetails.setUniversityId(student.getUniversityId());
        studentDetails.setFirstName(student.getFirstName());
        studentDetails.setSurname(student.getSurname());
        studentDetails.setPhoneNumber(student.getPhoneNumber());
        studentDetails.setEmail(student.getEmail());
        studentDetails.setEntryDate(student.getEntryDate());
        //studentDetails.setAvailableSpacesIds(new ArrayList<>(););
        return studentDetails;
    }
    /*
    =======================SO FXML-required mappings=======================
    */

    public UpdateStudentInput updateStudentInputFromStudent(Student student) {
        UpdateStudentInput updateStudentInput = new UpdateStudentInput();
        updateStudentInput.setId(student.getId());
        updateStudentInput.setUniversityId(student.getUniversityId());
        updateStudentInput.setFirstName(student.getFirstName());
        updateStudentInput.setSurname(student.getSurname());
        updateStudentInput.setPhoneNumber(student.getPhoneNumber());
        updateStudentInput.setEmail(student.getEmail());
        updateStudentInput.setEntryDate(student.getEntryDate());
        updateStudentInput.setEditButton(new Button());
        updateStudentInput.setDeleteButton(new Button());
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
            list.add( updateStudentInputFromStudent( student1 ) );
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