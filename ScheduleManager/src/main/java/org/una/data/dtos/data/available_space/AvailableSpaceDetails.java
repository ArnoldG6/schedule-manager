package org.una.data.dtos.data.available_space;
import lombok.Data;
import org.una.data.dtos.data.block.BlockDetails;
import org.una.data.dtos.data.student.StudentDetails;

@Data
public class AvailableSpaceDetails {
    private Long id;
    private String initialHour;
    private String finalHour;
    private BlockDetails block;
    private StudentDetails student;
    private String day;
}
