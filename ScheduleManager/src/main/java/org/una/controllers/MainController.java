package org.una.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

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
    void onTab1Select(Event event) {

        if(tab_1_availability.isSelected())
        System.out.println("tab_1");
        System.out.println(event.getEventType());
        event.consume();
    }

    @FXML
    void onTab2Select(Event event) {
        System.out.println("tab_2");
        System.out.println(event.getEventType());
    }

    @FXML
    void onTab3Selected(Event event) {
        System.out.println("tab_3");
        System.out.println(event.getEventType());
    }


    @FXML
    void onSelection(MouseEvent event) {

    }
    @FXML
    void onTab4Selected(Event event) {
        System.out.println("tab_4");
        System.out.println(event.getEventType());
    }

    @FXML
    void initialize() {
        assert main_vbox != null : "fx:id=\"main_vbox\" was not injected: check your FXML file 'MainView.fxml'.";
        assert main_menu_bar != null : "fx:id=\"main_menu_bar\" was not injected: check your FXML file 'MainView.fxml'.";
        assert main_tab_pane != null : "fx:id=\"main_tab_pane\" was not injected: check your FXML file 'MainView.fxml'.";
        assert tab_1_availability != null : "fx:id=\"tab_1_availability\" was not injected: check your FXML file 'MainView.fxml'.";
        assert tab_2_add_student != null : "fx:id=\"tab_2_add_student\" was not injected: check your FXML file 'MainView.fxml'.";
        assert tag_email_tab_2 != null : "fx:id=\"tag_email_tab_2\" was not injected: check your FXML file 'MainView.fxml'.";
        assert tag_student_name_1 != null : "fx:id=\"tag_student_name_1\" was not injected: check your FXML file 'MainView.fxml'.";
        assert tag_date_tab_2 != null : "fx:id=\"tag_date_tab_2\" was not injected: check your FXML file 'MainView.fxml'.";
        assert text_field_name_tab_2 != null : "fx:id=\"text_field_name_tab_2\" was not injected: check your FXML file 'MainView.fxml'.";
        assert text_field_last_name_tab_2 != null : "fx:id=\"text_field_last_name_tab_2\" was not injected: check your FXML file 'MainView.fxml'.";
        assert text_field_email_tab_2 != null : "fx:id=\"text_field_email_tab_2\" was not injected: check your FXML file 'MainView.fxml'.";
        assert date_field_entry_date_tab_2 != null : "fx:id=\"date_field_entry_date_tab_2\" was not injected: check your FXML file 'MainView.fxml'.";
        assert tag_available_spaces_tab_2 != null : "fx:id=\"tag_available_spaces_tab_2\" was not injected: check your FXML file 'MainView.fxml'.";
        assert list_view_available_spaces_tab_2 != null : "fx:id=\"list_view_available_spaces_tab_2\" was not injected: check your FXML file 'MainView.fxml'.";
        assert button_add_student != null : "fx:id=\"button_add_student\" was not injected: check your FXML file 'MainView.fxml'.";
        assert tag_phone_number_tab_2 != null : "fx:id=\"tag_phone_number_tab_2\" was not injected: check your FXML file 'MainView.fxml'.";
        assert text_field_phone_number_tab_2 != null : "fx:id=\"text_field_phone_number_tab_2\" was not injected: check your FXML file 'MainView.fxml'.";
        assert tag_last_name_1 != null : "fx:id=\"tag_last_name_1\" was not injected: check your FXML file 'MainView.fxml'.";
        assert tag_id_una_1 != null : "fx:id=\"tag_id_una_1\" was not injected: check your FXML file 'MainView.fxml'.";
        assert text_field_id_tab_2 != null : "fx:id=\"text_field_id_tab_2\" was not injected: check your FXML file 'MainView.fxml'.";
        assert progress_bar_tab_2 != null : "fx:id=\"progress_bar_tab_2\" was not injected: check your FXML file 'MainView.fxml'.";
        assert tab_3_edit_student != null : "fx:id=\"tab_3_edit_student\" was not injected: check your FXML file 'MainView.fxml'.";
        assert table_view_edit_student_tab_3 != null : "fx:id=\"table_view_edit_student_tab_3\" was not injected: check your FXML file 'MainView.fxml'.";
        assert text_field_search_tab_3 != null : "fx:id=\"text_field_search_tab_3\" was not injected: check your FXML file 'MainView.fxml'.";
        assert label_search_tab_3 != null : "fx:id=\"label_search_tab_3\" was not injected: check your FXML file 'MainView.fxml'.";
        assert tab_4_delete_student != null : "fx:id=\"tab_4_delete_student\" was not injected: check your FXML file 'MainView.fxml'.";
        assert table_view_edit_student_tab_4 != null : "fx:id=\"table_view_edit_student_tab_4\" was not injected: check your FXML file 'MainView.fxml'.";
        assert text_field_search_tab_4 != null : "fx:id=\"text_field_search_tab_4\" was not injected: check your FXML file 'MainView.fxml'.";
        assert label_search_tab_4 != null : "fx:id=\"label_search_tab_4\" was not injected: check your FXML file 'MainView.fxml'.";

    }
}
