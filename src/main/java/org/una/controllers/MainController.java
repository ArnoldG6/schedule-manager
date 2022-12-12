/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.una.custom_fx_components.CustomTextFieldTableCell;
import org.una.custom_fx_components.Draggable;
import org.una.data.dtos.data.available_space.AvailableSpaceDetails;
import org.una.data.dtos.data.available_space.AvailableSpaceInput;
import org.una.data.dtos.data.block.BlockDetails;
import org.una.data.dtos.data.block.BlockInput;
import org.una.data.dtos.data.student.StudentInput;
import org.una.data.dtos.data.year.YearDetails;
import org.una.data.dtos.fxml.available_space.AvailableSpaceStackPane;
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
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
    private double availableSpacesColumnsWidth;
    private double availableSpacesRowsHeight;
    private List<AvailableSpaceStackPane> availableSpacesStackPanes;
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
    private final double available_spaces_table_view_width_gap = 13;
    private final double available_spaces_table_view_height_gap = 13;
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
        availableSpacesStackPanes = new ArrayList<>();
        availableSpacesColumnsWidth = 0.0d;
        availableSpacesRowsHeight = 0.0d;
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
        ButtonType yesButton = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,null,yesButton,noButton);
        alert.setTitle("Precaución");
        alert.setHeaderText("");
        alert.setContentText(String.format("¿Está seguro que desea eliminar al estudiante %s %s?",
                student.getFirstName(), student.getSurname()));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.orElse(noButton) == yesButton) {
            try{
                studentService.deleteById(student.getId());
                Alert successfulDelete = new Alert(Alert.AlertType.INFORMATION);
                successfulDelete.setTitle("¡Éxito!");
                successfulDelete.setHeaderText("");
                successfulDelete.setContentText("Se ha eliminado al estudiante.\n");
                filterEditTabData(null);
                clearAvailableSpaceTabData();
                successfulDelete.showAndWait();

            }catch(Exception e){
                Alert unsuccessfulDelete = new Alert(Alert.AlertType.ERROR);
                unsuccessfulDelete.setTitle("¡Error!");
                unsuccessfulDelete.setHeaderText("");
                unsuccessfulDelete.setContentText("Ha ocurrido un error.\n");
                unsuccessfulDelete.setContentText("No se ha eliminado al estudiante.\n");
                unsuccessfulDelete.showAndWait();
                e.printStackTrace();
            }
        }
    }

    public void clearAddAvailableSpaceData(){
        this.addAvailableSpaceInput.setId(null);
        this.addAvailableSpaceInput.setDay(null);
        this.addAvailableSpaceInput.setInitialHour(null);
        this.addAvailableSpaceInput.setFinalHour(null);
        this.addAvailableSpaceInput.setBlockID(null);
        this.availableSpacesIdToDelete.clear();
        this.deleteAvailableSpacesButton.setDisable(true);

    }

    public void deleteAvailableSpaces(Long studentId){
        try{

            availableSpaceService.deleteAll(availableSpacesIdToDelete);
            availableSpacesListView.getItems().clear();
            for(AvailableSpaceDetails availableSpace: studentService.
                    findById(studentId).getAvailableSpaceDetailsList()){
                availableSpacesListViewItems.add(availableSpace.listViewToString());
            }
            //Data clear
            availableSpacesIdToDelete.clear();
            clearAvailableSpaceTabData();
            deleteAvailableSpacesButton.setDisable(true);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void addAvailableSpace(){

        try{
            StringBuilder errorMessage = new StringBuilder();
            boolean error = false;
            if(addAvailableSpaceInput.getBlockID() == null){
                errorMessage.append("Debe seleccionar un ciclo.\n");
                error = true;
            }
            if(addAvailableSpaceInput.getDay() == null){
                errorMessage.append("Debe seleccionar un día.\n");
                error = true;
            }

            if(addAvailableSpaceInput.getFinalHour() == null){
                errorMessage.append("Debe seleccionar una hora de finalización.\n");
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
            alert.setTitle("¡Ingreso exitoso!");
            alert.setHeaderText("");
            alert.setContentText("Se ha registrado el nuevo espacio disponible.");
            alert.showAndWait();
            //Data reset
            this.clearAddAvailableSpaceData();
            this.clearAvailableSpaceTabData();
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
    @FXML
    public void editTabEditAvailableSpacesForm(UpdateStudentInput student){
        try{
            addAvailableSpaceInput.setStudentID(student.getId());
            //Alert
            availableSpaceCloseButton = new ButtonType("Cerrar", ButtonBar.ButtonData.CANCEL_CLOSE);
            availableSpaceAlert = new Alert(Alert.AlertType.NONE,null, availableSpaceCloseButton);
            availableSpaceAlert.setTitle("Información de espacios disponibles");
            availableSpaceAlert.setHeaderText(null);
            availableSpaceAlert.setHeight(400);
            availableSpaceAlert.setWidth(400);
            //Initial Hour
            initialHourMenuButton = new MenuButton("Hora Inicial");
            for(String hour: this.availabilityHours){
                initialHourMenuItem = new MenuItem(hour);
                initialHourMenuButton.getItems().add(initialHourMenuItem);
                initialHourMenuItem.setOnAction(i ->{
                    this.addAvailableSpaceInput.setInitialHour(hour);
                    //System.out.println(this.addAvailableSpaceInput);
                });
            }

            //Final Hour
            finalHourMenuButton = new MenuButton("Hora Final");
            for(String hour: this.availabilityHours){
                finalHourMenuItem = new MenuItem(hour);
                finalHourMenuButton.getItems().add(finalHourMenuItem);
                finalHourMenuItem.setOnAction(b -> {
                    this.addAvailableSpaceInput.setFinalHour(hour);
                    //System.out.println(this.addAvailableSpaceInput);
                });
            }
            //Day
            dayMenuButton = new MenuButton("Día");
            for(String day: this.availabilityDays){
                dayMenuItem = new MenuItem(day);
                dayMenuButton.getItems().add(dayMenuItem);
                dayMenuItem.setOnAction(d->{
                    this.addAvailableSpaceInput.setDay(day);
                    //System.out.println(this.addAvailableSpaceInput);
                });
            }
            //Year and Block
            yearMenuButton = new MenuButton("Año");
            blockMenuButton = new MenuButton("Ciclo");
            for(YearDetails year: recordedYears) {
                yearMenuItem = new MenuItem(String.valueOf(year.getYear()));
                yearMenuButton.getItems().add(yearMenuItem);
                yearMenuItem.setOnAction(a -> {
                    this.addAvailableSpaceInput.setYear(year.getYear());
                    //System.out.println(this.addAvailableSpaceInput);
                    //Updates blockMenuButton options based on selected Year
                    blockMenuButton.getItems().clear(); //Cleans blockMenuButton options list
                    for (BlockDetails block : year.getBlocks()) {
                        MenuItem blockMenuItem = new MenuItem(block.getName());
                        blockMenuButton.getItems().add(blockMenuItem);
                        blockMenuItem.setOnAction(b -> {
                            this.addAvailableSpaceInput.setBlockID(block.getId());
                            this.addAvailableSpaceInput.setBlockName(block.getName());
                            //System.out.println(this.addAvailableSpaceInput);
                        });
                    }
                });
            }
            //Pane
            availableSpacePane = new FlowPane();
            //Add Button
            addAvailableSpaceButton = new Button(" Agregar ");
            addAvailableSpaceButton.setOnAction(a->{
                this.addAvailableSpace();
            });


            deleteAvailableSpacesButton  = new Button(" Eliminar ");
            deleteAvailableSpacesButton.setDisable(true);
            deleteAvailableSpacesButton.setOnAction(a->{
                this.deleteAvailableSpaces(student.getId());
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
            spacer2.setPrefWidth(55);
            spacer3 = new Region();
            spacer3.setPrefHeight(30);
            spacer3.setPrefWidth(165);

            availableSpacePane.getChildren().addAll(
                    yearMenuButton,blockMenuButton,dayMenuButton,
                    initialHourMenuButton,finalHourMenuButton,
                    addAvailableSpaceButton, spacer1,spacer2,availableSpacesListView, spacer3,
                    deleteAvailableSpacesButton
            );


            availableSpaceAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            //availableSpaceAlert.setResizable(true);
            availableSpaceAlert.getDialogPane().setPrefSize(450, 450);
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
            alert.setTitle("¡Error al actualizar el campo!");
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

    private void adjustAvailableSpacesStackPanesDimensions(){
        int foundHourIteration, notFoundHourIteration;
        boolean hourFound;
        if(availableSpacesStackPanes != null) {
            for (AvailableSpaceStackPane availableSpaceStackPane : availableSpacesStackPanes) {
                //Hour-Rows Y coordinate adjust
                foundHourIteration = 0;
                notFoundHourIteration = 0;
                hourFound = false;
                //Hour-Rows Y coordinate adjust
                for (String hour : availabilityHours) {
                    if (hourFound) foundHourIteration += 1;
                    else notFoundHourIteration += 1;
                    if (hour.equals(availableSpaceStackPane.getInitialHour())) {
                        hourFound = true;
                        availableSpaceStackPane.getStackPane().setTranslateY(getAvailableSpaceRowYTranslation(notFoundHourIteration));
                    }
                    if (hour.equals(availableSpaceStackPane.getFinalHour())) {
                        availableSpaceStackPane.setHeightDimensions(getAvailableSpaceRowHeight(foundHourIteration));
                        break;
                    }
                }
                //Day-Columns X coordinate adjust
                availableSpaceStackPane.setWidthDimensions(availableSpacesColumnsWidth);
                availableSpaceStackPane.getStackPane().setTranslateX(getAvailableSpaceColumnXTranslation(
                        ScheduleTools.translateDaysValue(availableSpaceStackPane.getDay())
                ));
            }
            /*
            double yColumnHeaderGap = available_spaces_table_view.getHeight() -
                    (availableSpacesRowsHeight*available_spaces_table_view.getItems().size());
            double yColumnHeaderGap2 = available_spaces_tab_anchor_pane.getHeight()-available_spaces_table_view.getHeight();
            double initialYCoordinate = (yColumnHeaderGap2 - yColumnHeaderGap) + available_spaces_table_view_height_gap;
            double initialXCoordinate = availableSpacesColumnsWidth * 1 + available_spaces_table_view_width_gap;
            for(Node node: available_spaces_tab_anchor_pane.getChildren()){
                try{
                    Rectangle xd = (Rectangle) node;
                    available_spaces_tab_anchor_pane.getChildren().remove(xd);
                }catch(Exception e){
                    ;//e.printStackTrace();
                }
            }

            Rectangle r = new Rectangle(initialXCoordinate,initialYCoordinate,50,50);
            available_spaces_tab_anchor_pane.getChildren().add(r);
        */
        }
    }
    private void adjustRowsHeight(TableView<?> tableView){
        tableView.heightProperty().addListener((obs, prevRes, newRes) -> {
            availableSpacesRowsHeight  = (Double) newRes / tableView.getItems().size();
            availableSpacesRowsHeight = availableSpacesRowsHeight-(availableSpacesRowsHeight*0.07);
            tableView.setFixedCellSize(availableSpacesRowsHeight);
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
            edit_tab_phone_number_col = new TableColumn<>("Telefóno");
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


    private void moveAvailableSpaceStackPanesByOneIndex(AvailableSpaceStackPane availableSpaceStackPane){
        AvailableSpaceStackPane aux;
        LinkedList<AvailableSpaceStackPane> originalAvailableSpaceStackPanesByDay =
                availableSpacesStackPanes.stream()
                        .filter(s -> s.getDay().equals(availableSpaceStackPane.getDay())).collect(Collectors.toCollection(LinkedList::new));
        if(originalAvailableSpaceStackPanesByDay.size() == 1) return; //Does nothing
        List<Integer> originalIndexes = originalAvailableSpaceStackPanesByDay.stream().map(AvailableSpaceStackPane::getIndex).collect(Collectors.toList());
        aux = originalAvailableSpaceStackPanesByDay.getLast();
        originalAvailableSpaceStackPanesByDay.removeLast();
        originalAvailableSpaceStackPanesByDay.addFirst(aux);
        int indexCursor = 0;
        //This for here is required to clean and put a space holder, this requires to be optimized into a single for.
        for(AvailableSpaceStackPane availableSpaceStackPane1: originalAvailableSpaceStackPanesByDay){
            available_spaces_tab_anchor_pane.getChildren().set(originalIndexes.get(indexCursor),new Rectangle(0,0,0,0)); //Putting a placeholder
            availableSpaceStackPane1.setIndex(originalIndexes.get(indexCursor));
            indexCursor += 1;
        }
        indexCursor = 0;
        for(AvailableSpaceStackPane availableSpaceStackPane1: originalAvailableSpaceStackPanesByDay){
            available_spaces_tab_anchor_pane.getChildren().set(originalIndexes.get(indexCursor),
                    availableSpaceStackPane1.getStackPane());
            indexCursor+=1;
        }

    }
    private void drawAvailableSpacesRectangles(){
        try{
            Draggable.Nature nature;
            for(AvailableSpaceStackPane availableSpaceStackPane: availableSpacesStackPanes)
                available_spaces_tab_anchor_pane.getChildren().remove(availableSpaceStackPane.getStackPane());
            availableSpacesStackPanes.clear();
            if(studentAvailabilityBlockInput.getId() != null){
                availableSpacesStackPanes = blockService.
                        findBlockFullDetailsById(studentAvailabilityBlockInput).getAvailableSpaceStackPaneList();
                for(AvailableSpaceStackPane availableSpaceStackPane: availableSpacesStackPanes){
                    nature = new Draggable.Nature(availableSpaceStackPane.getStackPane());
                    availableSpaceStackPane.setIndex(available_spaces_tab_anchor_pane.getChildren().size());
                    available_spaces_tab_anchor_pane.getChildren().add(availableSpaceStackPane.getStackPane());
                    availableSpaceStackPane.getStackPane().setOnMouseClicked(e-> {
                        if (e.getButton() == MouseButton.SECONDARY)
                            moveAvailableSpaceStackPanesByOneIndex(availableSpaceStackPane);
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
        if(recordedYears != null){ //Data initialization.
            //Event handlers.
            for(YearDetails year: recordedYears) {
                available_spaces_year_menu_item = new MenuItem(year.getYear().toString());
                available_spaces_year_menu_button.getItems().add(available_spaces_year_menu_item);
                available_spaces_year_menu_item.setOnAction(a -> {
                    available_spaces_year_menu_button.setText(year.getYear().toString());
                    studentAvailabilityBlockInput.setYear(year.getYear());
                    available_spaces_block_menu_button.getItems().clear(); //Cleans options list
                    drawAvailableSpacesRectangles();
                    if(year.getBlocks()!=null){
                        for (BlockDetails block : year.getBlocks()) {
                            if(block.equals(year.getBlocks().get(0))){//Default initial value
                                studentAvailabilityBlockInput.setName(block.getName());
                                studentAvailabilityBlockInput.setId(block.getId());
                                available_spaces_block_menu_button.setText(block.getName());
                                drawAvailableSpacesRectangles();
                            }
                            MenuItem available_spaces_block_menu_item = new MenuItem(block.getName());
                            available_spaces_block_menu_button.getItems().add(available_spaces_block_menu_item);
                            available_spaces_block_menu_item.setOnAction(b -> {
                                available_spaces_block_menu_button.setText(block.getName());
                                studentAvailabilityBlockInput.setName(block.getName());
                                studentAvailabilityBlockInput.setId(block.getId());
                                drawAvailableSpacesRectangles();
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
            errorMessage += "Debe ingresar el telefóno. \n";
            error = true;
        }

        try{
            if (date_field_entry_date_tab_2.getValue() == null)
                throw new Exception("Null date");
            addTabStudentInput.setEntryDate(Date.valueOf(date_field_entry_date_tab_2.getValue()));
        }catch(Exception e){
            errorMessage += "Debe ingresar una fecha válida. \n";
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
            alert.setTitle("¡Registro de estudiante exitoso!");
            alert.setHeaderText("");
            alert.setContentText(String.format("Se ha registrado al estudiante %s %s.",
                    addTabStudentInput.getFirstName(),
                    addTabStudentInput.getSurname())
            );
            this.clearAddStudentTabData();
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("¡Error al registrar el estudiante!");
            alert.setHeaderText("");
            alert.setContentText(String.format("Ha ocurrido un error al registrar a %s %s. \n" +
                            "Revise los datos que ingresó.",
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

    void clearAvailableSpaceTabData(){
        if(availableSpacesStackPanes != null){
            for(AvailableSpaceStackPane availableSpaceStackPane: availableSpacesStackPanes)
                available_spaces_tab_anchor_pane.getChildren().remove(availableSpaceStackPane.getStackPane());
            availableSpacesStackPanes.clear();
        }
        studentAvailabilityBlockInput = new BlockInput();
        recordedYears = yearService.findAll();
        initYearAndBlockComboBoxesEvents();
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
