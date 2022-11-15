package org.una.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.data.dtos.student.StudentDetails;
import org.una.data.dtos.student.StudentInput;
import org.una.data.entities.Student;
import org.una.data.mappers.StudentMapper;
import org.una.data.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public final class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<StudentDetails> findAll() {
        return StudentMapper.MAPPER.studentDetailsFromStudentList(studentRepository.findAll());
    }




    public StudentDetails findById(Long id) throws Exception {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new Exception(String.format("The Student with the id: %s not found!", id));
        }
        return StudentMapper.MAPPER.studentDetailsFromStudent(student.get());
    }


    public StudentDetails create(StudentInput studentInput) {
        Student student = StudentMapper.MAPPER.studentFromStudentInput(studentInput);
        return StudentMapper.MAPPER.studentDetailsFromStudent(studentRepository.saveAndFlush(student));
    }


    public StudentDetails update(StudentDetails studentDetails) throws Exception {
        Optional<Student> student = studentRepository.findById(studentDetails.getId());
        if (!student.isPresent()) {
            throw new Exception(String.format("The Student with the id: %s not found!", studentDetails.getId()));
        }
        Student studentUpdated = student.get();
        StudentMapper.MAPPER.studentFromStudentInput(studentDetails, studentUpdated);
        return StudentMapper.MAPPER.studentDetailsFromStudent(studentRepository.save(studentUpdated));
    }



    public void deleteById(Long id) throws Exception {
        if (!studentRepository.findById(id).isPresent()) {
            throw new Exception(String.format("The StudentRepository with the id: %s not found!", id));
        } else {
            studentRepository.deleteById(id);
        }
    }


}