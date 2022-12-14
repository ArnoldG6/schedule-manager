/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.data.dtos.data.student;
import lombok.Data;

import java.util.Date;

@Data
public class StudentInput {
    //private Long id;
    private String universityId;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private String email;
    private Date entryDate;
}
