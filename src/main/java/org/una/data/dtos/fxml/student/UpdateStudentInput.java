/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.data.dtos.fxml.student;

import javafx.scene.control.Button;
import lombok.Data;
import org.una.data.dtos.data.student.StudentDetails;
@Data
public class UpdateStudentInput extends StudentDetails {
    private Button editButton;
    private Button deleteButton;
}
