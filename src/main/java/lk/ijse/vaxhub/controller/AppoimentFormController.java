package lk.ijse.vaxhub.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.vaxhub.bo.BOFactory;
import lk.ijse.vaxhub.bo.custom.AppoimentBO;
import lk.ijse.vaxhub.bo.custom.EmployeeBO;
import lk.ijse.vaxhub.bo.custom.PatientBO;
import lk.ijse.vaxhub.dao.CrudDAO;
import lk.ijse.vaxhub.dao.custom.AppoimentDAO;
import lk.ijse.vaxhub.dao.custom.EmployeeDAO;
import lk.ijse.vaxhub.dao.custom.Impl.AppoimentDAOImpl;
import lk.ijse.vaxhub.dto.AppoimentDTO;
import lk.ijse.vaxhub.entity.Appoiment;
import lk.ijse.vaxhub.entity.Employee;
import lk.ijse.vaxhub.entity.Patient;
import lk.ijse.vaxhub.view.tdm.AppoimentTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class AppoimentFormController {

    @FXML
    private TextField ApooimentIdTextField;

    @FXML
    private Label AppoimentDateLable;

    @FXML
    private TableView<AppoimentTm> AppoimentTable;

    @FXML
    private TableColumn<?, ?> AppoimentTimeColumn;

    @FXML
    private Label AppoimentTimeLable;

    @FXML
    private TextField AppoimentTimeTextField;

    @FXML
    private Label DateLable;

    @FXML
    private JFXComboBox<String> EmployeeIdCMB;

    @FXML
    private TableColumn<?, ?> EmployeeIdColumn;

    @FXML
    private Label EmployeeIdLBL;

    @FXML
    private JFXComboBox<String> ParentNICCMB;

    @FXML
    private Label ParentNicLBL;

    @FXML
    private TextField PaymentIdTextField;

    @FXML
    private TableColumn<?, ?> appoimentDateTimeColumn;

    @FXML
    private Label appoimentIdLable;

    @FXML
    private TableColumn<?, ?> appoimentidColumn;

    @FXML
    private JFXButton clearButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private Label patientIdLable1;

    @FXML
    private TableColumn<?, ?> patientidColumn;

    @FXML
    private TableColumn<?, ?> paymentColumn;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton updateButton;
    //private ObservableList<AppoimentTm> appoimentTm = FXCollections.observableArrayList();
    private List<AppoimentDTO> appoimentList = new ArrayList<>();
    AppoimentBO appoimentBO  = (AppoimentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Appoiment);
    EmployeeBO employeeBO  = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Employee);
    PatientBO patientBO  = (PatientBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Patient);



    public void initialize() throws SQLException, ClassNotFoundException {
        this.appoimentList = getAllAppoiment();
        setCellDataFactory();
        loadAllAppoiment();
        setDate();
        getEmployeeId();
        getParentId();
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

    @FXML
    void ParentNICCMBOnAction(ActionEvent event) {
        String id = ParentNICCMB.getValue();
        try {
            Patient patient = patientBO.searchPatient(id);
            if (patient != null) {
                ParentNICCMB.setValue(patient.getPatient_id());

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    private void getEmployeeId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> IdList = employeeBO.getIds();

            for (String id : IdList) {
                obList.add(id);
            }
            EmployeeIdCMB.setItems(obList);


        }  catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }




    private void getParentId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> pIdList = patientBO.getPIds();

            for (String id : pIdList) {
                obList.add(id);
            }
            ParentNICCMB.setItems(obList);


        }  catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        DateLable.setText(String.valueOf(now));
    }

    //   private List<Appoiment> appoimentList = new ArrayList<>();
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    private void setCellDataFactory() {
        appoimentidColumn.setCellValueFactory(new PropertyValueFactory<>("appoiment_id"));
        EmployeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        patientidColumn.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        appoimentDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("appoiment_date"));
        AppoimentTimeColumn.setCellValueFactory(new PropertyValueFactory<>("appoiment_time"));

    }








    private void loadAllAppoiment() {
        ObservableList<AppoimentTm> tmList = FXCollections.observableArrayList();

        for (AppoimentDTO appoiment : appoimentList) {
            AppoimentTm appoimentTm = new AppoimentTm(
                    appoiment.getAppoiment_id(),
                    appoiment.getEmployee_id(),
                    appoiment.getPatient_id(),
                    appoiment.getAppoiment_date(),
                    appoiment.getAppoiment_time()
            );
            tmList.add(appoimentTm);
        }
        AppoimentTable.setItems(tmList);
        AppoimentTm selectedItem = AppoimentTable.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);


    }


    private List<AppoimentDTO> getAllAppoiment() {
        ArrayList<AppoimentDTO> appoimentList = null;
        try {
            appoimentList = appoimentBO.getAllAppoiment();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return appoimentList;
    }

    @FXML
    void SaveButtonOnAction() throws SQLException {
        String appoiment_id = ApooimentIdTextField.getText();
        String employee_id = EmployeeIdCMB.getValue();
        String patient_id = ParentNICCMB.getValue();
        String appoiment_date = DateLable.getText();
        String appoiment_time = AppoimentTimeTextField.getText();
        if (appoiment_id.trim().length() == 0 || employee_id.trim().length() == 0 || patient_id.trim().length() == 0 || appoiment_date.trim().length() == 0 || appoiment_time.trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }



        Appoiment appoiment= new Appoiment(appoiment_id,employee_id,patient_id,appoiment_date,appoiment_time);
        try {
            boolean isSaved = appoimentBO.saveAppoiment(new AppoimentDTO(appoiment.getAppoiment_id(), appoiment.getEmployee_id(), appoiment.getPatient_id(), appoiment.getAppoiment_date(), appoiment.getAppoiment_time()));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appoiment saved!").show();
                appoimentList.add(new AppoimentDTO(appoiment.getAppoiment_id(), appoiment.getEmployee_id(), appoiment.getPatient_id(), appoiment.getAppoiment_date(), appoiment.getAppoiment_time()));

                loadAllAppoiment();
                clearFields();
            }else {
                new Alert(Alert.AlertType.ERROR, "Try Again!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void ClearButtonOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void DeleteButtonOnAction(ActionEvent event) {
        String appoiment_id = ApooimentIdTextField.getText();

        try {
            boolean isDeleted = appoimentBO.deleteAppoiment(appoiment_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                loadAllAppoiment();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void UpdateButtonOnAction(ActionEvent event) {
        String appoiment_id = ApooimentIdTextField.getText();
        String employee_id = EmployeeIdCMB.getValue();
        String patient_id = ParentNICCMB.getValue();
        String appoiment_date = DateLable.getText();
        String appoiment_time = AppoimentTimeTextField.getText();

        Appoiment appoiment = new Appoiment(appoiment_id, employee_id, patient_id, appoiment_date, appoiment_time);

        try {
            boolean isUpdated = appoimentBO.updateAppoiment(new AppoimentDTO(appoiment_id, employee_id, patient_id, appoiment_date, appoiment_time));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "updated!").show();
                loadAllAppoiment();
            }
        }  catch (SQLException | ClassNotFoundException e)  {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    @FXML
    void PatientIdSearchOnAction(ActionEvent event) {
        String patient_id = ParentNICCMB.getValue();

        System.out.println(patient_id);
        try {
            Appoiment appoiment = appoimentBO.searchAppoiment(patient_id);

            if (appoiment != null) {
                ApooimentIdTextField.setText(appoiment.getAppoiment_id());
                EmployeeIdCMB.setValue(appoiment.getEmployee_id());
                ParentNICCMB.setValue(appoiment.getPatient_id());
                DateLable.setText(appoiment.getAppoiment_date());
                AppoimentTimeTextField.setText(appoiment.getAppoiment_time());

            }
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        ApooimentIdTextField.setText("");
        EmployeeIdCMB.setValue("");
        ParentNICCMB.setValue("");
        DateLable.setText("");
        AppoimentTimeTextField.setText("");
    }

    public void AppoimentSearchOnAction(ActionEvent event) {

    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green;  -fx-background-radius: 5; -fx-background-radius: 5");
    }


    @FXML
    void APidOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(AP)([A-z0-9]){1,}$");
        if (!idPattern.matcher(ApooimentIdTextField.getText()).matches()) {
            addError(ApooimentIdTextField);
            saveButton.setDisable(true);
        }else{
            removeError(ApooimentIdTextField);
            saveButton.setDisable(false);
        }
    }










}