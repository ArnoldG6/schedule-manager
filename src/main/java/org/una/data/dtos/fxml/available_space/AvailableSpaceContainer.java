/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.data.dtos.fxml.available_space;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Data;
import org.una.data.entities.AvailableSpace;
import org.una.tools.HexColorGenerator;
import org.una.tools.ScheduleTools;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Data
public final class AvailableSpaceContainer implements EventHandler<MouseEvent> {
    //Draggable-required attributes
    private double lastMouseX = 0, lastMouseY = 0;
    //Min and max draggable limits for translateX and translateY
    private Double minX;
    private Double minY;
    private Double maxX;
    private Double maxY;
    private Double yTranslation;
    private Double xTranslation;
    private ArrayList<Double> xLines;
    private ArrayList<Double> yLines;
    private boolean dragging = false;
    private final Node eventNode;
    private final Node dragNode;
    //FXML-Required attributes.
    private StackPane stackPane;
    private Rectangle rectangle;
    private Label label;
    private Color color;
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
        //Data DTO Attributes
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
        //FX Components-required attributes.
        color = Color.web(getHexColorByStudentId());
        stackPane = new StackPane();
        rectangle =  new Rectangle();
        rectangle.setStyle("-fx-opacity: 0.9;");
        rectangle.setStroke(color);
        rectangle.setFill(color);
        label = new Label(String.format("%s\n%s %s",this.studentUniversityId,
                this.studentFirstName,this.studentSurname));
        stackPane.getChildren().addAll(rectangle,label);
        //DraggableNode-required attributes
        eventNode = stackPane;
        dragNode = stackPane;
        this.eventNode.addEventHandler(MouseEvent.ANY, this);
        this.minX = 0.0d;
        this.minY = 0.0d;
        this.maxX = 0.0d;
        this.maxY = 0.0d;
        this.xTranslation=0.0d;
        this.yTranslation=0.0d;
        this.xLines = null;
        this.yLines = null;
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
        this.maxY = maxY-extraCellsHeight();
    }
    public double extraCellsHeight(){
        int gap = ScheduleTools.translateHoursValue(finalHour)-ScheduleTools.translateHoursValue(initialHour);
        if(gap == 1)//No extra cells
            return 0;
        return rectangle.getHeight()- (rectangle.getHeight() / gap);
    }
    public void setDraggableLines(ArrayList<Double> xLines, ArrayList<Double> yLines){
        yLines = (ArrayList<Double>) yLines.stream().filter(l-> l <= (maxY)).collect(Collectors.toList());
        this.xLines = xLines;
        this.yLines = yLines;
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
    //
    private void calculateProximity(double xTranslation, double yTranslation,final MouseEvent event){
        Double closestX = xLines.stream()
                .reduce(Double.MAX_VALUE, (best, current) ->
                        Math.abs(current - xTranslation) < Math.abs(best - xTranslation) ? current : best);
        Double closestY = yLines.stream()
                .reduce(Double.MAX_VALUE, (best, current) ->
                        Math.abs(current - yTranslation) < Math.abs(best - yTranslation) ? current : best);
        dragNode.setTranslateX(closestX);
        dragNode.setTranslateY(closestY);
        this.lastMouseX = event.getSceneX();
        this.lastMouseY = event.getSceneY();
    }
    @Override
    public final void handle(final MouseEvent event) {
        if (MouseEvent.MOUSE_PRESSED == event.getEventType()) {
            if (this.eventNode.contains(event.getX(), event.getY())) {
                this.lastMouseX = event.getSceneX();
                this.lastMouseY = event.getSceneY();
                event.consume();
            }
        } else if (MouseEvent.MOUSE_DRAGGED == event.getEventType()) {
            if (!this.dragging)
                this.dragging = true;
            final double deltaX = event.getSceneX() - this.lastMouseX;
            final double deltaY = event.getSceneY() - this.lastMouseY;
            final double initialTranslateX = dragNode.getTranslateX();
            final double initialTranslateY = dragNode.getTranslateY();
            xTranslation = initialTranslateX + deltaX;
            yTranslation = initialTranslateY + deltaY;
            if((xTranslation > minX && xTranslation < maxX)
                    && (yTranslation > minY && yTranslation < maxY)){
                dragNode.setTranslateX(xTranslation);
                dragNode.setTranslateY(yTranslation);
                this.lastMouseX = event.getSceneX();
                this.lastMouseY = event.getSceneY();
                //event.consume();
            }
        } else if (MouseEvent.MOUSE_RELEASED == event.getEventType()) {
            if (this.dragging) {
                calculateProximity(xTranslation,yTranslation,event);
                this.dragging = false;
                event.consume();
            }
        }
    }
}
