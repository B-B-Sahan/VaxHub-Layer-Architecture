package lk.ijse.vaxhub.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.vaxhub.bo.BOFactory;
import lk.ijse.vaxhub.bo.custom.EmployeeBO;
import lk.ijse.vaxhub.dto.EmployeeDTO;
import lk.ijse.vaxhub.entity.Employee;
import lk.ijse.vaxhub.util.ValidationUtil;
import lk.ijse.vaxhub.view.tdm.EmployeeTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeeFormController {

    @FXML
    private JFXButton BackEmployeeAttendanceButton;

    @FXML
    private TextField EmloyeeContactNumberTextField;

    @FXML
    private TableColumn<EmployeeTm, String> EmloyeeFirstNameColumn;

    @FXML
    private TextField EmloyeeFirstNameTextField;

    @FXML
    private TableColumn<EmployeeTm, String> EmployeeAddressColumn;

    @FXML
    private TextField EmployeeAddressTextField;

    @FXML
    private TableColumn<EmployeeTm, String> EmployeeContactNumberColumn;

    @FXML
    private TableColumn<EmployeeTm, String> EmployeeEmailColumn;

    @FXML
    private TextField EmployeeEmailTextField;

    @FXML
    private TableColumn<EmployeeTm, String> EmployeeIdColumn;

    @FXML
    private TableColumn<EmployeeTm, String> EmployeeLastNameColumn;

    @FXML
    private TextField EmployeeLastNameTextField;

    @FXML
    private TableView<EmployeeTm> EmployeeManageTable;

    @FXML
    private JFXComboBox<String> JobRoleCMB;

    @FXML
    private TableColumn<EmployeeTm, String> RoleColumn;

    @FXML
    private JFXButton clearButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private TextField employeeidTextField;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton updateButton;

    private List<EmployeeDTO> employeeList = new ArrayList<>();
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Employee);

    public void initialize() {
        this.employeeList = getAllEmployee();
        setCellDataFactory();
        loadAllEmployee();
        setJobRole();
    }

    private void setJobRole() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Public Health Officer");
        obList.add("Vaccine Clinic Manager");
        obList.add("Vaccine Distribution Coordinator");
        obList.add("Clinical Research Coordinator");
        obList.add("Data Entry Specialist");
        obList.add("Logistics Coordinator");
        obList.add("Vaccination Administer");
        obList.add("Customer Service Representative");
        obList.add("Security Officer");
        obList.add("Registered Nurse (RN)");
        obList.add("Nurse Practitioner");
        obList.add("Pharmacist");
        obList.add("Pharmacy Technician");
        obList.add("Medical Assistant");
        obList.add("Emergency Medical Technician");

        JobRoleCMB.setItems(obList);
    }

    private void loadAllEmployee() {
        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();

        for (EmployeeDTO employee : employeeList) {
            EmployeeTm employeeTm = new EmployeeTm(
                    employee.getEmployee_id(),
                    employee.getFirst_name(),
                    employee.getLast_name(),
                    employee.getRole(),
                    employee.getEmail(),
                    employee.getAddress(),
                    employee.getContact_number()
            );
            tmList.add(employeeTm);
        }

        EmployeeManageTable.setItems(tmList);
    }

    private List<EmployeeDTO> getAllEmployee() {
        List<EmployeeDTO> employeeList = null;
        try {
            employeeList = employeeBO.getAllEmployee();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    private void setCellDataFactory() {
        EmployeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        EmloyeeFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        EmployeeLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        RoleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        EmployeeEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        EmployeeAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        EmployeeContactNumberColumn.setCellValueFactory(new PropertyValueFactory<>("contact_number"));
    }
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    @FXML
    void EKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object respond = ValidationUtil.validation(map);
            if (respond instanceof TextField) {
                TextField textField = (TextField) respond;
                textField.requestFocus();
            } else {
                SaveButtonOnAction();
            }
        }
    }

    @FXML
    void BackEmployeeAttendanceButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/employeeattendance_form.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registerPane);
    }

    @FXML
    void ClearButtonOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        employeeidTextField.setText("");
        EmloyeeFirstNameTextField.setText("");
        EmployeeLastNameTextField.setText("");
        JobRoleCMB.setValue("");
        EmployeeEmailTextField.setText("");
        EmployeeAddressTextField.setText("");
        EmloyeeContactNumberTextField.setText("");
    }

    @FXML
    void DeleteButtonOnAction(ActionEvent event) {
        String employee_id = employeeidTextField.getText();

        try {
            boolean isDeleted = employeeBO.deleteEmployee(employee_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
                loadAllEmployee();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void SaveButtonOnAction() {
        String employee_id = employeeidTextField.getText();
        String first_name = EmloyeeFirstNameTextField.getText();
        String last_name = EmployeeLastNameTextField.getText();
        String role = JobRoleCMB.getValue();
        String email = EmployeeEmailTextField.getText();
        String address = EmployeeAddressTextField.getText();
        String contact_number = EmloyeeContactNumberTextField.getText();

        if (employee_id.trim().isEmpty() || first_name.trim().isEmpty() || last_name.trim().isEmpty() ||
                role == null || email.trim().isEmpty() || address.trim().isEmpty() || contact_number.trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO(employee_id, first_name, last_name, role, email, address, contact_number);

        try {
            boolean isSaved = employeeBO.saveEmployee(employeeDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                loadAllEmployee();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void UpdateButtonOnAction(ActionEvent event) {
        String employee_id = employeeidTextField.getText();
        String first_name = EmloyeeFirstNameTextField.getText();
        String last_name = EmployeeLastNameTextField.getText();
        String role = JobRoleCMB.getValue();
        String email = EmployeeEmailTextField.getText();
        String address = EmployeeAddressTextField.getText();
        String contact_number = EmloyeeContactNumberTextField.getText();

        EmployeeDTO employeeDTO = new EmployeeDTO(employee_id, first_name, last_name, role, email, address, contact_number);

        try {
            boolean isUpdated = employeeBO.updateEmployee(employeeDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                loadAllEmployee();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void employeeSearchOnAction(ActionEvent event) {
        String employee_id = employeeidTextField.getText();

        try {
            Employee employeeDTO = employeeBO.searchEmployee(employee_id);

            if (employeeDTO != null) {
                employeeidTextField.setText(employeeDTO.getEmployee_id());
                EmloyeeFirstNameTextField.setText(employeeDTO.getFirst_name());
                EmployeeLastNameTextField.setText(employeeDTO.getLast_name());
                JobRoleCMB.setValue(employeeDTO.getRole());
                EmployeeEmailTextField.setText(employeeDTO.getEmail());
                EmployeeAddressTextField.setText(employeeDTO.getAddress());
                EmloyeeContactNumberTextField.setText(employeeDTO.getContact_number());
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red; -fx-background-radius: 5;");
    }

    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green; -fx-background-radius: 5;");
    }

    @FXML
    void EAdressKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^([A-z0-9]|[-/,.@+]|\\\\s){4,}$");
        if (!idPattern.matcher(EmployeeAddressTextField.getText()).matches()) {
            addError(EmployeeAddressTextField);
            saveButton.setDisable(true);
        } else {
            removeError(EmployeeAddressTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void EConKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^[0-9]{10}$");
        if (!idPattern.matcher(EmloyeeContactNumberTextField.getText()).matches()) {
            addError(EmloyeeContactNumberTextField);
            saveButton.setDisable(true);
        } else {
            removeError(EmloyeeContactNumberTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void EFNKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z ]*$");
        if (!idPattern.matcher(EmloyeeFirstNameTextField.getText()).matches()) {
            addError(EmloyeeFirstNameTextField);
            saveButton.setDisable(true);
        } else {
            removeError(EmloyeeFirstNameTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void EIDKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(E)([A-z0-9.]){1,}$");
        if (!idPattern.matcher(employeeidTextField.getText()).matches()) {
            addError(employeeidTextField);
            saveButton.setDisable(true);
        } else {
            removeError(employeeidTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void ELNKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z ]*$");
        if (!idPattern.matcher(EmployeeLastNameTextField.getText()).matches()) {
            addError(EmployeeLastNameTextField);
            saveButton.setDisable(true);
        } else {
            removeError(EmployeeLastNameTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void EemailKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$");
        if (!idPattern.matcher(EmployeeEmailTextField.getText()).matches()) {
            addError(EmployeeEmailTextField);
            saveButton.setDisable(true);
        } else {
            removeError(EmployeeEmailTextField);
            saveButton.setDisable(false);
        }
    }
}
