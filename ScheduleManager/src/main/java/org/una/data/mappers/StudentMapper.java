package org.una.data.mappers;


import org.una.data.dtos.student.StudentDetails;
import org.una.data.dtos.student.StudentInput;
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
        //studentDetails.setAvailableSpacesIds(new ArrayList<>(););
        return studentDetails;
    }
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