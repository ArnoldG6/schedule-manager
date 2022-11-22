package org.una.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.una.data.dtos.data.student.StudentDetails;
import org.una.data.dtos.data.student.StudentInput;
import org.una.services.StudentService;
import org.una.services.YearService;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

@Component
public class MainController {
    /*
        StudentInput-required fields to store info between tabs.
    */

    public MainController(){
        editTabStudentInput = new StudentInput();
    }
    @Autowired
    private StudentService studentService;


    @Autowired
    YearService yearService;

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
    ========================================SO Edit-student Tab attributes========================================
     */
    private StudentInput editTabStudentInput;
    @FXML
    private TableView<StudentDetails> table_view_edit_student_tab_3;

    @FXML
    private TableColumn<StudentDetails, String> edit_tab_una_id_col,
            edit_tab_first_name_col,edit_tab_surname_col,edit_tab_phone_number_col,edit_tab_email_col;
    @FXML
    private TableColumn<StudentDetails, Date> edit_tab_col_entry_date;
    @FXML
    private TableColumn<StudentDetails, Button> edit_tab_col_edit_button;
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

    @FXML
    void onTab1Select(Event event) {
        event.consume();
    }

    @FXML
    void onTab2Select(Event event) {
    }

    @FXML
    void onTab3Selected(Event event) {
        try{
            this.refreshStudentEditTabData();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void filterTab3Data(Event event){
        try{
            String searchPattern = text_field_search_tab_3.getText();
            if(!(searchPattern == null || searchPattern.replace(" ","").isEmpty()))
                table_view_edit_student_tab_3.setItems(
                        FXCollections.observableArrayList(studentService.
                                filterByAllFields(searchPattern,searchPattern,searchPattern,searchPattern,searchPattern)
                        )
                );
            else
                table_view_edit_student_tab_3.setItems(FXCollections.observableArrayList(studentService.findAll()));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void refreshStudentEditTabData(){
        try{

            final ObservableList<StudentDetails> data = FXCollections.
                    observableArrayList(studentService.findAll()
                    );
            table_view_edit_student_tab_3.setItems(data);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void initEditTabTableView(){
        try{

            edit_tab_una_id_col = new TableColumn("ID UNA");
            edit_tab_una_id_col.setMinWidth(136);
            edit_tab_una_id_col.setCellValueFactory(new PropertyValueFactory<>("universityId"));

            edit_tab_first_name_col = new TableColumn("Nombre");
            edit_tab_first_name_col.setMinWidth(135);
            edit_tab_first_name_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));

            edit_tab_surname_col = new TableColumn("Apellidos");
            edit_tab_surname_col.setMinWidth(138);
            edit_tab_surname_col.setCellValueFactory(new PropertyValueFactory<>("surname"));

            edit_tab_phone_number_col = new TableColumn("Telefóno");
            edit_tab_phone_number_col.setMinWidth(135);
            edit_tab_phone_number_col.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

            edit_tab_email_col = new TableColumn("Email");
            edit_tab_email_col.setMinWidth(139);
            edit_tab_email_col.setCellValueFactory(new PropertyValueFactory<>("email"));


            edit_tab_col_entry_date = new TableColumn("Fecha de Ingreso");
            edit_tab_col_entry_date.setMinWidth(135);
            edit_tab_col_entry_date.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
            edit_tab_col_edit_button = new TableColumn("Editar");
            edit_tab_col_edit_button.setMinWidth(135);
            edit_tab_col_edit_button.setCellValueFactory(new PropertyValueFactory<>("editButton"));

            table_view_edit_student_tab_3.getColumns().addAll(
                    edit_tab_una_id_col, edit_tab_first_name_col, edit_tab_surname_col,
                    edit_tab_phone_number_col, edit_tab_email_col, edit_tab_col_entry_date,
                    edit_tab_col_edit_button
            );

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void onTab4Selected(Event event) {

    }

    @FXML
    void initialize() {
        canvas_availability.setHeight(650);
        canvas_availability.setWidth(870);
        initEditTabTableView();
        var gc = canvas_availability.getGraphicsContext2D();
        drawLines(gc);
        setText(gc);
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
            editTabStudentInput.setEntryDate(Date.valueOf(date_field_entry_date_tab_2.getValue()));
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
            editTabStudentInput.setUniversityId(text_field_id_tab_2.getText());
            editTabStudentInput.setFirstName(text_field_name_tab_2.getText());
            editTabStudentInput.setSurname(text_field_last_name_tab_2.getText());
            editTabStudentInput.setEmail(text_field_email_tab_2.getText());
            editTabStudentInput.setPhoneNumber(text_field_phone_number_tab_2.getText());
            studentService.create(editTabStudentInput);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¡Registro de estudiante exitoso!");
            alert.setHeaderText("");
            alert.setContentText(String.format("Se ha registrado al estudiante %s %s.",
                    editTabStudentInput.getFirstName(),
                    editTabStudentInput.getSurname())
            );
            this.resetTab2Data();
            alert.showAndWait();
        }catch (Exception e){
            System.err.println(e);
        }

    }

    void resetTab2Data(){
        editTabStudentInput = new StudentInput();
        text_field_id_tab_2.setText(null);
        text_field_name_tab_2.setText(null);
        text_field_last_name_tab_2.setText(null);
        text_field_email_tab_2.setText(null);
        date_field_entry_date_tab_2.setValue(null);
        text_field_phone_number_tab_2.setText(null);
    }


}
