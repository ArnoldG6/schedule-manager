package org.una.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML
    private Button button_add_student;

    @FXML
    private DatePicker date_field_entry_date_tab_2;

    @FXML
    private Label label_search_tab_3;

    @FXML
    private Label label_search_tab_4;

    @FXML
    private ListView<?> list_view_availlable_spaces_tab_2;

    @FXML
    private MenuBar main_menu_bar;

    @FXML
    private TabPane main_tab_pane;

    @FXML
    private VBox main_vbox;

    @FXML
    private ProgressBar progress_bar_tab_2;

    @FXML
    private Tab tab_1_availability;

    @FXML
    private Tab tab_2_add_student;

    @FXML
    private Tab tab_3_edit_student;

    @FXML
    private Tab tab_4_delete_student;

    @FXML
    private TableView<?> table_view_edit_student_tab_3;

    @FXML
    private TableView<?> table_view_edit_student_tab_4;

    @FXML
    private Label tag_available_spaces_tab_2;

    @FXML
    private Label tag_date_tab_2;

    @FXML
    private Label tag_email_tab_2;

    @FXML
    private Label tag_id_una_1;

    @FXML
    private Label tag_last_name_1;

    @FXML
    private Label tag_phone_number_tab_2;

    @FXML
    private Label tag_student_name_1;

    @FXML
    private TextField text_field_email_tab_2;

    @FXML
    private TextField text_field_id_tab_2;

    @FXML
    private TextField text_field_last_name_tab_2;

    @FXML
    private TextField text_field_name_tab_2;

    @FXML
    private TextField text_field_phone_number_tab_2;

    @FXML
    private TextField text_field_search_tab_3;

    @FXML
    private TextField text_field_search_tab_4;

    @FXML
    void onButtonAddStudentClicked(ActionEvent event) {

    }

}
