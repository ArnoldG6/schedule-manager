package org.una.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.una.data.dtos.data.available_space.AvailableSpaceDetails;
import org.una.data.dtos.data.available_space.AvailableSpaceInput;
import org.una.data.dtos.data.block.BlockDetails;
import org.una.data.dtos.data.student.StudentInput;
import org.una.data.dtos.data.year.YearDetails;
import org.una.data.dtos.fxml.UpdateStudentInput;
import org.una.data.entities.AvailableSpace;
import org.una.services.AvailableSpaceService;
import org.una.services.BlockService;
import org.una.services.StudentService;
import org.una.services.YearService;

import java.net.URL;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MainController {
    /*
        StudentInput-required fields to store info between tabs.
    */


    @Autowired
    private StudentService studentService;


    @Autowired
    private BlockService blockService;

    @Autowired
    private YearService yearService;

    @Autowired
    private AvailableSpaceService availableSpaceService;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox main_vbox;

    @FXML
    private MenuBar main_menu_bar;

    @FXML
    private TabPane main_tab_pane;

    @FXML
    private Tab tab_1_availability;

    @FXML
    private Tab tab_2_add_student;

    @FXML
    private Label tag_email_tab_2;

    @FXML
    private Label tag_student_name_1;

    @FXML
    private Label tag_date_tab_2;

    @FXML
    private TextField text_field_name_tab_2;

    @FXML
    private TextField text_field_last_name_tab_2;

    @FXML
    private TextField text_field_email_tab_2;

    @FXML
    private DatePicker date_field_entry_date_tab_2;

    @FXML
    private Label tag_available_spaces_tab_2;

    @FXML
    private ListView<?> list_view_available_spaces_tab_2;

    @FXML
    private Button button_add_student;

    @FXML
    private Label tag_phone_number_tab_2;

    @FXML
    private TextField text_field_phone_number_tab_2;

    @FXML
    private Label tag_last_name_1;

    @FXML
    private Label tag_id_una_1;

    @FXML
    private TextField text_field_id_tab_2;

    @FXML
    private ProgressBar progress_bar_tab_2;

    @FXML
    private Tab tab_3_edit_student;
    /*
    ========================================SO Add-student Tab attributes========================================
     */
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
    private final List<String> availabilityDays = Arrays.asList("Lunes","Martes","Miércoles","Jueves","Viernes");

    @FXML
    private TableView<UpdateStudentInput> table_view_edit_student_tab_3;

    @FXML
    private TableColumn<UpdateStudentInput, String> edit_tab_una_id_col, edit_tab_first_name_col,
            edit_tab_surname_col,edit_tab_phone_number_col,edit_tab_email_col;
    @FXML
    private TableColumn<UpdateStudentInput, Date> edit_tab_col_entry_date;
    @FXML
    private TableColumn<UpdateStudentInput, Button> edit_tab_col_edit_button, edit_tab_delete_button;

    /*
    ========================================EO Edit-student Tab attributes========================================
     */
    @FXML
    private TextField text_field_search_tab_3;

    @FXML
    private Label label_search_tab_3;

    @FXML
    private Tab tab_4_delete_student;

    @FXML
    private TableView<?> table_view_edit_student_tab_4;

    @FXML
    private TextField text_field_search_tab_4;

    @FXML
    private Label label_search_tab_4;

    @FXML
    private Canvas canvas_availability;

    public MainController(){
        addTabStudentInput = new StudentInput();
        addAvailableSpaceInput = new AvailableSpaceInput();
        availableSpacesIdToDelete = new HashSet<>();
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
            this.availableSpacesIdToDelete.clear();
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

        System.out.println(availableSpacesIdToDelete);
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
                    System.out.println(this.addAvailableSpaceInput);
                });
            }

            //Final Hour
            finalHourMenuButton = new MenuButton("Hora Final");
            for(String hour: this.availabilityHours){
                finalHourMenuItem = new MenuItem(hour);
                finalHourMenuButton.getItems().add(finalHourMenuItem);
                finalHourMenuItem.setOnAction(b -> {
                    this.addAvailableSpaceInput.setFinalHour(hour);
                    System.out.println(this.addAvailableSpaceInput);
                });
            }
            //Day
            dayMenuButton = new MenuButton("Día");
            for(String day: this.availabilityDays){
                dayMenuItem = new MenuItem(day);
                dayMenuButton.getItems().add(dayMenuItem);
                dayMenuItem.setOnAction(d->{
                    this.addAvailableSpaceInput.setDay(day);
                    System.out.println(this.addAvailableSpaceInput);
                });
            }
            //Year and Block
            yearMenuButton = new MenuButton("Año");
            blockMenuButton = new MenuButton("Ciclo");
            for(YearDetails year: yearService.findAll()) {
                yearMenuItem = new MenuItem(String.valueOf(year.getYear()));
                yearMenuButton.getItems().add(yearMenuItem);
                yearMenuItem.setOnAction(a -> {
                    this.addAvailableSpaceInput.setYear(year.getYear());
                    System.out.println(this.addAvailableSpaceInput);
                    //Updates blockMenuButton options based on selected Year
                    blockMenuButton.getItems().clear(); //Cleans blockMenuButton options list
                    for (BlockDetails block : year.getBlocks()) {
                        MenuItem blockMenuItem = new MenuItem(block.getName());
                        blockMenuButton.getItems().add(blockMenuItem);
                        blockMenuItem.setOnAction(b -> {
                            this.addAvailableSpaceInput.setBlockID(block.getId());
                            this.addAvailableSpaceInput.setBlockName(block.getName());
                            System.out.println(this.addAvailableSpaceInput);
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

    void initEditTabTableView(){
        try{
            edit_tab_una_id_col = new TableColumn<>("ID UNA");
            edit_tab_una_id_col.setCellFactory(TextFieldTableCell.forTableColumn());
            edit_tab_una_id_col.setMinWidth(130);
            edit_tab_una_id_col.setCellValueFactory(new PropertyValueFactory<>("universityId"));
            edit_tab_first_name_col = new TableColumn<>("Nombre");
            edit_tab_first_name_col.setCellFactory(TextFieldTableCell.forTableColumn());
            edit_tab_first_name_col.setMinWidth(130);
            edit_tab_first_name_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            edit_tab_surname_col = new TableColumn<>("Apellidos");
            edit_tab_surname_col.setCellFactory(TextFieldTableCell.forTableColumn());
            edit_tab_surname_col.setMinWidth(130);
            edit_tab_surname_col.setCellValueFactory(new PropertyValueFactory<>("surname"));
            edit_tab_phone_number_col = new TableColumn<>("Telefóno");
            edit_tab_phone_number_col.setCellFactory(TextFieldTableCell.forTableColumn());
            edit_tab_phone_number_col.setMinWidth(100);
            edit_tab_phone_number_col.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            edit_tab_email_col = new TableColumn<>("Email");
            edit_tab_email_col.setCellFactory(TextFieldTableCell.forTableColumn());
            edit_tab_email_col.setMinWidth(130);
            edit_tab_email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
            edit_tab_col_entry_date = new TableColumn<>("Fecha de Ingreso");
            edit_tab_col_entry_date.setMinWidth(120);
            edit_tab_col_entry_date.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
            edit_tab_col_edit_button = new TableColumn<>("Espacios Disponibles");
            edit_tab_col_edit_button.setMinWidth(20);
            edit_tab_col_edit_button.setCellValueFactory(new PropertyValueFactory<>("editButton"));
            edit_tab_delete_button = new TableColumn<>("Eliminar");
            edit_tab_delete_button.setMinWidth(20);
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

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void onTab4Selected(Event event) {

    }

    @FXML
    void initialize() {
        /*
        canvas_availability.setHeight(650);
        canvas_availability.setWidth(870);
        var gc = canvas_availability.getGraphicsContext2D();
        drawLines(gc);
        setText(gc);
        */
        initEditTabTableView();

    }


    private void setText(GraphicsContext gc) {
        int y = 60;
        int x = 168;
        int hora = 7;
        String s = ":00";
        String time = "";
        for (int i = 0; i < 13; i++) {
            if (hora < 10) {
                time = new StringBuilder().append("0").toString();
            }
            time = new StringBuilder().append(time).append(hora).append(s).toString();
            if (hora < 12) {
                time = new StringBuilder().append(time).append(" am").toString();
            } else if (hora == 12) {
                time = new StringBuilder().append(time).append(" md").toString();
            } else {
                time = new StringBuilder().append(time).append(" pm").toString();
            }
            gc.fillText(time, 40, y);
            hora++;
            time = "";
            y += 49;
        }

        for(int i = 0; i<5; i++) {
            switch (x) {
                case 168:
                    gc.fillText("LUNES", x, 35);
                    break;
                case 318:
                    gc.fillText("MARTES", x, 35);
                    break;
                case 468:
                    gc.fillText("MIERCOLES", x, 35);
                    break;
                case 618:
                    gc.fillText("JUEVES", x, 35);
                    break;
                case 768:
                    gc.fillText("VIERNES", x, 35);
                    break;
                default:
                    gc.fillText("", x, 35);
            }
            x = x + 150;
        }
    }

    private void drawLines(GraphicsContext gc) {
        double y = 60;
        double x= 110;

        gc.beginPath();

        //Draws the line in the X axis
        for(int i = 0; i<13;i++){
            gc.moveTo(100, y);
            gc.lineTo(860, y);
            y = y+49;
        };

        //Draws the line in the Y axis
        for(int i = 0; i<12;i++){
            gc.moveTo(x, 50.5);//
            gc.lineTo(x, 700);
            x = x+150;
        };
        gc.stroke();
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
            this.resetAddStudentTabData();
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

    void resetAddStudentTabData(){
        addTabStudentInput = new StudentInput();
        text_field_id_tab_2.setText(null);
        text_field_name_tab_2.setText(null);
        text_field_last_name_tab_2.setText(null);
        text_field_email_tab_2.setText(null);
        date_field_entry_date_tab_2.setValue(null);
        text_field_phone_number_tab_2.setText(null);
    }
}
