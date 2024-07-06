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
import lk.ijse.vaxhub.model.Employee;
import lk.ijse.vaxhub.model.Tm.EmployeeTm;
import lk.ijse.vaxhub.repository.EmployeeRepo;
import lk.ijse.vaxhub.util.ValidationUtil;

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
    private TableColumn<?, ?> EmloyeeFirstNameColumn;

    @FXML
    private TableColumn<?, ?> EmloyeeFirstNameColumn1;

    @FXML
    private TextField EmloyeeFirstNameTextField;

    @FXML
    private Label EmmployeeLatNameLable;

    @FXML
    private TableColumn<?, ?> EmployeeAddressColumn;

    @FXML
    private TextField EmployeeAddressTextField;

    @FXML
    private TableColumn<?, ?> EmployeeContactNumberColumn;

    @FXML
    private TableColumn<?, ?> EmployeeEmailColumn;

    @FXML
    private TextField EmployeeEmailTextField;

    @FXML
    private Label EmployeeFNmaeLable;

    @FXML
    private TableColumn<?, ?> EmployeeIdColumn;

    @FXML
    private Label EmployeeIdLable;

    @FXML
    private Label EmployeeIdLable1;

    @FXML
    private TableColumn<?, ?> EmployeeLastNameColumn;

    @FXML
    private TextField EmployeeLastNameTextField;

    @FXML
    private TableView<EmployeeTm> EmployeeManageTable;

    @FXML
    private Label EmployeeRolLablle;

    @FXML
    private JFXComboBox<String> JobRoleCMB;

    @FXML
    private TableColumn<?, ?> RoleColumn;

    @FXML
    private JFXButton clearButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private TextField employeeidTextField;

    @FXML
    private TextField employeeidTextField1;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton updateButton;
    @FXML
    private TableColumn<?, ?> locationIdColumn;


    private List<Employee> employeeList = new ArrayList<>();

    public void initialize() {
        this.employeeList = getAllEmployee();
        setCellDataFactory();
        loadAllEmployee();
        setJobRole();

    }

    private void setJobRole() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("public health officer");
        obList.add("vaccine clinic manager");
        obList.add("Vaccine Distribution Coordinator");
        obList.add("Clinical Research Coordinator");
        obList.add("data entry specialist");
        obList.add("Logistics Coordinator");
        obList.add("vaccination administer");
        obList.add("customer service representative ");
        obList.add("Security Officer ");
        obList.add("Registered Nurse (RN)");
        obList.add("nurse practitioner");
        obList.add("pharmacist");
        obList.add("pharmacy technician");
        obList.add("medical assistance");
        obList.add("emergency medical technician ");

        JobRoleCMB.setItems(obList);

    }

    private void loadAllEmployee() {
        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();

        for (Employee employee : employeeList) {
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
        EmployeeTm selectedItem = EmployeeManageTable.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);


    }

    private List<Employee> getAllEmployee() {
        List<Employee> employeeList  = null;
        try {
            employeeList  = EmployeeRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }



    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    private void setCellDataFactory() {
        EmployeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        EmloyeeFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        EmployeeLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        RoleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        EmployeeEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        EmployeeAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        EmployeeContactNumberColumn.setCellValueFactory(new PropertyValueFactory<>("contact_number"));



    }


    @FXML
    void EKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object respond =  ValidationUtil.validation(map);
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
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }

    @FXML
    void ClearButtonOnAction(ActionEvent event) {clearFields();}

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
            boolean isDeleted = EmployeeRepo.delete(employee_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                loadAllEmployee();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void SaveButtonOnAction() {
        String employee_id = employeeidTextField.getText();
        String first_name = EmloyeeFirstNameTextField.getText();
        String last_name =  EmployeeLastNameTextField.getText();
        String role = JobRoleCMB.getValue();
        String email = EmployeeEmailTextField.getText();
        String address = EmployeeAddressTextField.getText();
        String contact_number = EmloyeeContactNumberTextField.getText();

        if (employee_id.trim().length() == 0 || first_name.trim().length() == 0 || last_name.trim().length() == 0 || role.trim().length() == 0 || email.trim().length() == 0|| address.trim().length() == 0|| contact_number.trim().length() == 0 ) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }

        Employee employee = new Employee(employee_id,first_name,last_name,role,email,address,contact_number);

        try {
            boolean isSaved = EmployeeRepo.save(employee);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "saved!").show();
                loadAllEmployee();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void UpdateButtonOnAction(ActionEvent event) {
        String employee_id = employeeidTextField.getText();
        String first_name = EmloyeeFirstNameTextField.getText();
        String last_name =  EmployeeLastNameTextField.getText();
        String role = JobRoleCMB.getValue();
        String email = EmployeeEmailTextField.getText();
        String address = EmployeeAddressTextField.getText();
        String contact_number = EmloyeeContactNumberTextField.getText();


        Employee employee = new Employee(employee_id,first_name,last_name,role,email,address,contact_number);

        try {
            boolean isUpdated = EmployeeRepo.update(employee);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " updated!").show();
                loadAllEmployee();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void employeeSearchOnAction(ActionEvent event) {
        String  employee_id = employeeidTextField.getText();

        try {
            Employee employee = EmployeeRepo.searchById( employee_id);

            if (employee != null) {
                employeeidTextField.setText(employee.getEmployee_id());
                EmloyeeFirstNameTextField.setText(employee.getFirst_name());
                EmployeeLastNameTextField.setText(employee.getLast_name());
                JobRoleCMB.setValue(employee.getRole());
                EmployeeEmailTextField.setText(employee.getEmail());
                EmployeeAddressTextField.setText(employee.getAddress());
                EmloyeeContactNumberTextField.setText(employee.getContact_number());

            }
        } catch (SQLException e) {
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
    void EAdressKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^([A-z0-9]|[-/,.@+]|\\\\s){4,}$");
        if (!idPattern.matcher(EmployeeAddressTextField.getText()).matches()) {
            addError(EmployeeAddressTextField);
            saveButton.setDisable(true);
        }else{
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
        }else{
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
        }else{
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
        }else{
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
        }else{
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
        }else{
            removeError(EmployeeEmailTextField);
            saveButton.setDisable(false);
        }
    }




}
