package lk.ijse.vaxhub.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.vaxhub.model.Patient;
import lk.ijse.vaxhub.model.Register;
import lk.ijse.vaxhub.repository.PatientRepo;
import lk.ijse.vaxhub.repository.RegisterRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PatientFormController {

    @FXML
    private Label ADreactLable;

    @FXML
    private Label DateAdminLable;

    @FXML
    private DatePicker DatePicker;

    @FXML
    private JFXComboBox<String> GenderCMB;

    @FXML
    private Label ParentNicLBL;

    @FXML
    private TextField PatientAddressTextField;

    @FXML
    private TextField PatientContactNumberTextField;

    @FXML
    private TextField PatientEmailTextField;

    @FXML
    private TextField PatientFirstNameTextField;

    @FXML
    private TextField PatientHieghtTextField;

    @FXML
    private TextField PatientIdTextField;

    @FXML
    private TextField PatientLastNameTextField;

    @FXML
    private TextField PatientWeightTextField;

    @FXML
    private JFXComboBox<String> RegisterCMB;

    @FXML
    private JFXButton VaccinationButton;

    @FXML
    private JFXButton clearButton;

    @FXML
    private Label dateADLable;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private Label patientBDLable;

    @FXML
    private Label patientConNumLable;

    @FXML
    private Label patientFnAameLable;

    @FXML
    private Label patientGenderLable;

    @FXML
    private Label patientIdLable;

    @FXML
    private Label patientLnameLable;

    @FXML
    private JFXComboBox<String> reactionCMB;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXComboBox<String> vaccineNmaeCMB;

    @FXML
    private Hyperlink viewTableHyperLink;



    private List<Patient> patientList = new ArrayList<>();

    public void initialize() {
        
        setDate();
        setGender();
        setAdverseReaction();
        setVaccineName();
        getRegisterId();
    }

    private void getRegisterId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> pIdList = RegisterRepo.getRIds();

            for (String id : pIdList) {
                obList.add(id);
            }
            RegisterCMB.setItems(obList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void RegisterCMBOnAction(ActionEvent event) {
        String id = RegisterCMB.getValue();
        try {
            Register register = RegisterRepo.searchById(id);
            if (register != null) {
                RegisterCMB.setValue(register.getRegister_id());

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

    private void setAdverseReaction() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Good");
        obList.add("Bad");
        obList.add("None");
        reactionCMB.setItems(obList);
    }

    private void setGender() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Male");
        obList.add("Female");
        GenderCMB.setItems(obList);
    }


    private void setDate() {
        LocalDate now = LocalDate.now();
        DateAdminLable.setText(String.valueOf(now));
    }


    @FXML
    void ClearButtonOnAction(ActionEvent event) {clearFields();}

    private void clearFields() {
        PatientIdTextField.setText("");
        RegisterCMB.setValue(" ");
        PatientFirstNameTextField.setText("");
        PatientLastNameTextField.setText("");
        PatientEmailTextField.setText("");
        PatientAddressTextField.setText("");
        PatientContactNumberTextField.setText("");


        }


    @FXML
    void DeleteButtonOnAction(ActionEvent event)throws SQLException {
        String patient_id = PatientIdTextField.getText();

        try {
            boolean isDeleted = PatientRepo.delete(patient_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
}



    @FXML
    void SaveButtonOnAction(ActionEvent event) throws SQLException {
        String patient_id = PatientIdTextField.getText();
        String register_id = RegisterCMB.getValue();
        String first_name = PatientFirstNameTextField.getText();
        String last_name = PatientLastNameTextField.getText();
        String gender = GenderCMB.getValue();
        String birth_day = DatePicker.getValue().toString();
        String email = PatientEmailTextField.getText();
        String address = PatientAddressTextField.getText();
        String contact_number = PatientContactNumberTextField.getText();
        String date_administer = DateAdminLable.getText();
        String adverse_reaction = reactionCMB.getValue();
        String vaccine_name = vaccineNmaeCMB.getValue();
        String weight_value = PatientWeightTextField.getText();
        String hight_value = PatientHieghtTextField.getText();




       Patient patient = new Patient(patient_id,register_id, first_name,last_name,gender,birth_day,email, address, contact_number, date_administer, adverse_reaction,vaccine_name,weight_value,hight_value);

        try {
            boolean isSaved = PatientRepo.save(patient);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "patient saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    @FXML
    void UpdateButtonOnAction(ActionEvent event) throws SQLException {
        String patient_id = PatientIdTextField.getText();
        String register_id = RegisterCMB.getValue();
        String first_name = PatientFirstNameTextField.getText();
        String last_name = PatientLastNameTextField.getText();
        String gender = GenderCMB.getValue();
        String birth_day = DatePicker.getValue().toString();
        String email = PatientEmailTextField.getText();
        String address = PatientAddressTextField.getText();
        String contact_number = PatientContactNumberTextField.getText();
        String date_administer = DateAdminLable.getText();
        String adverse_reaction = reactionCMB.getValue();
        String vaccine_name = vaccineNmaeCMB.getValue();
        String weight_value = PatientWeightTextField.getText();
        String hight_value = PatientHieghtTextField.getText();

        Patient patient = new Patient(patient_id,register_id, first_name,last_name,gender,birth_day,email, address, contact_number, date_administer, adverse_reaction,vaccine_name,weight_value,hight_value);

        try {
            boolean isUpdated = PatientRepo.update(patient);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "patient updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    public void PTextSearchOnAction(ActionEvent event) throws SQLException {
        String  patient_id = PatientIdTextField.getText();

        try {
            Patient patient = PatientRepo.searchById( patient_id);

            if (patient != null) {
                PatientIdTextField.setText(patient.getPatient_id());
                RegisterCMB.setValue(patient.getRegister_id());
                PatientFirstNameTextField.setText(patient.getFirst_name());
                PatientLastNameTextField.setText(patient.getLast_name());
                GenderCMB.setValue(patient.getGender());
                DatePicker.getValue().toString();
                PatientEmailTextField.setText(patient.getEmail());
                PatientAddressTextField.setText(patient.getAddress());
                PatientContactNumberTextField.setText(patient.getContact_number());
                DateAdminLable.setText(patient.getDate_administer());
                reactionCMB.setValue(patient.getAdverse_reaction());
                vaccineNmaeCMB.setValue(patient.getVaccine_name());
                PatientWeightTextField.setText(patient.getWeight_value());
                PatientHieghtTextField.setText(patient.getHight_value());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    @FXML
    void VaccinationButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/vaccination_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }










    @FXML
    void viewTableHyperLinkOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/patientTable_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green;  -fx-background-radius: 5; -fx-background-radius: 5");
    }


    @FXML
    void PAdressKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^([A-z0-9]|[-/,.@+]|\\\\s){4,}$");
        if (!idPattern.matcher(PatientAddressTextField.getText()).matches()) {
            addError(PatientAddressTextField);
            saveButton.setDisable(true);
        }else{
            removeError(PatientAddressTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void PEmailKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$");
        if (!idPattern.matcher(PatientEmailTextField.getText()).matches()) {
            addError(PatientEmailTextField);
            saveButton.setDisable(true);
        }else{
            removeError(PatientEmailTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void PFnameKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z ]*$");
        if (!idPattern.matcher(PatientFirstNameTextField.getText()).matches()) {
            addError(PatientFirstNameTextField);
            saveButton.setDisable(true);
        }else{
            removeError(PatientFirstNameTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void PHieghtKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z 0-9]*$");
        if (!idPattern.matcher(PatientHieghtTextField.getText()).matches()) {
            addError(PatientHieghtTextField);
            saveButton.setDisable(true);
        }else{
            removeError(PatientHieghtTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void PIdKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^([A-z0-9]){1,12}$");
        if (!idPattern.matcher(PatientIdTextField.getText()).matches()) {
            addError(PatientIdTextField);
            saveButton.setDisable(true);
        }else{
            removeError(PatientIdTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void PLnamekeyrealased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z ]*$");
        if (!idPattern.matcher(PatientLastNameTextField.getText()).matches()) {
            addError(PatientLastNameTextField);
            saveButton.setDisable(true);
        }else{
            removeError(PatientLastNameTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void PchildweightKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z 0-9]*$");
        if (!idPattern.matcher(PatientWeightTextField.getText()).matches()) {
            addError(PatientWeightTextField);
            saveButton.setDisable(true);
        }else{
            removeError(PatientWeightTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void PconKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^[0-9]{10}$");
        if (!idPattern.matcher(PatientContactNumberTextField.getText()).matches()) {
            addError(PatientContactNumberTextField);
            saveButton.setDisable(true);
        }else{
            removeError(PatientContactNumberTextField);
            saveButton.setDisable(false);
        }
    }



}