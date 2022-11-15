package org.una.data.repository;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.data.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}

