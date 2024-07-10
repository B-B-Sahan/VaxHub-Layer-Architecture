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
import lk.ijse.vaxhub.bo.custom.PatientBO;
import lk.ijse.vaxhub.bo.custom.PaymentBO;
import lk.ijse.vaxhub.bo.custom.RegisterBO;
import lk.ijse.vaxhub.dto.PatientDTO;
import lk.ijse.vaxhub.dto.PaymentDTO;
import lk.ijse.vaxhub.entity.Appoiment;
import lk.ijse.vaxhub.entity.Patient;
import lk.ijse.vaxhub.entity.Payment;
import lk.ijse.vaxhub.view.tdm.PaymentTm;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class PaymentFormController {

    @FXML
    private TableColumn<?, ?> AmountIdColumn;

    @FXML
    private TextField AmountTextField;

    @FXML
    private JFXComboBox<String> AppoimentIdCMB;

    @FXML
    private Label DateAdminLable;

    @FXML
    private TableColumn<?, ?> DateColumn;

    @FXML
    private JFXComboBox<String> ParentNICCMB;

    @FXML
    private Label ParentNicLBL;

    @FXML
    private TableColumn<?, ?> PatientIdColumn;

    @FXML
    private TableColumn<?, ?> PaymentIdColumn;

    @FXML
    private Label PaymentIdLBL;

    @FXML
    private TextField PaymentIdTextField;

    @FXML
    private TableView<PaymentTm> PaymentTable;

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
    private JFXButton saveButton;

    @FXML
    private JFXButton updateButton;

    private List<PaymentDTO> paymentList = new ArrayList<>();
    PatientBO patientBO  = (PatientBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Patient);
    AppoimentBO appoimentBO  = (AppoimentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Appoiment);
    PaymentBO paymentBO  = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Payment);

    @FXML
    void APOnKeyReleased(KeyEvent event) {

    }
    public void initialize() {
        this.paymentList = getAllPayment();
        setCellDataFactory();
        loadAllPayment();
        setDate();
        getParentId();
        getAppoimentId();
    }

    private List<PaymentDTO> getAllPayment() {
        List<PaymentDTO> paymentList  = null;
        try {
            paymentList  = paymentBO.getAllPayment();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return paymentList;

    }

    private void loadAllPayment() {
        ObservableList<PaymentTm> tmList = FXCollections.observableArrayList();

        for (PaymentDTO payment : paymentList) {
            PaymentTm paymentTm = new PaymentTm(

                    payment.getPayment_id(),
                    payment.getAppoiment_id(),
                    payment.getPatient_id(),
                    payment.getAmount(),
                    payment.getPayment_date()


            );
            tmList.add(paymentTm);

        }

        PaymentTable.setItems(tmList);
        PaymentTm selectedItem = PaymentTable.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);

    }

    @FXML
    void AppoimentCMBCMBOnAction(ActionEvent event) {
        String id = AppoimentIdCMB.getValue();
        try {
            Appoiment appoiment = appoimentBO.searchAppoiment(id);
            if (appoiment != null) {
                AppoimentIdCMB.setValue(appoiment.getAppoiment_id());

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }







    private void getAppoimentId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> pIdList = appoimentBO.getAIds();

            for (String id : pIdList) {
                obList.add(id);
            }
            AppoimentIdCMB.setItems(obList);


        } catch (SQLException | ClassNotFoundException e) {
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
        DateAdminLable.setText(String.valueOf(now));
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



    private void setCellDataFactory() {
        PaymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
        appoimentidColumn.setCellValueFactory(new PropertyValueFactory<>("appoiment_id"));
        PatientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        AmountIdColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("payment_date"));

    }




    @FXML
    void ClearButtonOnAction(ActionEvent event) {clearFields();}

    private void clearFields() {
        PaymentIdTextField.setText("");
        AppoimentIdCMB.setValue("");
        ParentNICCMB.setValue("");
        AmountTextField.setText("");
        DateAdminLable.setText("");
    }

    @FXML
    void DeleteButtonOnAction(ActionEvent event) {
        String patient_id = ParentNICCMB.getValue();

        try {
            boolean isDeleted = paymentBO.deletePayment(patient_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                loadAllPayment();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void SaveButtonOnAction(ActionEvent event) {
        String payment_id = PaymentIdTextField.getText();
        String appoiment_id = AppoimentIdCMB.getValue();
        String patient_id = ParentNICCMB.getValue();
        String amount =  AmountTextField.getText();
        String payment_date = DateAdminLable.getText();



       Payment payment = new Payment(payment_id,appoiment_id,patient_id,amount,payment_date);

        try {
            boolean isSaved = paymentBO.savePayment(new PaymentDTO(payment_id,appoiment_id,patient_id,amount,payment_date));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "saved!").show();
                loadAllPayment();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void UpdateButtonOnAction(ActionEvent event) {
        String payment_id = PaymentIdTextField.getText();
        String appoiment_id = AppoimentIdCMB.getValue();
        String patient_id = ParentNICCMB.getValue();
        String amount =  AmountTextField.getText();
        String payment_date = DateAdminLable.getText();



        Payment payment = new Payment(payment_id,appoiment_id,patient_id,amount,payment_date);

        try {
            boolean isUpdated = paymentBO.updatePayment(new PaymentDTO(payment_id,appoiment_id,patient_id,amount,payment_date));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " updated!").show();
                loadAllPayment();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void PatientPaySearchOnAction(ActionEvent event) {
        String  patient_id = ParentNICCMB.getValue();

        try {
            Payment payment = paymentBO.searchPayment( patient_id);

            if (payment != null) {
                PaymentIdTextField.setText(payment.getPayment_id());
                AppoimentIdCMB.setValue(payment.getAppoiment_id());
                ParentNICCMB.setValue(payment.getPatient_id());
                AmountTextField.setText(payment.getAmount());
                DateAdminLable.setText(payment.getPayment_date());


            }
        } catch (SQLException | ClassNotFoundException e) {
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
    void PAmountOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^([A-z0-9.]){1,}$");
        if (!idPattern.matcher(AmountTextField.getText()).matches()) {
            addError(AmountTextField);
            saveButton.setDisable(true);
        }else{
            removeError(AmountTextField);
            saveButton.setDisable(false);
        }
    }

    @FXML
    void PIdOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(P)([A-z0-9.]){1,}$");
        if (!idPattern.matcher(PaymentIdTextField.getText()).matches()) {
            addError(PaymentIdTextField);
            saveButton.setDisable(true);
        }else{
            removeError(PaymentIdTextField);
            saveButton.setDisable(false);
        }
    }

}
