package lk.ijse.vaxhub.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.vaxhub.bo.BOFactory;
import lk.ijse.vaxhub.bo.custom.EmployeeAttendanceBO;
import lk.ijse.vaxhub.bo.custom.EmployeeBO;
import lk.ijse.vaxhub.dto.EmployeeAttendanceDTO;
import lk.ijse.vaxhub.entity.Employee;
import lk.ijse.vaxhub.entity.EmployeeAttendance;



import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import com.jfoenix.controls.JFXComboBox;
import javafx.scene.control.Label;
import lk.ijse.vaxhub.view.tdm.EmployeeAttendanceTm;

public class EmployeeAttendanceController {

    @FXML
    private JFXComboBox<String> AtenndanceCMB;

    @FXML
    private TableColumn<?, ?> AttendanceColumn;

    @FXML
    private TableColumn<?, ?> AttendanceIdColumn;

    @FXML
    private TextField AttendanceIdTextField;

    @FXML
    private TableColumn<?, ?> DateColumn;

    @FXML
    private Label DateLable;

    @FXML
    private TableView<EmployeeAttendanceTm> EmployeeAttendanceTable;

    @FXML
    private JFXComboBox<String> EmployeeIdCMB;

    @FXML
    private TableColumn<?, ?> EmployeeIdColumn;

    @FXML
    private TableColumn<?, ?> InTimeColumn;

    @FXML
    private TextField InTimeTextField;

    @FXML
    private JFXButton MoveEmployeeManageButton;

    @FXML
    private TableColumn<?, ?> OutTimeColumn;

    @FXML
    private TextField OutTimeTextField;

    @FXML
    private JFXButton clearButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private AnchorPane paneHolder;
    @FXML
    private Label EmployeeIdLBL;
    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton updateButton;


