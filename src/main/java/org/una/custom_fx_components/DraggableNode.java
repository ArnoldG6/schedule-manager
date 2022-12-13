package org.una.custom_fx_components;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Modified version of @author phill
 * adapted-to-requirements version by: @author ArnoldG6
 */
public class DraggableNode implements EventHandler<MouseEvent> {
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
    public DraggableNode(final Node node) {
        this(node, node);
    }
    public DraggableNode(final Node eventNode, final Node dragNode) {
        this.eventNode = eventNode;
        this.dragNode = dragNode;
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
    public void setDraggableLimits(Double minX,Double minY,Double maxX,Double maxY){
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }
    public void setDraggableLines(ArrayList<Double> xLines, ArrayList<Double> yLines){
        this.xLines = xLines;
        this.yLines = yLines;
    }

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
                event.consume();
                this.dragging = false;
            }
        }
    }
}