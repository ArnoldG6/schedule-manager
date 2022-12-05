/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.data.dtos.fxml.available_space;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Data;
import org.una.data.entities.AvailableSpace;
import org.una.tools.HexColorGenerator;

@Data
public final class AvailableSpaceStackPane {
    //FXML-Required attributes.
    //private double height;
    //private double width;
    private StackPane stackPane;
    private Rectangle rectangle;
    private Label label;
    private Color color;
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
    public AvailableSpaceStackPane(AvailableSpace availableSpace){
        this.setId(availableSpace.getId());
        this.setDay(availableSpace.getDay());
        this.setInitialHour(availableSpace.getInitialHour());
        this.setFinalHour(availableSpace.getFinalHour());
        if(availableSpace.getBlock() != null)
            this.setBlockID(availableSpace.getBlock().getId());
        if(availableSpace.getStudent() != null){
            this.setStudentUniversityId(availableSpace.getStudent().getUniversityId());
            this.setStudentId(availableSpace.getStudent().getId());
            this.setStudentFirstName(availableSpace.getStudent().getFirstName());
            this.setStudentSurname(availableSpace.getStudent().getSurname());
        }
        color = Color.web(getHexColorByStudentId());
        stackPane = new StackPane();
        rectangle =  new Rectangle(100, 100, 200, 50);
        rectangle.setStyle("-fx-opacity: 0.5;");
        rectangle.setStroke(color);
        rectangle.setFill(color);
        label = new Label(String.format("%s\n%s-%s",this.studentUniversityId,
                this.studentFirstName,this.studentSurname));
        stackPane.getChildren().addAll(rectangle,label);
    }
    public String getHexColorByStudentId(){
        try{
            return HexColorGenerator.generateHexColor(this.studentUniversityId);
        }catch(Exception e){
            return "#c4d9ed"; //Default value in case something fails.
        }
    }
}
