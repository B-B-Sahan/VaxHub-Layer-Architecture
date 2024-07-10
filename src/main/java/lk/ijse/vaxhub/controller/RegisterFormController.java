package lk.ijse.vaxhub.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.vaxhub.bo.BOFactory;
import lk.ijse.vaxhub.bo.custom.RegisterBO;
import lk.ijse.vaxhub.dto.RegisterDTO;
import lk.ijse.vaxhub.view.tdm.RegisterTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegisterFormController {

    @FXML
    private TableColumn<RegisterTm, String> PatientAddressColumn;

    @FXML
    private TextField PatientAddressTextField;

    @FXML
    private TableColumn<RegisterTm, String> PatientCintactNumberColumn;

    @FXML
    private TextField PatientContactNumberTextField;

    @FXML
    private TableColumn<RegisterTm, String> PatientFirstNameColumn;

    @FXML
    private TextField PatientFirstNameTextField;

    @FXML
    private TableColumn<RegisterTm, String> PatientIdColumn;

    @FXML
    private TextField PatientIdTextField;

    @FXML
    private TableColumn<RegisterTm, String> PatientLastNameColumn;

    @FXML
    private TextField PatientLastNameTextField;

    @FXML
    private JFXButton clearButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private TableView<RegisterTm> patientTable;

    @FXML
    private TableColumn<RegisterTm, String> regIdColumn;

    @FXML
    private TextField registerIdTextField;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton updateButton;
    @FXML
    private TextField regAdress;
    private List<RegisterDTO> registerList = new ArrayList<>();
    private RegisterBO registerBO = (RegisterBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Register);

    public void initialize() {
        this.registerList = getAllRegister();
        setCellDataFactory();
        loadAllRegisters();
    }

    private void loadAllRegisters() {
        ObservableList<RegisterTm> tmList = FXCollections.observableArrayList();

        for (RegisterDTO register : registerList) {
            RegisterTm registerTm = new RegisterTm(
                    register.getRegister_id(),
                    register.getFirst_name(),
                    register.getLast_name(),
                    register.getAddress(),
                    register.getContact_number()
            );
            tmList.add(registerTm);
        }
        patientTable.setItems(tmList);
    }

    private void setCellDataFactory() {
        regIdColumn.setCellValueFactory(new PropertyValueFactory<>("register_id"));
        PatientFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        PatientLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        PatientAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        PatientCintactNumberColumn.setCellValueFactory(new PropertyValueFactory<>("contact_number"));
    }

    private List<RegisterDTO> getAllRegister() {
        try {
            return registerBO.getAllRegister();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            return new ArrayList<>();
        }
    }

    @FXML
    void ClearButtonOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        registerIdTextField.setText("");
        PatientFirstNameTextField.setText("");
        PatientLastNameTextField.setText("");
        PatientAddressTextField.setText("");
        PatientContactNumberTextField.setText("");
    }

    @FXML
    void DeleteButtonOnAction(ActionEvent event) {
        String register_id = registerIdTextField.getText();

        try {
            boolean isDeleted = registerBO.deleteRegister(register_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
                loadAllRegisters();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void SaveButtonOnAction(ActionEvent event) {
        String register_id = registerIdTextField.getText();
        String first_name = PatientFirstNameTextField.getText();
        String last_name = PatientLastNameTextField.getText();
        String address = PatientAddressTextField.getText();
        String contact_number = PatientContactNumberTextField.getText();

        try {
            boolean isSaved = registerBO.saveRegister(new RegisterDTO(register_id, first_name, last_name, address, contact_number));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                loadAllRegisters();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void UpdateButtonOnAction(ActionEvent event) {
        String register_id = registerIdTextField.getText();
        String first_name = PatientFirstNameTextField.getText();
        String last_name = PatientLastNameTextField.getText();
        String address = PatientAddressTextField.getText();
        String contact_number = PatientContactNumberTextField.getText();

        try {
            boolean isUpdated = registerBO.updateRegister(new RegisterDTO(register_id, first_name, last_name, address, contact_number));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                loadAllRegisters();
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
    void regAddressOnKeyReleased(KeyEvent event) {
        Pattern pattern = Pattern.compile("^([A-Za-z0-9]|[-/,.@+]|\\s){4,}$");
        if (!pattern.matcher(PatientAddressTextField.getText()).matches()) {
            addError(PatientAddressTextField);
            saveButton.setDisable(true);
        } else {
            removeError(PatientAddressTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void regFNOnKeyReleased(KeyEvent event) {
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        if (!pattern.matcher(PatientFirstNameTextField.getText()).matches()) {
            addError(PatientFirstNameTextField);
            saveButton.setDisable(true);
        } else {
            removeError(PatientFirstNameTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void regIdOnKeyReleased(KeyEvent event) {
        Pattern pattern = Pattern.compile("^(REG)([A-Za-z0-9.]){1,}$");
        if (!pattern.matcher(registerIdTextField.getText()).matches()) {
            addError(registerIdTextField);
            saveButton.setDisable(true);
        } else {
            removeError(registerIdTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void regLNOnKeyReleased(KeyEvent event) {
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        if (!pattern.matcher(PatientLastNameTextField.getText()).matches()) {
            addError(PatientLastNameTextField);
            saveButton.setDisable(true);
        } else {
            removeError(PatientLastNameTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void regConOnKeyReleased(KeyEvent event) {
        Pattern pattern = Pattern.compile("^[0-9]{10}$");
        if (!pattern.matcher(PatientContactNumberTextField.getText()).matches()) {
            addError(PatientContactNumberTextField);
            saveButton.setDisable(true);
        } else {
            removeError(PatientContactNumberTextField);
            saveButton.setDisable(false);
        }
    }

    public void RegChildSearchOnAction(ActionEvent event) {
    }

    public void regAdressOnKeyReleased(KeyEvent keyEvent) {
    }

    public void regconOnKeyReleased(KeyEvent keyEvent) {
    }
}
