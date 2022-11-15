package org.una.data.dtos.available_space;

import lombok.Data;
import org.una.data.dtos.block.BlockDetails;
import org.una.data.dtos.student.StudentDetails;
import org.una.data.entities.Block;
import org.una.data.entities.Student;

@Data
public class AvailableSpaceInput {
    private Long id;
    private String initialHour;
    private String finalHour;
    //private BlockDetails block;
    //private StudentDetails student;
    private String day;
}
