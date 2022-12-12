package org.una.custom_fx_components;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Modified version of @author phill
 * adapted-to-requirements version by: @author ArnoldG6
 */
public class Draggable {
    public static final class Nature implements EventHandler<MouseEvent> {
        private double lastMouseX = 0, lastMouseY = 0;
        //Min and max draggable limits for translateX and translateY
        private Double minX;
        private Double minY;
        private Double maxX;
        private Double maxY;
        private ArrayList<Double> xLines;
        private ArrayList<Double> yLines;
        private boolean dragging = false;
        private final boolean enabled = true;
        private final Node eventNode;
        private final Node dragNodes;
        public Nature(final Node node) {
            this(node, node);
        }
        public Nature(final Node eventNode, final Node dragNodes) {
            this.eventNode = eventNode;
            this.dragNodes = dragNodes;
            this.eventNode.addEventHandler(MouseEvent.ANY, this);
            this.minX = 0.0d;
            this.minY = 0.0d;
            this.maxX = 0.0d;
            this.maxY = 0.0d;
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
            System.out.println("("+dragNodes.getTranslateX()+","+closestX+")");
            System.out.println("("+dragNodes.getTranslateY()+","+closestY+")");
            eventNode.setTranslateX(closestX);
            eventNode.setTranslateY(closestY);
            this.lastMouseX = event.getSceneX();
            this.lastMouseY = event.getSceneY();
        }
        @Override
        public final void handle(final MouseEvent event) {
            double xTranslation=0.0d,yTranslation =0.0d;
            if (MouseEvent.MOUSE_PRESSED == event.getEventType()) {
                if (this.enabled && this.eventNode.contains(event.getX(), event.getY())) {
                    this.lastMouseX = event.getSceneX();
                    this.lastMouseY = event.getSceneY();
                    event.consume();
                }
            } else if (MouseEvent.MOUSE_DRAGGED == event.getEventType()) {
                if (!this.dragging)
                    this.dragging = true;
                final double deltaX = event.getSceneX() - this.lastMouseX;
                final double deltaY = event.getSceneY() - this.lastMouseY;
                final double initialTranslateX = dragNodes.getTranslateX();
                final double initialTranslateY = dragNodes.getTranslateY();
                xTranslation = initialTranslateX + deltaX;
                yTranslation = initialTranslateY + deltaY;
                if((xTranslation > minX && xTranslation < maxX)
                && (yTranslation > minY && yTranslation < maxY)){
                    dragNodes.setTranslateX(xTranslation);
                    dragNodes.setTranslateY(yTranslation);
                    //calculateProximity(xTranslation,yTranslation,event);
                    this.lastMouseX = event.getSceneX();
                    this.lastMouseY = event.getSceneY();
                    event.consume();
                }
            } else if (MouseEvent.MOUSE_RELEASED == event.getEventType()) {
                if (this.dragging) {
                    event.consume();
                    this.dragging = false;
                }
            }
        }


    }
}