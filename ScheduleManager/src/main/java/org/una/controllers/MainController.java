package org.una.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.una.data.dtos.student.StudentInput;
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
    private StudentInput tab4StudentInput;
    public MainController(){
        tab4StudentInput = new StudentInput();
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

    @FXML
    private TableView<?> table_view_edit_student_tab_3;

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
        //if(tab_1_availability.isSelected())
        //System.out.println("tab_1");
        //System.out.println(event.getEventType());
        event.consume();
    }

    @FXML
    void onTab2Select(Event event) {
        //System.out.println("tab_2");
        //System.out.println(event.getEventType());
    }

    @FXML
    void onTab3Selected(Event event) {
        //System.out.println("tab_3");
        //System.out.println(event.getEventType());
    }


    @FXML
    void onSelection(MouseEvent event) {

    }
    @FXML
    void onTab4Selected(Event event) {
        //System.out.println("tab_4");
        //System.out.println(event.getEventType());
    }

    @FXML
    void initialize() {
        //System.out.println("Initializing...");
        canvas_availability.setHeight(650);
        canvas_availability.setWidth(870);
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
        tab4StudentInput.setUniversityId(text_field_id_tab_2.getText());
        tab4StudentInput.setFirstName(text_field_name_tab_2.getText());
        tab4StudentInput.setSurname(text_field_last_name_tab_2.getText());
        tab4StudentInput.setEmail(text_field_email_tab_2.getText());
        tab4StudentInput.setPhoneNumber(text_field_phone_number_tab_2.getText());
        try{
            tab4StudentInput.setEntryDate(Date.valueOf(date_field_entry_date_tab_2.getValue()));
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error de ingreso de datos");
            alert.setHeaderText("");
            alert.setContentText("Debe ingresar una fecha valida");
            alert.showAndWait();
        }
        ////System.out.println(tab4StudentInput);
        try{
            studentService.create(tab4StudentInput);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¡Registro de estudiante exitoso!");
            alert.setHeaderText("");
            alert.setContentText(String.format("Se ha registrado al estudiante %s %s.",
                    tab4StudentInput.getFirstName(),
                    tab4StudentInput.getSurname())
            );
            this.resetTab4Data();
            alert.showAndWait();
            System.out.println(studentService.findAll());
        }catch (Exception e){
            System.err.println(e);
        }

    }

    void resetTab4Data(){
        tab4StudentInput = new StudentInput(); //Restarts the user info.
        text_field_id_tab_2.setText(null);
        text_field_name_tab_2.setText(null);
        text_field_last_name_tab_2.setText(null);
        text_field_email_tab_2.setText(null);
        date_field_entry_date_tab_2.setValue(null);
        text_field_phone_number_tab_2.setText(null);
    }

    @FXML void onTextFieldNameTab2KeyPressed(){
        tab4StudentInput.setFirstName(text_field_name_tab_2.getText());
        //System.out.println(tab4StudentInput);
    }
    @FXML void onTextFieldLastNameTab2KeyPressed(){
        tab4StudentInput.setSurname(text_field_last_name_tab_2.getText());
        //System.out.println(tab4StudentInput);
    }
    /*
    EO: Add Student Section
    */
    /*

    */

}
