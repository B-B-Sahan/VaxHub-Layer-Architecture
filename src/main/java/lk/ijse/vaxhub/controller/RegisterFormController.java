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
import lk.ijse.vaxhub.model.Register;
import lk.ijse.vaxhub.model.Tm.RegisterTm;
import lk.ijse.vaxhub.repository.RegisterRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegisterFormController {

    @FXML
    private TableColumn<?, ?> PatientAddressColumn;

    @FXML
    private TextField PatientAddressTextField;

    @FXML
    private TableColumn<?, ?> PatientCintactNumberColumn;

    @FXML
    private TextField PatientContactNumberTextField;

    @FXML
    private TableColumn<?, ?> PatientFirstNameColumn;

    @FXML
    private TextField PatientFirstNameTextField;

    @FXML
    private TableColumn<?, ?> PatientIdColumn;

    @FXML
    private TextField PatientIdTextField;

    @FXML
    private TableColumn<?, ?> PatientLastNameColumn;

    @FXML
    private TextField PatientLastNameTextField;

    @FXML
    private JFXButton clearButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private Label patientConNumLable;

    @FXML
    private Label patientFnAameLable;

    @FXML
    private Label patientIdLable;

    @FXML
    private Label patientLnameLable;

    @FXML
    private Label patientLnameLable1;

    @FXML
    private TableView<RegisterTm> patientTable;

    @FXML
    private TableColumn<?, ?> regIdColumn;

    @FXML
    private TextField registerIdTextField;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton updateButton;

    private List<Register> registerList = new ArrayList<>();
    public void initialize() {
        this.registerList = getAllRegister();
        setCellDataFactory();
        loadAllRegisters();
    }

    private void loadAllRegisters() {
        ObservableList<RegisterTm> tmList = FXCollections.observableArrayList();

        for (Register register : registerList) {
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
        RegisterTm selectedItem = patientTable.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);


    }



    private void setCellDataFactory() {

        regIdColumn.setCellValueFactory(new PropertyValueFactory<>("register_id"));
        PatientFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        PatientLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        PatientAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        PatientCintactNumberColumn.setCellValueFactory(new PropertyValueFactory<>("contact_number"));


    }

    private List<Register> getAllRegister() {
        List<Register> registerList = null;
        try {
            registerList = RegisterRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return registerList;
    }

    @FXML
    void ClearButtonOnAction(ActionEvent event) {clearFields();}

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
            boolean isDeleted = RegisterRepo.delete(register_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                loadAllRegisters();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void PTextSearchOnAction(ActionEvent event) {

    }

    @FXML
    void RegChildSearchOnAction(ActionEvent event) {
        String  register_id = registerIdTextField.getText();

        try {
            Register register = RegisterRepo.searchById( register_id);

            if (register != null) {
                registerIdTextField.setText(register.getRegister_id());
                PatientFirstNameTextField.setText(register.getFirst_name());
                PatientLastNameTextField.setText(register.getLast_name());
                PatientAddressTextField.setText(register.getAddress());
                PatientContactNumberTextField.setText(register.getContact_number());


            }
        } catch (SQLException e) {
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




        Register register = new Register(register_id, first_name,last_name, address, contact_number);

        try {
            boolean isSaved = RegisterRepo.save(register);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, " saved!").show();
                loadAllRegisters();
            }
        } catch (SQLException e) {
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




        Register register = new Register(register_id,first_name,last_name, address, contact_number);



        try {
            boolean isUpdated = RegisterRepo.update(register);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "updated!").show();
                loadAllRegisters();
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
    void regAdressOnKeyReleased(KeyEvent event) {
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
    void regFNOnKeyReleased(KeyEvent event) {
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
    void regIdOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(REG)([A-z0-9.]){1,}$");
        if (!idPattern.matcher(registerIdTextField.getText()).matches()) {
            addError(registerIdTextField);
            saveButton.setDisable(true);
        }else{
            removeError(registerIdTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void regLNOnKeyReleased(KeyEvent event) {
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
    void regconOnKeyReleased(KeyEvent event) {
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