    private List<EmployeeAttendanceDTO> employeeAttendanceList = new ArrayList<>();
    EmployeeAttendanceBO employeeAttendanceBO  = (EmployeeAttendanceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Employee_Attendance);
    EmployeeBO employeeBO  = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Employee);

    public void initialize() {
        this.employeeAttendanceList = getAllEmployeeAttendance();
        setCellDataFactory();
        loadAllAttendance();
        getEmployeeId();
        setAttendance();
        setDate();


    }
    private List<EmployeeAttendanceDTO> getAllEmployeeAttendance() {
        List<EmployeeAttendanceDTO> employeeAttendanceList = null;
        try {
            employeeAttendanceList = employeeAttendanceBO.getAllEmployeeAttendance();
        }  catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employeeAttendanceList;
    }
    private void getEmployeeId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> IdList = employeeBO.getIds();

            for (String id : IdList) {
                obList.add(id);
            }
            EmployeeIdCMB.setItems(obList);


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void EmployeeIdCMBOnAction(ActionEvent event) {
        String id = EmployeeIdCMB.getValue();
        try {
            Employee employee = employeeBO.searchEmployee(id);
            if (employee != null) {
                EmployeeIdCMB.setValue(employee.getEmployee_id());

            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void setAttendance() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("0");
        obList.add("1");
        AtenndanceCMB.setItems(obList);
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        DateLable.setText(String.valueOf(now));

    }

    private void loadAllAttendance() {

        ObservableList<EmployeeAttendanceTm> tmList = FXCollections.observableArrayList();

        for (EmployeeAttendanceDTO employeeAttendance : employeeAttendanceList) {
            EmployeeAttendanceTm employeeAttendanceTm = new EmployeeAttendanceTm(

                    employeeAttendance.getAttendance_id(),
                    employeeAttendance.getEmployee_id(),
                    employeeAttendance.getDate(),
                    employeeAttendance.getIn_time(),
                    employeeAttendance.getOut_time(),
                    employeeAttendance.getAttendance()

            );
            tmList.add(employeeAttendanceTm);

        }

        EmployeeAttendanceTable.setItems(tmList);
        EmployeeAttendanceTm selectedItem = EmployeeAttendanceTable.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }




    private void setCellDataFactory() {
        AttendanceIdColumn.setCellValueFactory(new PropertyValueFactory<>("attendance_id"));
        EmployeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        InTimeColumn.setCellValueFactory(new PropertyValueFactory<>("in_time"));
        OutTimeColumn.setCellValueFactory(new PropertyValueFactory<>("out_time"));
        AttendanceColumn.setCellValueFactory(new PropertyValueFactory<>("attendance"));




    }






    @FXML
    void AbsentButtonOnAction(ActionEvent event) {

    }

    @FXML
    void ClearButtonOnAction(ActionEvent event) {
        clearFields();}

    private void clearFields() {
        AttendanceIdTextField.setText("");
        EmployeeIdCMB.setValue("");
        DateLable.setText("");
        InTimeTextField.setText("");
        OutTimeTextField.setText("");
        AtenndanceCMB.setValue(" ");

    }

    @FXML
    void DeleteButtonOnAction(ActionEvent event) {
        String attendance_id =AttendanceIdTextField.getText();

        try {
            boolean isDeleted = employeeAttendanceBO.deleteEmployeeAttendance(attendance_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                loadAllAttendance();
            }
        } catch (SQLException | ClassNotFoundException e)  {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void MoveEmployeeManageButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/employee_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }

    @FXML
    void PresentButtonOnAction(ActionEvent event) {

    }

    @FXML
    void SaveButtonOnAction(ActionEvent event) {
        String  attendance_id = AttendanceIdTextField.getText();
        String employee_id = EmployeeIdCMB.getValue();
        String date = DateLable.getText();
        String in_time = InTimeTextField.getText();
        String out_time = OutTimeTextField.getText();
        String attendance = AtenndanceCMB.getValue();

        if (attendance_id.trim().length() == 0 || employee_id.trim().length() == 0 || date.trim().length() == 0 || in_time.trim().length() == 0 || out_time.trim().length() == 0|| attendance.trim().length() == 0 ) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }


        EmployeeAttendance employeeAttendance = new EmployeeAttendance(attendance_id,employee_id,date,in_time,out_time,attendance);

        try {
            boolean isSaved = employeeAttendanceBO.saveEmployeeAttendance(new EmployeeAttendanceDTO(attendance_id,employee_id,date,in_time,out_time,attendance));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, " saved!").show();
                clearFields();
                loadAllAttendance();
            }
        }  catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void UpdateButtonOnAction(ActionEvent event) {
        String  attendance_id = AttendanceIdTextField.getText();
        String employee_id = EmployeeIdCMB.getValue();
        String date = DateLable.getText();
        String in_time = InTimeTextField.getText();
        String out_time = OutTimeTextField.getText();
        String attendance = AtenndanceCMB.getValue();




        EmployeeAttendance employeeAttendance = new EmployeeAttendance(attendance_id, employee_id ,date,in_time,out_time,attendance);

        try {
            boolean isUpdated = employeeAttendanceBO.updateEmployeeAttendance(new EmployeeAttendanceDTO(attendance_id,employee_id,date,in_time,out_time,attendance));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "updated!").show();
                loadAllAttendance();
            }
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void EmployeeAttendanceSearchOnAction(ActionEvent event) {
        String  employee_id = EmployeeIdCMB.getValue();

        try {
            EmployeeAttendance employeeAttendance = employeeAttendanceBO.searchEmployeeAttendance( employee_id );

            if (employeeAttendance != null) {
                AttendanceIdTextField.setText(employeeAttendance.getAttendance_id());
                EmployeeIdCMB.setValue(employeeAttendance.getEmployee_id());
                DateLable.setText(employeeAttendance.getDate());
                InTimeTextField.setText(employeeAttendance.getIn_time());
                OutTimeTextField.setText(employeeAttendance.getOut_time());
                AtenndanceCMB.setValue(employeeAttendance.getAttendance());
            }
        } catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green;  -fx-background-radius: 5; -fx-background-radius: 5");
    }


    @FXML
    void EattendIdKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(AT)([A-z0-9.]){1,}$");
        if (!idPattern.matcher(AttendanceIdTextField.getText()).matches()) {
            addError(AttendanceIdTextField);
            saveButton.setDisable(true);
        }else{
            removeError(AttendanceIdTextField);
            saveButton.setDisable(false);
        }
    }

    public void EattendKeyReleased(KeyEvent keyEvent) {
    }
}



