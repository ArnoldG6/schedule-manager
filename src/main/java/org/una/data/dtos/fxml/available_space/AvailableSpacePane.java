package org.una.data.dtos.fxml.available_space;

import javafx.scene.layout.StackPane;
import lombok.Data;

@Data
public class AvailableSpacePane extends StackPane {
    private AvailableSpaceStackPane parentPane;
    public AvailableSpacePane(AvailableSpaceStackPane parent){
        this.parentPane = parent;
    }

}
