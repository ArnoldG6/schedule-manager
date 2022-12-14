/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.controllers;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.una.custom_fx_components.CustomTextFieldTableCell;
import org.una.data.dtos.data.available_space.AvailableSpaceDetails;
import org.una.data.dtos.data.available_space.AvailableSpaceInput;
import org.una.data.dtos.data.block.BlockDetails;
import org.una.data.dtos.data.block.BlockInput;
import org.una.data.dtos.data.student.StudentInput;
import org.una.data.dtos.data.year.YearDetails;
import org.una.data.dtos.fxml.available_space.AvailableSpaceContainer;
import org.una.data.dtos.fxml.available_space.AvailableSpaceTableCellRow;
import org.una.data.dtos.fxml.student.UpdateStudentInput;
import org.una.services.AvailableSpaceService;
import org.una.services.BlockService;
import org.una.services.StudentService;
import org.una.services.YearService;
import org.una.settings.UniversalSettings;
import org.una.tools.ScheduleTools;

import java.sql.Date;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
public class MainController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private YearService yearService;

    @Autowired
    private BlockService blockService;
    @Autowired
    private AvailableSpaceService availableSpaceService;

    @FXML
    private AnchorPane main_anchor_pane;

    /*
    ========================================SO student availability Tab attributes========================================
    */
    private List<AvailableSpaceContainer> availableSpaceContainers;
    private BlockInput studentAvailabilityBlockInput;
    private List<YearDetails> recordedYears;
    @FXML
    private MenuItem available_spaces_block_menu_item;
    @FXML
    private MenuButton available_spaces_block_menu_button;
    @FXML
    private MenuItem available_spaces_year_menu_item;
    @FXML
    private MenuButton available_spaces_year_menu_button;
    @FXML
    private AnchorPane available_spaces_tab_anchor_pane;
    @FXML
    private AnchorPane available_spaces_tab_menu_button_anchor_pane;
    @FXML
    private TableView<AvailableSpaceTableCellRow> available_spaces_table_view;
    private final Double available_spaces_table_view_width_gap = 13.0d;
    private final Double available_spaces_table_view_height_gap = 13.0d;
    private ArrayList<Pair<String,Double>> availableSpaceContainersXLimits;
    private ArrayList<Pair<String,Double>> availableSpaceContainersYLimits;
    private Double availableSpacesColumnsWidth;
    private Double availableSpacesRowsHeight;
    private Double availableSpaceContainerMinX;
    private Double availableSpaceContainerMaxX;
    private Double availableSpaceContainerMinY;
    private Double availableSpaceContainerMaxY;
    @FXML
    private TableColumn<AvailableSpaceTableCellRow, String> available_spaces_table_view_hours_column;
    @FXML
    private TableColumn<AvailableSpaceTableCellRow, String> available_spaces_table_view_monday_column,
            available_spaces_table_view_tuesday_column,available_spaces_table_view_wednesday_column,
            available_spaces_table_view_thursday_column,
            available_spaces_table_view_friday_column;
    /*
    ========================================EO student availability Tab attributes========================================
     */
        /*
    ========================================SO Add-student Tab attributes========================================
     */

    @FXML
    private TextField text_field_name_tab_2;

    @FXML
    private TextField text_field_last_name_tab_2;

    @FXML
    private TextField text_field_email_tab_2;

    @FXML
    private DatePicker date_field_entry_date_tab_2;

    @FXML
    private TextField text_field_phone_number_tab_2;

    @FXML
    private TextField text_field_id_tab_2;

    private StudentInput addTabStudentInput;
        /*
    ========================================EO Add-student Tab attributes========================================
     */
    /*
    ========================================SO Edit-student Tab attributes========================================
     */

    private AvailableSpaceInput addAvailableSpaceInput;
    private ButtonType availableSpaceCloseButton;
    private Alert availableSpaceAlert;
    private MenuButton initialHourMenuButton;
    private MenuItem initialHourMenuItem;
    private MenuButton finalHourMenuButton;
    private MenuItem finalHourMenuItem;
    private MenuButton dayMenuButton;
    private MenuItem dayMenuItem;
    private MenuButton yearMenuButton;
    private MenuButton blockMenuButton;
    private MenuItem yearMenuItem;
    private FlowPane availableSpacePane;
    private Button addAvailableSpaceButton, deleteAvailableSpacesButton;
    private ListView<String> availableSpacesListView;
    private ObservableList<String> availableSpacesListViewItems;
    private HashSet<Long> availableSpacesIdToDelete;
    private Region spacer1, spacer2,spacer3;

    private final List<String> availabilityHours = Arrays.asList("07:00","08:00","09:00","10:00",
            "11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00",
            "20:00","21:00"
    );
    private final List<String> availabilityDays = Arrays.asList(
            UniversalSettings.MONDAY_ES.value,UniversalSettings.TUESDAY_ES.value,
            UniversalSettings.WEDNESDAY_ES.value,UniversalSettings.THURSDAY_ES.value,
            UniversalSettings.FRIDAY_ES.value
    );

    @FXML
    private TableView<UpdateStudentInput> table_view_edit_student_tab_3;

    @FXML
    private TableColumn<UpdateStudentInput, String> edit_tab_una_id_col, edit_tab_first_name_col,
            edit_tab_surname_col,edit_tab_phone_number_col,edit_tab_email_col;
    @FXML
    private TableColumn<UpdateStudentInput, Date> edit_tab_col_entry_date;
    @FXML
    private TableColumn<UpdateStudentInput, Button> edit_tab_col_edit_button, edit_tab_delete_button;

    @FXML
    private TextField text_field_search_tab_3;

    /*
    ========================================EO Edit-student Tab attributes========================================
     */

    public MainController(){
        addTabStudentInput = new StudentInput();
        addAvailableSpaceInput = new AvailableSpaceInput();
        availableSpacesIdToDelete = new HashSet<>();
        studentAvailabilityBlockInput = new BlockInput();
        availableSpaceContainers = new ArrayList<>();
        availableSpacesColumnsWidth = 0.0d;
        availableSpacesRowsHeight = 0.0d;
        availableSpaceContainerMinX = 0.0d;
        availableSpaceContainerMaxX = 0.0d;
        availableSpaceContainerMinY = 0.0d;
        availableSpaceContainerMaxY = 0.0d;
        availableSpaceContainersXLimits = new ArrayList<>();
        availableSpaceContainersYLimits = new ArrayList<>();
    }

    @FXML
    void onTab1Select(Event event) {
        event.consume();
    }

    @FXML
    void onTab2Select(Event event) {
    }


    @FXML
    void onEditTabSelected(Event event) {
        try{
            filterEditTabData(event);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    public void editTabDeleteForm(UpdateStudentInput student){
        ButtonType yesButton = new ButtonType("S??", ButtonBar.ButtonData.OK_DONE);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,null,yesButton,noButton);
        alert.setTitle("Precauci??n");
        alert.setHeaderText("");
        alert.setContentText(String.format("??Est?? seguro que desea eliminar al estudiante %s %s?",
                student.getFirstName(), student.getSurname()));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.orElse(noButton) == yesButton) {
            try{
                studentService.deleteById(student.getId());
                Alert successfulDelete = new Alert(Alert.AlertType.INFORMATION);
                successfulDelete.setTitle("????xito!");
                successfulDelete.setHeaderText("");
                successfulDelete.setContentText("Se ha eliminado al estudiante.\n");
                filterEditTabData(null);
                clearAvailableSpaceTabData(true);
                successfulDelete.showAndWait();

            }catch(Exception e){
                Alert unsuccessfulDelete = new Alert(Alert.AlertType.ERROR);
                unsuccessfulDelete.setTitle("??Error!");
                unsuccessfulDelete.setHeaderText("");
                unsuccessfulDelete.setContentText("Ha ocurrido un error.\n");
                unsuccessfulDelete.setContentText("No se ha eliminado al estudiante.\n");
                unsuccessfulDelete.showAndWait();
                e.printStackTrace();
            }
        }
    }

    public void clearAddAvailableSpaceData(){
        addAvailableSpaceInput.setId(null);
        addAvailableSpaceInput.setDay(null);
        addAvailableSpaceInput.setYear(null);
        addAvailableSpaceInput.setInitialHour(null);
        addAvailableSpaceInput.setFinalHour(null);
        addAvailableSpaceInput.setBlockID(null);
        availableSpacesIdToDelete.clear();
        deleteAvailableSpacesButton.setDisable(true);
        //initialHourMenuButton.getItems().clear();
        finalHourMenuButton.getItems().clear();
        //dayMenuButton.getItems().clear();
        //yearMenuButton.getItems().clear();
        blockMenuButton.getItems().clear();
        initialHourMenuButton.setText("Hora Inicial");
        finalHourMenuButton.setText("Hora Final");
        dayMenuButton.setText("D??a");
        yearMenuButton.setText("A??o");
        blockMenuButton.setText("Ciclo");

    }

    public void deleteAvailableSpaces(Long studentId){
        try{

            availableSpaceService.deleteAll(availableSpacesIdToDelete);
            availableSpacesListView.getItems().clear();
            for(AvailableSpaceDetails availableSpace: studentService.
                    findById(studentId).getAvailableSpaceDetailsList())
                availableSpacesListViewItems.add(availableSpace.listViewToString());
            //Data clear
            availableSpacesIdToDelete.clear();
            clearAvailableSpaceTabData(true);
            deleteAvailableSpacesButton.setDisable(true);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void addAvailableSpace(){

        try{
            StringBuilder errorMessage = new StringBuilder();
            boolean error = false;
            if(addAvailableSpaceInput.getYear() == null){
                errorMessage.append("Debe seleccionar un a??o.\n");
                error = true;
            }
            if(addAvailableSpaceInput.getBlockID() == null){
                errorMessage.append("Debe seleccionar un ciclo.\n");
                error = true;
            }
            if(addAvailableSpaceInput.getDay() == null){
                errorMessage.append("Debe seleccionar un d??a.\n");
                error = true;
            }

            if(addAvailableSpaceInput.getFinalHour() == null){
                errorMessage.append("Debe seleccionar una hora de finalizaci??n.\n");
                error = true;
            }
            if(addAvailableSpaceInput.getInitialHour() == null){
                errorMessage.append("Debe seleccionar una hora de inicio.\n");
                error = true;
            }
            if(error){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error de ingreso de datos");
                alert.setHeaderText("");
                alert.setContentText(errorMessage.toString());
                alert.showAndWait();
                return;
            }

            this.availableSpacesListView.getItems().add(availableSpaceService.create(addAvailableSpaceInput)
                    .listViewToString());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("??Ingreso exitoso!");
            alert.setHeaderText("");
            alert.setContentText("Se ha registrado el nuevo espacio disponible.");
            alert.showAndWait();
            //Data reset
            this.clearAddAvailableSpaceData();
            this.clearAvailableSpaceTabData(true);
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de ingreso de datos");
            alert.setHeaderText("");
            alert.setContentText("Ha ocurrido un error al crear el nuevo espacio disponible.");
            alert.showAndWait();
        }

    }

    public void addAvailableSpaceStringToDeleteList(String availableSpaceStringRep, boolean unselected, boolean selected){
        String id = availableSpaceStringRep.split("-")[0];
        if(selected)
            availableSpacesIdToDelete.add(Long.valueOf(id));
        if(unselected)
            availableSpacesIdToDelete.remove(Long.valueOf(id));
        if(availableSpacesIdToDelete.size() == 0)
            deleteAvailableSpacesButton.setDisable(true);
        else
            deleteAvailableSpacesButton.setDisable(false);

        //System.out.println(availableSpacesIdToDelete);
    }

    public void updateFinalHourMenuItemList(List<String> hours){
        finalHourMenuButton.getItems().clear();
        for(String hour: hours){
            finalHourMenuItem = new MenuItem(hour);
            finalHourMenuButton.getItems().add(finalHourMenuItem);
            finalHourMenuItem.setOnAction(b -> {
                addAvailableSpaceInput.setFinalHour(hour);
                finalHourMenuButton.setText(hour);
            });
        }
    }
    public void editTabEditAvailableSpacesForm(UpdateStudentInput student){
        try{
            List<String> availabilityFinalHours = new ArrayList<>(availabilityHours);
            availabilityFinalHours.add(ScheduleTools.calculateMaxPossibleFinalHour(availabilityFinalHours));
            addAvailableSpaceInput.setStudentID(student.getId());
            //Alert
            availableSpaceCloseButton = new ButtonType("Cerrar", ButtonBar.ButtonData.CANCEL_CLOSE);
            availableSpaceAlert = new Alert(Alert.AlertType.NONE,null, availableSpaceCloseButton);
            availableSpaceAlert.setTitle("Informaci??n de espacios disponibles");
            availableSpaceAlert.setHeaderText(null);
            availableSpaceAlert.setHeight(400);
            availableSpaceAlert.setWidth(900);
            //Initial Hour
            initialHourMenuButton = new MenuButton();
            initialHourMenuButton.setMinWidth(100);
            initialHourMenuButton.setPrefWidth(100);
            initialHourMenuButton.setMaxWidth(100);
            initialHourMenuButton.setText("Hora Inicial");
            //Final Hour
            finalHourMenuButton = new MenuButton();
            finalHourMenuButton.setMinWidth(100);
            finalHourMenuButton.setPrefWidth(100);
            finalHourMenuButton.setMaxWidth(100);
            finalHourMenuButton.setText("Hora Final");
            finalHourMenuButton.setOnMouseClicked(e->{
                if(addAvailableSpaceInput.getInitialHour() == null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("??Importante!");
                    alert.setHeaderText("");
                    alert.setContentText("Antes de seleccionar una hora de cierre,\n debe seleccionar una hora de inicio.");
                    alert.showAndWait();
                    e.consume();
                }
            });
            for(String hour: availabilityHours){
                initialHourMenuItem = new MenuItem(hour);
                initialHourMenuButton.getItems().add(initialHourMenuItem);
                initialHourMenuItem.setOnAction(i ->{
                    finalHourMenuButton.setOnMouseClicked(null);
                    addAvailableSpaceInput.setInitialHour(hour);
                    initialHourMenuButton.setText(hour);
                    //Reset based on selection
                    finalHourMenuButton.setText("Hora Final");
                    addAvailableSpaceInput.setFinalHour(null);
                    updateFinalHourMenuItemList(ScheduleTools.filterHoursGreaterThan(availabilityFinalHours,hour));
                });
            }

            dayMenuButton = new MenuButton();
            dayMenuButton.setMinWidth(100);
            dayMenuButton.setPrefWidth(100);
            dayMenuButton.setMaxWidth(100);
            //Day
            dayMenuButton.setText("D??a");
            for(String day: availabilityDays){
                dayMenuItem = new MenuItem(day);
                dayMenuButton.getItems().add(dayMenuItem);
                dayMenuItem.setOnAction(d->{
                    addAvailableSpaceInput.setDay(day);
                    dayMenuButton.setText(day);
                });
            }
            //Year and Block
            yearMenuButton = new MenuButton();
            yearMenuButton.setMinWidth(100);
            yearMenuButton.setPrefWidth(100);
            yearMenuButton.setMaxWidth(100);
            yearMenuButton.setText("A??o");
            blockMenuButton = new MenuButton();
            blockMenuButton.setMinWidth(100);
            blockMenuButton.setPrefWidth(100);
            blockMenuButton.setMaxWidth(100);
            blockMenuButton.setText("Ciclo");

            blockMenuButton.setOnMouseClicked(e->{
                if(addAvailableSpaceInput.getYear() == null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("??Importante!");
                    alert.setHeaderText("");
                    alert.setContentText("Antes de seleccionar un ciclo, debe seleccionar un a??o.\n");
                    alert.showAndWait();
                    e.consume();
                }
            });




            for(YearDetails year: recordedYears) {
                yearMenuItem = new MenuItem(String.valueOf(year.getYear()));
                yearMenuButton.getItems().add(yearMenuItem);
                yearMenuItem.setOnAction(a -> {
                    //blockMenuButton.setOnMouseClicked(null);
                    yearMenuButton.setText(year.getYear().toString());
                    blockMenuButton.setText("Ciclo");
                    addAvailableSpaceInput.setBlockID(null);
                    addAvailableSpaceInput.setBlockName(null);
                    this.addAvailableSpaceInput.setYear(year.getYear());
                    //Updates blockMenuButton options based on selected Year
                    blockMenuButton.getItems().clear(); //Cleans blockMenuButton options list
                    for (BlockDetails block : year.getBlocks()) {
                        MenuItem blockMenuItem = new MenuItem(block.getName());
                        blockMenuButton.getItems().add(blockMenuItem);
                        blockMenuItem.setOnAction(b -> {
                            blockMenuButton.setText(block.getName());
                            addAvailableSpaceInput.setBlockID(block.getId());
                            addAvailableSpaceInput.setBlockName(block.getName());

                        });
                    }
                });
            }
            //Pane
            availableSpacePane = new FlowPane();
            //Add Button
            addAvailableSpaceButton = new Button(" Agregar ");
            addAvailableSpaceButton.setPrefWidth(100);
            addAvailableSpaceButton.setMaxWidth(100);
            addAvailableSpaceButton.setMinWidth(100);
            addAvailableSpaceButton.setOnAction(a->{
                addAvailableSpace();
            });
            deleteAvailableSpacesButton  = new Button(" Eliminar ");
            deleteAvailableSpacesButton.setDisable(true);
            deleteAvailableSpacesButton.setPrefWidth(100);
            deleteAvailableSpacesButton.setMaxWidth(100);
            deleteAvailableSpacesButton.setMinWidth(100);
            deleteAvailableSpacesButton.setOnAction(a->{
                deleteAvailableSpaces(student.getId());
            });
            //AvailableSpaces ListView
            availableSpacesListView = new ListView<>();
            availableSpacesListViewItems = FXCollections.observableArrayList ();

            for(AvailableSpaceDetails availableSpace: student.getAvailableSpaceDetailsList())
                availableSpacesListViewItems.add(availableSpace.listViewToString());
            availableSpacesListView.setItems(availableSpacesListViewItems);

            availableSpacesListView.setCellFactory(CheckBoxListCell.forListView(item -> {
                BooleanProperty observable = new SimpleBooleanProperty();
                observable.addListener((obs, wasSelected, isNowSelected) ->
                        addAvailableSpaceStringToDeleteList(item,wasSelected,isNowSelected)
                );
                return observable ;
            }));

            spacer1 = new Region();
            spacer1.setPrefHeight(80);
            spacer2 = new Region();
            spacer2.setPrefWidth(155);
            spacer3 = new Region();
            spacer3.setPrefHeight(45);
            spacer3.setPrefWidth(265);
            availableSpacePane.getChildren().addAll(
                    yearMenuButton,blockMenuButton,dayMenuButton,
                    initialHourMenuButton,finalHourMenuButton,
                    addAvailableSpaceButton, spacer1,spacer2,availableSpacesListView, spacer3,
                    deleteAvailableSpacesButton
            );
            availableSpaceAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            //availableSpaceAlert.setResizable(true);
            availableSpaceAlert.getDialogPane().setPrefSize(650, 480);
            availableSpacesListView.setPrefWidth(300);
            availableSpacesListView.setPrefHeight(300);
            availableSpaceAlert.getDialogPane().setContent(availableSpacePane);
            if(availableSpaceAlert.showAndWait().orElse(ButtonType.NO) == availableSpaceCloseButton){
                this.clearAddAvailableSpaceData();
                this.filterEditTabData(null);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    void updateEditTabData(List<UpdateStudentInput> students){
        ExecutorService te1 = Executors.newSingleThreadExecutor();
        try {
            te1.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            for(UpdateStudentInput student: students) {
                                //System.out.printf("T1-%s.\n", Timestamp.from(Instant.now()));
                                student.getEditButton().addEventHandler(MouseEvent.MOUSE_CLICKED,
                                        e -> editTabEditAvailableSpacesForm(student)
                                );
                            }
                        }
                    }
            );
        }catch(Exception e){e.printStackTrace();}
            finally {
            te1.shutdownNow();
        }



        ExecutorService te2 = Executors.newSingleThreadExecutor();
        try {
            te2.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            for(UpdateStudentInput student: students) {
                                //System.out.printf("T2-%s.\n", Timestamp.from(Instant.now()));
                                student.getDeleteButton().addEventHandler(MouseEvent.MOUSE_CLICKED,
                                        e -> editTabDeleteForm(student)
                                );
                            }
                        }
                    }
            );
        }catch(Exception e){e.printStackTrace();}
        finally {
            te2.shutdownNow();
        }
        table_view_edit_student_tab_3.setItems(FXCollections.observableArrayList(students));

    }


    @FXML
    void updateStudentField(UpdateStudentInput student, String fieldToUpdate, String valueToUpdate){
        try{
            switch(fieldToUpdate){
                case "universityId":
                    student.setUniversityId(valueToUpdate);
                    break;
                case "firstName":
                    student.setFirstName(valueToUpdate);
                    break;
                case "surname":
                    student.setSurname(valueToUpdate);
                    break;
                case "phoneNumber":
                    student.setPhoneNumber(valueToUpdate);
                    break;
                case "email":
                    student.setEmail(valueToUpdate);
                    break;
                //case "entryDate":
                //    break;
            }
            studentService.updateFromUpdateStudentInput(student);
            filterEditTabData(null);

        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("??Error al actualizar el campo!");
            alert.setHeaderText("");
            alert.setContentText("Ha ocurrido un error al modificar el campo.\n");
            alert.setContentText("Revise los datos digitados.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void filterEditTabData(Event event){
        try{
            String searchPattern = text_field_search_tab_3.getText();
            if(!(searchPattern == null || searchPattern.replace(" ","").isEmpty()))
                updateEditTabData(
                        studentService.
                                filterByAllFieldsUpdateStudentInput(
                                        searchPattern,searchPattern,searchPattern,searchPattern,searchPattern
                                )
                );
            else
                updateEditTabData(studentService.findAllUpdateStudentInput());

        }catch (Exception e){
            e.printStackTrace();
        }
    }




    private double getAvailableSpaceRowYTranslation(int notFoundHourIteration){
        double yColumnHeaderGap = available_spaces_table_view.getHeight() -
                (availableSpacesRowsHeight*available_spaces_table_view.getItems().size());
        double yColumnHeaderGap2 = available_spaces_tab_anchor_pane.getHeight()-available_spaces_table_view.getHeight();
        return (availableSpacesRowsHeight * notFoundHourIteration) +
                (yColumnHeaderGap2 - yColumnHeaderGap) + available_spaces_table_view_height_gap;
    }
    private double getAvailableSpaceRowHeight(int foundHourIteration){
        return availableSpacesRowsHeight * foundHourIteration;
    }
    private double getAvailableSpaceColumnXTranslation(int dayColumnIteration){
        return availableSpacesColumnsWidth * dayColumnIteration + available_spaces_table_view_width_gap;
    }
    public void updateAvailableSpaceContainersDraggableXLimits(){
        availableSpaceContainerMinX = getAvailableSpaceColumnXTranslation(1);
        availableSpaceContainerMaxX = getAvailableSpaceColumnXTranslation(availabilityDays.size());
        availableSpaceContainersXLimits.clear();
        for(int i = 1; i<=availabilityDays.size(); i++)
            availableSpaceContainersXLimits.add(new Pair<>(availabilityDays.get(i-1),getAvailableSpaceColumnXTranslation(i)));

    }
    public void updateAvailableSpaceContainersDraggableYLimits(){
        availableSpaceContainerMinY = getAvailableSpaceRowYTranslation(1);
        availableSpaceContainerMaxY = getAvailableSpaceRowYTranslation(availabilityHours.size());
        availableSpaceContainersYLimits.clear();
        for(int i = 1; i<=availabilityHours.size(); i++)
            availableSpaceContainersYLimits.add(new Pair<>(availabilityHours.get(i-1),getAvailableSpaceRowYTranslation(i)));
    }
    private void adjustAvailableSpacesStackPanesDimensions(){
        int foundHourIteration, notFoundHourIteration;
        boolean hourFound;
        List<String> availabilityFinalHours = new ArrayList<>(availabilityHours);
        availabilityFinalHours.add(ScheduleTools.calculateMaxPossibleFinalHour(availabilityFinalHours));
        if(availableSpaceContainers != null) {
            for (AvailableSpaceContainer availableSpaceContainer : availableSpaceContainers) {
                //Hour-Rows Y coordinate adjust
                foundHourIteration = 0;
                notFoundHourIteration = 0;
                hourFound = false;
                //Hour-Rows Y coordinate adjust
                for (String hour : availabilityFinalHours) {
                    if (hourFound) foundHourIteration += 1;
                    else notFoundHourIteration += 1;
                    if (hour.equals(availableSpaceContainer.getInitialHour())) {
                        hourFound = true;
                        availableSpaceContainer.getStackPane().setTranslateY(getAvailableSpaceRowYTranslation(notFoundHourIteration));

                    }
                    if (hour.equals(availableSpaceContainer.getFinalHour())) {
                        availableSpaceContainer.setHeightDimensions(getAvailableSpaceRowHeight(foundHourIteration));
                        break;
                    }

                }
                //Day-Columns X coordinate adjust
                availableSpaceContainer.setWidthDimensions(availableSpacesColumnsWidth);
                availableSpaceContainer.getStackPane().setTranslateX(getAvailableSpaceColumnXTranslation(
                        ScheduleTools.translateDaysValue(availableSpaceContainer.getDay())
                ));
                //Settings X and Y-draggable limits
                availableSpaceContainer.setDraggableLimits(availableSpaceContainerMinX,availableSpaceContainerMinY,
                        availableSpaceContainerMaxX,availableSpaceContainerMaxY);
                availableSpaceContainer.setDraggableLines(availableSpaceContainersXLimits,availableSpaceContainersYLimits);
            }
        }
    }
    private void adjustRowsHeight(TableView<?> tableView){
        tableView.heightProperty().addListener((obs, prevRes, newRes) -> {
            availableSpacesRowsHeight  = (Double) newRes / tableView.getItems().size();
            availableSpacesRowsHeight = availableSpacesRowsHeight-(availableSpacesRowsHeight*0.07);
            tableView.setFixedCellSize(availableSpacesRowsHeight);
            updateAvailableSpaceContainersDraggableYLimits();
            adjustAvailableSpacesStackPanesDimensions();
        });
    }
    private void adjustColumnsWidth(TableView<?> tableView){
        tableView.widthProperty().addListener((obs, prevRes, newRes) -> {
            availableSpacesColumnsWidth = (Double) newRes / tableView.getColumns().size();
            for (TableColumn<?, ?> column: tableView.getColumns()){
                column.setMaxWidth(availableSpacesColumnsWidth);
                column.setMinWidth(availableSpacesColumnsWidth);
            }
            updateAvailableSpaceContainersDraggableXLimits();
            adjustAvailableSpacesStackPanesDimensions();
        });
    }
    void initAvailableSpacesTabTableView(){
        try{
            available_spaces_table_view_hours_column = new TableColumn<>("Hora");
            available_spaces_table_view_hours_column.setSortable(false);
            available_spaces_table_view_hours_column.setCellFactory(CustomTextFieldTableCell.forTableColumn());
            available_spaces_table_view_hours_column.setCellValueFactory(new PropertyValueFactory<>("hour"));

            available_spaces_table_view_monday_column = new TableColumn<>(availabilityDays.get(0));
            available_spaces_table_view_monday_column.setSortable(false);

            available_spaces_table_view_tuesday_column = new TableColumn<>(availabilityDays.get(1));
            available_spaces_table_view_tuesday_column.setSortable(false);

            available_spaces_table_view_wednesday_column = new TableColumn<>(availabilityDays.get(2));
            available_spaces_table_view_wednesday_column.setSortable(false);

            available_spaces_table_view_thursday_column = new TableColumn<>(availabilityDays.get(3));
            available_spaces_table_view_thursday_column.setSortable(false);


            available_spaces_table_view_friday_column = new TableColumn<>(availabilityDays.get(4));
            available_spaces_table_view_friday_column.setSortable(false);
            available_spaces_table_view.getColumns().addAll(
                    available_spaces_table_view_hours_column,available_spaces_table_view_monday_column,
                    available_spaces_table_view_tuesday_column,available_spaces_table_view_wednesday_column,
                    available_spaces_table_view_thursday_column,available_spaces_table_view_friday_column
            );
            available_spaces_table_view.addEventFilter(ScrollEvent.ANY, Event::consume);
            for(String hour: availabilityHours)
                available_spaces_table_view.getItems().add(new AvailableSpaceTableCellRow(hour));
            adjustRowsHeight(available_spaces_table_view);
            adjustColumnsWidth(available_spaces_table_view);

        }catch(Exception e){
            e.printStackTrace();
        }

    }




    void initEditTabTableView(){
        try{
            edit_tab_una_id_col = new TableColumn<>("ID UNA");
            edit_tab_una_id_col.setCellFactory(TextFieldTableCell.forTableColumn());
            edit_tab_una_id_col.setCellValueFactory(new PropertyValueFactory<>("universityId"));
            edit_tab_first_name_col = new TableColumn<>("Nombre");
            edit_tab_first_name_col.setCellFactory(TextFieldTableCell.forTableColumn());
            edit_tab_first_name_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            edit_tab_surname_col = new TableColumn<>("Apellidos");
            edit_tab_surname_col.setCellFactory(TextFieldTableCell.forTableColumn());
            edit_tab_surname_col.setCellValueFactory(new PropertyValueFactory<>("surname"));
            edit_tab_phone_number_col = new TableColumn<>("Telef??no");
            edit_tab_phone_number_col.setCellFactory(TextFieldTableCell.forTableColumn());
            edit_tab_phone_number_col.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            edit_tab_email_col = new TableColumn<>("Email");
            edit_tab_email_col.setCellFactory(TextFieldTableCell.forTableColumn());
            edit_tab_email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
            edit_tab_col_entry_date = new TableColumn<>("Fecha de Ingreso");
            edit_tab_col_entry_date.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
            edit_tab_col_edit_button = new TableColumn<>("Espacios Disponibles");
            edit_tab_col_edit_button.setCellValueFactory(new PropertyValueFactory<>("editButton"));
            edit_tab_delete_button = new TableColumn<>("Eliminar");
            edit_tab_delete_button.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
            ExecutorService te3 = Executors.newSingleThreadExecutor();
            try {
                te3.execute(
                        new Runnable() {
                            @Override
                            public void run() {
                                edit_tab_una_id_col.setOnEditCommit(e->
                                        updateStudentField(e.getTableView().getItems().get(e.getTablePosition().getRow()),
                                                "universityId",e.getNewValue())
                                );
                                edit_tab_first_name_col.setOnEditCommit(e->
                                        updateStudentField(e.getTableView().getItems().get(e.getTablePosition().getRow()),
                                                "firstName",e.getNewValue())
                                );
                                edit_tab_surname_col.setOnEditCommit(e->
                                        updateStudentField(e.getTableView().getItems().get(e.getTablePosition().getRow()),
                                                "surname",e.getNewValue())
                                );
                                edit_tab_phone_number_col.setOnEditCommit(e->
                                        updateStudentField(e.getTableView().getItems().get(e.getTablePosition().getRow()),
                                                "phoneNumber",e.getNewValue())
                                );
                                edit_tab_email_col.setOnEditCommit(e->
                                        updateStudentField(e.getTableView().getItems().get(e.getTablePosition().getRow()),
                                                "email",e.getNewValue())
                                );
                                edit_tab_col_entry_date.setOnEditCommit(e->
                                        updateStudentField(e.getTableView().getItems().get(e.getTablePosition().getRow()),
                                                "entryDate",e.getNewValue().toString())
                                );
                            }
                        }
                );
            }catch(Exception e){e.printStackTrace();}
            finally {
                te3.shutdownNow();
            }
            table_view_edit_student_tab_3.getColumns().addAll(
                    edit_tab_una_id_col, edit_tab_first_name_col, edit_tab_surname_col,
                    edit_tab_phone_number_col, edit_tab_email_col, edit_tab_col_entry_date,
                    edit_tab_col_edit_button,edit_tab_delete_button
            );
            table_view_edit_student_tab_3.setEditable(true);
            adjustColumnsWidth(table_view_edit_student_tab_3);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void moveAvailableSpaceContainerByOneIndex(AvailableSpaceContainer availableSpaceContainer){
        AvailableSpaceContainer aux;
        LinkedList<AvailableSpaceContainer> originalAvailableSpacesContainersByDay =
                availableSpaceContainers.stream()
                        .filter(s -> s.getDay().equals(availableSpaceContainer.getDay())).collect(Collectors.toCollection(LinkedList::new));
        if(originalAvailableSpacesContainersByDay.size() == 1) return; //Does nothing
        List<Integer> originalIndexes = originalAvailableSpacesContainersByDay.stream().map(AvailableSpaceContainer::getIndex).collect(Collectors.toList());
        aux = originalAvailableSpacesContainersByDay.getLast();
        originalAvailableSpacesContainersByDay.removeLast();
        originalAvailableSpacesContainersByDay.addFirst(aux);
        int indexCursor = 0;
        //This for here is required to clean and put a space holder, this requires to be optimized into a single for.
        for(AvailableSpaceContainer availableSpaceContainer1 : originalAvailableSpacesContainersByDay){
            available_spaces_tab_anchor_pane.getChildren().set(originalIndexes.get(indexCursor),new Rectangle(0,0,0,0)); //Putting a placeholder
            availableSpaceContainer1.setIndex(originalIndexes.get(indexCursor));
            indexCursor += 1;
        }
        indexCursor = 0;
        for(AvailableSpaceContainer availableSpaceContainer1 : originalAvailableSpacesContainersByDay){
            available_spaces_tab_anchor_pane.getChildren().set(originalIndexes.get(indexCursor),
                    availableSpaceContainer1.getStackPane());
            indexCursor+=1;
        }

    }
    private void drawAvailableSpacesContainers(){
        try{
            for(AvailableSpaceContainer availableSpaceContainer : availableSpaceContainers)
                available_spaces_tab_anchor_pane.getChildren().remove(availableSpaceContainer.getStackPane());
            availableSpaceContainers.clear();
            if(studentAvailabilityBlockInput.getId() != null){
                availableSpaceContainers = blockService.
                        findBlockFullDetailsById(studentAvailabilityBlockInput).getAvailableSpaceContainerList();
                for(AvailableSpaceContainer availableSpaceContainer : availableSpaceContainers){
                    availableSpaceContainer.injectAvailableSpaceService(availableSpaceService);
                    availableSpaceContainer.setIndex(available_spaces_tab_anchor_pane.getChildren().size());
                    available_spaces_tab_anchor_pane.getChildren().add(availableSpaceContainer.getStackPane());
                    availableSpaceContainer.getStackPane().setOnMouseClicked(e-> {
                        if (e.getButton() == MouseButton.SECONDARY)
                            moveAvailableSpaceContainerByOneIndex(availableSpaceContainer);
                    });
                }
                adjustAvailableSpacesStackPanesDimensions();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initYearAndBlockComboBoxesEvents(){
        available_spaces_year_menu_button.setText(null);
        available_spaces_block_menu_button.setText(null);
        available_spaces_block_menu_button.getItems().clear();
        available_spaces_year_menu_button.getItems().clear();
        available_spaces_block_menu_button.setOnMouseClicked(e->{
            if(studentAvailabilityBlockInput.getYear() == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("??Importante!");
                alert.setHeaderText("");
                alert.setContentText("Antes de seleccionar un ciclo, debe seleccionar un a??o.\n");
                alert.showAndWait();
                e.consume();
            }
        });
        if(recordedYears != null){ //Data initialization.
            //Event handlers.
            for(YearDetails year: recordedYears) {
                available_spaces_year_menu_item = new MenuItem(year.getYear().toString());
                available_spaces_year_menu_button.getItems().add(available_spaces_year_menu_item);
                available_spaces_year_menu_item.setOnAction(a -> {
                    available_spaces_year_menu_button.setText(year.getYear().toString());
                    studentAvailabilityBlockInput.setYear(year.getYear());
                    available_spaces_block_menu_button.getItems().clear(); //Cleans options list
                    drawAvailableSpacesContainers();
                    if(year.getBlocks()!=null){
                        for (BlockDetails block : year.getBlocks()) {
                            if(block.equals(year.getBlocks().get(0))){//Default initial value
                                studentAvailabilityBlockInput.setName(block.getName());
                                studentAvailabilityBlockInput.setId(block.getId());
                                available_spaces_block_menu_button.setText(block.getName());
                                drawAvailableSpacesContainers();
                            }
                            MenuItem available_spaces_block_menu_item = new MenuItem(block.getName());
                            available_spaces_block_menu_button.getItems().add(available_spaces_block_menu_item);
                            available_spaces_block_menu_item.setOnAction(b -> {
                                available_spaces_block_menu_button.setText(block.getName());
                                studentAvailabilityBlockInput.setName(block.getName());
                                studentAvailabilityBlockInput.setId(block.getId());
                                drawAvailableSpacesContainers();
                            });
                        }
                    }

                });
            }
        }
        /*
        On selection event handling.
        */
    }
    /*
    SO: Add Student Section
    */
    @FXML
    void onButtonAddStudentClicked(){
        String errorMessage = "";
        boolean error = false;
        if(text_field_id_tab_2.getText() ==null || text_field_id_tab_2.getText().isEmpty()){
            errorMessage += "Debe ingresar el ID. \n";
            error = true;
        }
        if(text_field_name_tab_2.getText() ==null || text_field_name_tab_2.getText().isEmpty()){
            errorMessage += "Debe ingresar el nombre. \n";
            error = true;
        }
        if(text_field_last_name_tab_2.getText() ==null || text_field_last_name_tab_2.getText().isEmpty()){
            errorMessage += "Debe ingresar los apellidos. \n";
            error = true;
        }

        if(text_field_email_tab_2.getText() ==null || text_field_email_tab_2.getText().isEmpty()){
            errorMessage += "Debe ingresar el email. \n";
            error = true;
        }

        if(text_field_phone_number_tab_2.getText() ==null || text_field_phone_number_tab_2.getText().isEmpty()){
            errorMessage += "Debe ingresar el telef??no. \n";
            error = true;
        }

        try{
            if (date_field_entry_date_tab_2.getValue() == null)
                throw new Exception("Null date");
            addTabStudentInput.setEntryDate(Date.valueOf(date_field_entry_date_tab_2.getValue()));
        }catch(Exception e){
            errorMessage += "Debe ingresar una fecha v??lida. \n";
            error = true;
        }

        if(error){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error de ingreso de datos");
            alert.setHeaderText("");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return;
        }
        try{
            addTabStudentInput.setUniversityId(text_field_id_tab_2.getText());
            addTabStudentInput.setFirstName(text_field_name_tab_2.getText());
            addTabStudentInput.setSurname(text_field_last_name_tab_2.getText());
            addTabStudentInput.setEmail(text_field_email_tab_2.getText());
            addTabStudentInput.setPhoneNumber(text_field_phone_number_tab_2.getText());
            studentService.create(addTabStudentInput);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("??Registro de estudiante exitoso!");
            alert.setHeaderText("");
            alert.setContentText(String.format("Se ha registrado al estudiante %s %s.",
                    addTabStudentInput.getFirstName(),
                    addTabStudentInput.getSurname())
            );
            this.clearAddStudentTabData();
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("??Error al registrar el estudiante!");
            alert.setHeaderText("");
            alert.setContentText(String.format("Ha ocurrido un error al registrar a %s %s. \n" +
                            "Revise los datos que ingres??.",
                    addTabStudentInput.getFirstName(),
                    addTabStudentInput.getSurname())
            );
            alert.showAndWait();
            e.printStackTrace();
        }

    }

    void clearAddStudentTabData(){
        addTabStudentInput = new StudentInput();
        text_field_id_tab_2.setText(null);
        text_field_name_tab_2.setText(null);
        text_field_last_name_tab_2.setText(null);
        text_field_email_tab_2.setText(null);
        date_field_entry_date_tab_2.setValue(null);
        text_field_phone_number_tab_2.setText(null);
    }

    void clearAvailableSpaceTabData(boolean secondaryCall){
        if(availableSpaceContainers != null){
            available_spaces_tab_anchor_pane.getChildren().removeAll(
                    availableSpaceContainers.stream().map(
                            AvailableSpaceContainer::getStackPane).collect(Collectors.toList()
                    )
            );
            availableSpaceContainers.clear();
        }
        studentAvailabilityBlockInput = new BlockInput();
        recordedYears = yearService.findAll();
        if(secondaryCall){
            Platform.runLater(() -> {
                try{
                    initYearAndBlockComboBoxesEvents();
                }catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("??Error!");
                    alert.setHeaderText("");
                    alert.setContentText("Ha ocurrido un error.\n" +
                            "No se han podido cargar los datos de la pesta??a\n" +
                            "de espacios disponibles"
                    );
                    alert.showAndWait();
                }
            });
        }else{
            initYearAndBlockComboBoxesEvents();
        }
    }
    public void stylizeAvailableSpacesTab(){
        String css = Objects.requireNonNull(this.getClass().getResource("/presentation/views/css/main-view.css")).
                toExternalForm();
        available_spaces_table_view.getStylesheets().add(css);
        available_spaces_table_view.getStyleClass().add("table-view");
    }
    @FXML
    void initialize() {
        try{
            stylizeAvailableSpacesTab();
            recordedYears = yearService.findAll();
            initAvailableSpacesTabTableView();
            initYearAndBlockComboBoxesEvents();
            initEditTabTableView();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
