package org.una.services;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.data.dtos.data.student.StudentDetails;
import org.una.data.dtos.data.student.StudentInput;
import org.una.data.dtos.fxml.UpdateStudentInput;
import org.una.data.entities.Student;
import org.una.data.mappers.StudentMapper;
import org.una.data.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public final class StudentService {


    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    /*
    =======================SO FXML-required services=======================
    */

    public List<UpdateStudentInput> findAllUpdateStudentInput() {
        return studentMapper.updateStudentInputListFromStudentList(studentRepository.findAll());
    }

    public List<UpdateStudentInput> filterByAllFieldsUpdateStudentInput(String s1, String s2, String s3, String s4, String s5){
        return studentMapper.updateStudentInputListFromStudentList(
                studentRepository.findByUniversityIdContainingOrFirstNameContainingIgnoreCaseOrSurnameContainingIgnoreCaseOrPhoneNumberContainingOrEmailContainingIgnoreCase(
                        s1,s2,s3,s4,s5
                )
        );
    }

    public void updateFromUpdateStudentInput(UpdateStudentInput updateStudentInput) throws Exception {
        Optional<Student> target = studentRepository.findById(updateStudentInput.getId());
        if (!target.isPresent())
            throw new Exception(String.format("The Student with the id: %s not found!", (updateStudentInput.getId())));
        Student source = studentMapper.studentFromUpdateStudentInput(updateStudentInput);
        BeanUtils.copyProperties(source,target.get());
        studentRepository.saveAndFlush(target.get());
    }

    /*
    =======================EO FXML-required services=======================
     */
    public List<StudentDetails> findAll() {
        return studentMapper.studentDetailsFromStudentList(studentRepository.findAll());
    }


    public StudentDetails findById(Long id) throws Exception {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new Exception(String.format("The Student with the id: %s not found!", id));
        }
        return studentMapper.studentDetailsFromStudent(student.get());
    }


    public StudentDetails create(StudentInput studentInput) {
        Student student = studentMapper.studentFromStudentInput(studentInput);
        return studentMapper.studentDetailsFromStudent(studentRepository.saveAndFlush(student));
    }




    public void deleteById(Long id) throws Exception {
        if (!studentRepository.findById(id).isPresent()) {
            throw new Exception(String.format("The StudentRepository with the id: %s not found!", id));
        } else {
            studentRepository.deleteById(id);
        }
    }


}