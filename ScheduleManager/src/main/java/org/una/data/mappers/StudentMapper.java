package org.una.data.mappers;


import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.una.data.dtos.student.StudentDetails;
import org.una.data.dtos.student.StudentInput;
import org.una.data.entities.Student;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);
    StudentDetails studentDetailsFromStudent(Student student);
    List<StudentDetails> studentDetailsFromStudentList(List<Student> student);
    Student studentFromStudentInput(StudentInput studentInput);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void studentFromStudentInput(StudentDetails dto, @MappingTarget Student student);



}
