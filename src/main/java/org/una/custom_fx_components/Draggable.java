package org.una.custom_fx_components;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

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
        }
        public void setDraggableLimits(Double minX,Double minY,Double maxX,Double maxY){
            this.minX = minX;
            this.minY = minY;
            this.maxX = maxX;
            this.maxY = maxY;
        }
        @Override
        public final void handle(final MouseEvent event) {
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
                double xTranslation = initialTranslateX + deltaX;
                double yTranslation = initialTranslateY + deltaY;
                if((xTranslation > minX && xTranslation < maxX)
                && (yTranslation > minY && yTranslation < maxY)){
                    dragNodes.setTranslateX(xTranslation);
                    dragNodes.setTranslateY(yTranslation);
                    //System.out.println(dragNodes.getTranslateX());
                    //System.out.println(dragNodes.getTranslateY());
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