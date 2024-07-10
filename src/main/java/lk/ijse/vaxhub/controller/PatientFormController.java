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
import lk.ijse.vaxhub.bo.BOFactory;
import lk.ijse.vaxhub.bo.custom.PatientBO;
import lk.ijse.vaxhub.bo.custom.RegisterBO;
import lk.ijse.vaxhub.dto.PatientDTO;
import lk.ijse.vaxhub.entity.Patient;
import lk.ijse.vaxhub.entity.Register;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class PatientFormController {

    @FXML
    private Label ADreactLable, DateAdminLable, ParentNicLBL, dateADLable, patientBDLable, patientConNumLable,
            patientFnAameLable, patientGenderLable, patientIdLable, patientLnameLable;

    @FXML
    private DatePicker DatePicker;

    @FXML
    private JFXComboBox<String> GenderCMB, RegisterCMB, reactionCMB, vaccineNmaeCMB;

    @FXML
    private TextField PatientAddressTextField, PatientContactNumberTextField, PatientEmailTextField,
            PatientFirstNameTextField, PatientHieghtTextField, PatientIdTextField,
            PatientLastNameTextField, PatientWeightTextField;

    @FXML
    private JFXButton VaccinationButton, clearButton, deleteButton, saveButton, updateButton;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private Hyperlink viewTableHyperLink;

    private PatientBO patientBO = (PatientBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Patient);
    private RegisterBO registerBO = (RegisterBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Register);

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
            List<String> pIdList = registerBO.getRIds();

          for(String id:pIdList)  {
              obList.add(id);
          }
            RegisterCMB.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void RegisterCMBOnAction(ActionEvent event) {
        String id = RegisterCMB.getValue();
        try {
            Register register = registerBO.searchRegister(id);
            if (register != null) {
                RegisterCMB.setValue(register.getRegister_id());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setVaccineName() {
        ObservableList<String> obList = FXCollections.observableArrayList(
                "B.C.G", "DPT 1", "DPT 2", "DPT 3", "DPT 4", "OPV 1", "OPV 2",
                "OPV 3", "OPV 4", "OPV 5", "Measles", "Vitamin A", "Rubella",
                "D.T", "atd", "JE 1", "JE 2", "JE 3", "JE 4", "HPV"
        );
        vaccineNmaeCMB.setItems(obList);
    }

    private void setAdverseReaction() {
        ObservableList<String> obList = FXCollections.observableArrayList("Good", "Bad", "None");
        reactionCMB.setItems(obList);
    }

    private void setGender() {
        ObservableList<String> obList = FXCollections.observableArrayList("Male", "Female");
        GenderCMB.setItems(obList);
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        DateAdminLable.setText(String.valueOf(now));
    }

    @FXML
    void ClearButtonOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        PatientIdTextField.clear();
        RegisterCMB.setValue(null);
        PatientFirstNameTextField.clear();
        PatientLastNameTextField.clear();
        PatientEmailTextField.clear();
        PatientAddressTextField.clear();
        PatientContactNumberTextField.clear();
        PatientWeightTextField.clear();
        PatientHieghtTextField.clear();
        GenderCMB.setValue(null);
        reactionCMB.setValue(null);
        vaccineNmaeCMB.setValue(null);
        DatePicker.setValue(null);
    }

    @FXML
    void DeleteButtonOnAction(ActionEvent event) {
        String patient_id = PatientIdTextField.getText();
        try {
            boolean isDeleted = patientBO.deletePatient(patient_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Patient deleted!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void SaveButtonOnAction(ActionEvent event) {
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
        String height_value = PatientHieghtTextField.getText();

        try {
            boolean isSaved = patientBO.savePatient(new PatientDTO(patient_id, register_id, first_name, last_name, gender, birth_day, email, address, contact_number, date_administer, adverse_reaction, vaccine_name, weight_value, height_value));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Patient saved!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void UpdateButtonOnAction(ActionEvent event) {
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
        String height_value = PatientHieghtTextField.getText();

        try {
            boolean isUpdated = patientBO.updatePatient(new PatientDTO(patient_id, register_id, first_name, last_name, gender, birth_day, email, address, contact_number, date_administer, adverse_reaction, vaccine_name, weight_value, height_value));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Patient updated!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void PTextSearchOnAction(ActionEvent event) {
        String patient_id = PatientIdTextField.getText();
        try {
            Patient patient = patientBO.searchPatient(patient_id);
            if (patient != null) {
                PatientIdTextField.setText(patient.getPatient_id());
                RegisterCMB.setValue(patient.getRegister_id());
                PatientFirstNameTextField.setText(patient.getFirst_name());
                PatientLastNameTextField.setText(patient.getLast_name());
                GenderCMB.setValue(patient.getGender());
                DatePicker.setValue(LocalDate.parse(patient.getBirth_day()));
                PatientEmailTextField.setText(patient.getEmail());
                PatientAddressTextField.setText(patient.getAddress());
                PatientContactNumberTextField.setText(patient.getContact_number());
                DateAdminLable.setText(patient.getDate_administer());
                reactionCMB.setValue(patient.getAdverse_reaction());
                vaccineNmaeCMB.setValue(patient.getVaccine_name());
                PatientWeightTextField.setText(patient.getWeight_value());
                PatientHieghtTextField.setText(patient.getHight_value());
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void VaccinationButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/vaccination_form.fxml"));
        Pane registePane = fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }

    @FXML
    void viewTableHyperLinkOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/patientTable_form.fxml"));
        Pane registePane = fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red; -fx-background-radius: 5;");
    }

    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green; -fx-background-radius: 5;");
    }

    @FXML
    void PAdressKeyReleased(KeyEvent event) {
        validateField(PatientAddressTextField, "^([A-z0-9]|[-/,.@+]|\\\\s){4,}$");
    }

    @FXML
    void PEmailKeyReleased(KeyEvent event) {
        validateField(PatientEmailTextField, "^([A-z])([A-z0-9.]{1,})[@]([A-z0-9]{1,10})[.]([A-z]{2,5})$");
    }

    @FXML
    void PFnameKeyReleased(KeyEvent event) {
        validateField(PatientFirstNameTextField, "^[a-zA-Z ]*$");
    }

    @FXML
    void PHieghtKeyReleased(KeyEvent event) {
        validateField(PatientHieghtTextField, "^[a-zA-Z 0-9]*$");
    }

    @FXML
    void PIdKeyReleased(KeyEvent event) {
        validateField(PatientIdTextField, "^([A-z0-9]{1,12})$");
    }

    @FXML
    void PLnamekeyrealased(KeyEvent event) {
        validateField(PatientLastNameTextField, "^[a-zA-Z ]*$");
    }

    @FXML
    void PchildweightKeyReleased(KeyEvent event) {
        validateField(PatientWeightTextField, "^[a-zA-Z 0-9]*$");
    }

    @FXML
    void PconKeyReleased(KeyEvent event) {
        validateField(PatientContactNumberTextField, "^[0-9]{10}$");
    }

    private void validateField(TextField textField, String regex) {
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(textField.getText()).matches()) {
            addError(textField);
            saveButton.setDisable(true);
        } else {
            removeError(textField);
            saveButton.setDisable(false);
        }
    }
}
