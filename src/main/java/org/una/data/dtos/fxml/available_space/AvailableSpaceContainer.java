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
import org.una.custom_fx_components.DraggableNode;
import org.una.data.entities.AvailableSpace;
import org.una.tools.HexColorGenerator;
import org.una.tools.ScheduleTools;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Data
public final class AvailableSpaceContainer {
    //FXML-Required attributes.

    private StackPane stackPane;
    private Rectangle rectangle;
    private Label label;
    private Color color;
    //Customized FXML-Required attributes.
    private DraggableNode nature;
    private Double minX;
    private Double minY;
    private Double maxX;
    private Double maxY;
    //Data attributes.
    private Long id;
    private String day;
    private String initialHour;
    private String finalHour;
    private Long blockID;
    //Student attributes
    private String studentUniversityId;
    private Long studentId;
    private String studentFirstName;
    private String studentSurname;
    private int index;
    public AvailableSpaceContainer(AvailableSpace availableSpace){
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
        rectangle =  new Rectangle();
        rectangle.setStyle("-fx-opacity: 0.9;");
        rectangle.setStroke(color);
        rectangle.setFill(color);
        label = new Label(String.format("%s\n%s %s",this.studentUniversityId,
                this.studentFirstName,this.studentSurname));
        stackPane.getChildren().addAll(rectangle,label);
        nature = new DraggableNode(stackPane);
    }
    public String getHexColorByStudentId(){
        try{
            return HexColorGenerator.generateHexColor(this.studentUniversityId);
        }catch(Exception e){
            return "#c4d9ed"; //Default value in case something fails.
        }
    }
    public void setDraggableLimits(Double minX,Double minY,Double maxX,Double maxY){
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        nature.setDraggableLimits(minX,minY,maxX,maxY);
    }
    public void setDraggableLines(ArrayList<Double> xLines, ArrayList<Double> yLines){
        int gap = ScheduleTools.translateHoursValue(finalHour)-ScheduleTools.translateHoursValue(initialHour);
        double extraCellsHeight;
        if(gap == 1)//No extra cells
            extraCellsHeight = 0;
        else
            extraCellsHeight= rectangle.getHeight()- (rectangle.getHeight() / gap);
        yLines = (ArrayList<Double>) yLines.stream().filter(l-> l <= (maxY-extraCellsHeight)).collect(Collectors.toList());
        nature.setDraggableLines(xLines,yLines);
    }
    public void setWidthDimensions(double width){
        this.getStackPane().setMaxWidth(width);
        this.getStackPane().setMinWidth(width);
        this.getRectangle().setWidth(width);
    }
    public void setHeightDimensions(double height){
        this.getStackPane().setMaxHeight(height);
        this.getStackPane().setMinHeight(height);
        this.getRectangle().setHeight(height);
    }
}
