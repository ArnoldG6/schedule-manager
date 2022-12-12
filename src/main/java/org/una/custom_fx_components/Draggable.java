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
                dragNodes.setTranslateX(initialTranslateX + deltaX);
                dragNodes.setTranslateY(initialTranslateY + deltaY);
                System.out.println(dragNodes.getTranslateX());
                System.out.println(dragNodes.getTranslateY());
                this.lastMouseX = event.getSceneX();
                this.lastMouseY = event.getSceneY();
                event.consume();
            } else if (MouseEvent.MOUSE_RELEASED == event.getEventType()) {
                if (this.dragging) {
                    event.consume();
                    this.dragging = false;
                }
            }
        }


    }
}