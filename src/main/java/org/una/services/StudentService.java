/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.services;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.data.dtos.data.student.StudentDetails;
import org.una.data.dtos.data.student.StudentInput;
import org.una.data.dtos.fxml.student.UpdateStudentInput;
import org.una.data.entities.AvailableSpace;
import org.una.data.entities.Student;
import org.una.data.repository.AvailableSpaceRepository;
import org.una.data.repository.StudentRepository;
import org.una.mappers.EntityMapper;

import java.util.List;
import java.util.Optional;

@Service
public final class StudentService {


    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AvailableSpaceRepository availableSpaceRepository;
    @Autowired
    private EntityMapper entityMapper;

    /*
    =======================SO FXML-required services=======================
    */

    public List<UpdateStudentInput> findAllUpdateStudentInput() {
        return entityMapper.updateStudentInputListFromStudentList(studentRepository.findAll());
    }

    public List<UpdateStudentInput> filterByAllFieldsUpdateStudentInput(String s1, String s2, String s3, String s4, String s5){
        return entityMapper.updateStudentInputListFromStudentList(
                studentRepository.findByUniversityIdContainingOrFirstNameContainingIgnoreCaseOrSurnameContainingIgnoreCaseOrPhoneNumberContainingOrEmailContainingIgnoreCase(
                        s1,s2,s3,s4,s5
                )
        );
    }

    public void updateFromUpdateStudentInput(UpdateStudentInput updateStudentInput) throws Exception {
        Optional<Student> target = studentRepository.findById(updateStudentInput.getId());
        if (!target.isPresent())
            throw new Exception(String.format("The Student with the id: %s not found!", (updateStudentInput.getId())));
        Student source = entityMapper.studentFromUpdateStudentInput(updateStudentInput);
        BeanUtils.copyProperties(source,target.get());
        studentRepository.saveAndFlush(target.get());
    }

    /*
    =======================EO FXML-required services=======================
     */
    public List<StudentDetails> findAll() {
        return entityMapper.studentDetailsFromStudentList(studentRepository.findAll());
    }


    public StudentDetails findById(Long id) throws Exception {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new Exception(String.format("The Student with the id: %s not found!", id));
        }
        return entityMapper.studentDetailsFromStudent(student.get());
    }


    public StudentDetails create(StudentInput studentInput) {
        Student student = entityMapper.studentFromStudentInput(studentInput);
        return entityMapper.studentDetailsFromStudent(studentRepository.saveAndFlush(student));
    }




    public void deleteById(Long id) throws Exception {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent())
            throw new Exception(String.format("The StudentRepository with the id: %s not found!", id));
        if(student.get().getAvailableSpaces()!=null)
            availableSpaceRepository.deleteAll(student.get().getAvailableSpaces());
        studentRepository.deleteById(id);
    }


}