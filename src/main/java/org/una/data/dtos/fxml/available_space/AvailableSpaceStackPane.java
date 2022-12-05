package org.una.data.dtos.fxml.available_space;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import lombok.Data;
import org.una.data.dtos.data.available_space.AvailableSpaceDetails;

import java.awt.*;

@Data
public final class AvailableSpaceStackPane {
    //FXML-Required attributes.
    //private double height;
    //private double width;
    private StackPane stackPane;
    private Rectangle rectangle;
    private Label label;
    //Data attributes.
    protected Long id;
    protected String day;
    protected String initialHour;
    protected String finalHour;
    protected Long blockID;
    //Student attributes
    protected String studentUniversityId;
    protected Long studentId;
    protected String studentFirstName;
    protected String studentSurname;
    //
    public AvailableSpaceStackPane(AvailableSpaceDetails availableSpaceDetails){
    }
}
