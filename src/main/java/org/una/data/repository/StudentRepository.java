/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.data.repository;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.data.entities.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByUniversityIdContainingOrFirstNameContainingIgnoreCaseOrSurnameContainingIgnoreCaseOrPhoneNumberContainingOrEmailContainingIgnoreCase(
            String s1, String s2,String s3,String s4,String s5
    );
}

