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
import lk.ijse.vaxhub.model.Employee;
import lk.ijse.vaxhub.model.Tm.VaccineTm;
import lk.ijse.vaxhub.model.Vaccine;
import lk.ijse.vaxhub.repository.EmployeeRepo;
import lk.ijse.vaxhub.repository.VaccineRepo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class VaccineFormController {

    @FXML
    private Label DateLable;

    @FXML
    private JFXComboBox<String> EmployeeIdCMB;

    @FXML
    private TableColumn<?, ?> EmployeeIdColumn;

    @FXML
    private Label EmployeeIdLBL;

    @FXML
    private TableColumn<?, ?> ManufactureColumn;

    @FXML
    private TextField ManufactureTextField;

    @FXML
    private TableColumn<?, ?> QuantityColumn;

    @FXML
    private TextField QuantityTextField;

    @FXML
    private TableView<VaccineTm> VaccinationTable;

    @FXML
    private TableColumn<?, ?> VaccineDateColumn;

    @FXML
    private TableColumn<?, ?> VaccineIdColumn;

    @FXML
    private TextField VaccineIdTextField;

    @FXML
    private TableColumn<?, ?> VaccineNameColumn;

    @FXML
    private JFXButton clearButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXComboBox<String> vaccineNmaeCMB;




    private List<Vaccine> vaccineList = new ArrayList<>();
    public void initialize() {
        this.vaccineList = getAllVaccine();
        setCellDataFactory();
        loadAllVaccine();
        setDate();
        setVaccineName();
        getEmployeeId();
    }

    private void getEmployeeId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> IdList = EmployeeRepo.getIds();

            for (String id : IdList) {
                obList.add(id);
            }
            EmployeeIdCMB.setItems(obList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void EmployeeIdCMBOnAction(ActionEvent event) {
        String id = EmployeeIdCMB.getValue();
        try {
            Employee employee = EmployeeRepo.searchById(id);
            if (employee != null) {
                EmployeeIdLBL.setText(employee.getEmployee_id());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setVaccineName() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("B.C.G");
        obList.add("DPT 1");
        obList.add("DPT 2");
        obList.add("DPT 3");
        obList.add("DPT 4");
        obList.add("OPV 1");
        obList.add("OPV 2");
        obList.add("OPV 3");
        obList.add("OPV 4");
        obList.add("OPV 5");
        obList.add("Measles");
        obList.add("Vitamin A");
        obList.add("Rubella");
        obList.add("D.T");
        obList.add("atd");
        obList.add("JE 1");
        obList.add("JE 2");
        obList.add("JE 3");
        obList.add("JE 4");
        obList.add("HPV");
        vaccineNmaeCMB.setItems(obList);
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        DateLable.setText(String.valueOf(now));
    }

    private void loadAllVaccine() {
        ObservableList<VaccineTm> tmList = FXCollections.observableArrayList();

        for (Vaccine vaccine : vaccineList) {
            VaccineTm vaccineTm = new VaccineTm(
                    vaccine.getVaccine_id(),
                    vaccine.getEmployee_id(),
                    vaccine.getVaccine_name(),
                    vaccine.getVaccine_date(),
                    vaccine.getManufacture(),
                    vaccine.getQuantity()
            );
            tmList.add(vaccineTm);

        }

        VaccinationTable.setItems(tmList);
        VaccineTm selectedItem = VaccinationTable.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);


    }

    private void setCellDataFactory() {
        VaccineIdColumn.setCellValueFactory(new PropertyValueFactory<>("vaccine_id"));
        EmployeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        VaccineNameColumn.setCellValueFactory(new PropertyValueFactory<>("vaccine_name"));
        VaccineDateColumn.setCellValueFactory(new PropertyValueFactory<>("vaccine_date"));
        ManufactureColumn.setCellValueFactory(new PropertyValueFactory<>("manufacture"));
        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private List<Vaccine> getAllVaccine() {
        List<Vaccine> vaccineList  = null;
        try {
            vaccineList  = VaccineRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vaccineList;


    }

    @FXML
    void ClearButtonOnAction(ActionEvent event) {clearFields();}

    private void clearFields() {
        VaccineIdTextField.setText("");
        EmployeeIdCMB.setValue("");
        ManufactureTextField.setText("");
        QuantityTextField.setText("");

    }

    @FXML
    void DeleteButtonOnAction(ActionEvent event) {
        String vaccine_id = VaccineIdTextField.getText();

        try {
            boolean isDeleted = VaccineRepo.delete(vaccine_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                loadAllVaccine();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void SaveButtonOnAction(ActionEvent event) {
        String vaccine_id = VaccineIdTextField.getText();
        String employee_id =  EmployeeIdCMB.getValue();
        String vaccine_name = vaccineNmaeCMB.getValue();
        String vaccine_date = DateLable.getText();
        String manufacture = ManufactureTextField.getText();
        String quantity = QuantityTextField.getText();


        Vaccine vaccine = new Vaccine(vaccine_id,employee_id,vaccine_name,vaccine_date,manufacture,quantity);

        try {
            boolean isSaved = VaccineRepo.save(vaccine);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "saved!").show();
                loadAllVaccine();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void UpdateButtonOnAction(ActionEvent event) {
        String vaccine_id = VaccineIdTextField.getText();
        String employee_id =  EmployeeIdCMB.getValue();
        String vaccine_name = vaccineNmaeCMB.getValue();
        String vaccine_date = DateLable.getText();
        String manufacture = ManufactureTextField.getText();
        String quantity = QuantityTextField.getText();

        Vaccine vaccine = new Vaccine(vaccine_id,employee_id,vaccine_name,vaccine_date,manufacture,quantity);


        try {
            boolean isUpdated = VaccineRepo.update(vaccine);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " updated!").show();
                loadAllVaccine();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void VaccineNameOnAction(ActionEvent event) {
        String  vaccine_name = vaccineNmaeCMB.getValue();

        try {
            Vaccine vaccine = VaccineRepo.searchById( vaccine_name);

            if (vaccine != null) {
                VaccineIdTextField.setText(vaccine.getVaccine_id());
                EmployeeIdCMB.setValue(vaccine.getEmployee_id());
                vaccineNmaeCMB.setValue(vaccine.getVaccine_name());
                DateLable.setText(vaccine.getVaccine_date());
                ManufactureTextField.setText(vaccine.getManufacture());
                QuantityTextField.setText(vaccine.getQuantity());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            loadAllVaccine();
        }
    }

    public void Vaccine(ActionEvent event) {
    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green;  -fx-background-radius: 5; -fx-background-radius: 5");
    }



    @FXML
    void VccineIdOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(V)([A-z0-9.]){1,}$");
        if (!idPattern.matcher(VaccineIdTextField.getText()).matches()) {
            addError(VaccineIdTextField);
            saveButton.setDisable(true);
        }else{
            removeError(VaccineIdTextField);
            saveButton.setDisable(false);
        }
    }


    @FXML
    void VccineQTYOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^([A-z0-9.]){1,}$");
        if (!idPattern.matcher(QuantityTextField.getText()).matches()) {
            addError(QuantityTextField);
            saveButton.setDisable(true);
        }else{
            removeError(QuantityTextField);
            saveButton.setDisable(false);
        }
    }





}


