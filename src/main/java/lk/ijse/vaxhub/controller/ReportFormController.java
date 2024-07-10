package lk.ijse.vaxhub.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.vaxhub.bo.BOFactory;
import lk.ijse.vaxhub.bo.custom.EmployeeBO;
import lk.ijse.vaxhub.bo.custom.PatientBO;
import lk.ijse.vaxhub.dto.PatientDTO;
import lk.ijse.vaxhub.entity.Patient;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportFormController {
    @FXML
    private Label DateLable;
    @FXML
    private Label HeightLable;
    @FXML
    private JFXButton PrintButton;
    @FXML
    private JFXComboBox<String> PatientIdComboBox;

    @FXML
    private Label PatientIdLable;

    @FXML
    private Label PatientNameLable;

    @FXML
    private JFXButton SearchButton;

    @FXML
    private JFXButton SendButton;

    @FXML
    private Label VaccineName1Lable;

    @FXML
    private Label VaccineName2Lable;

    @FXML
    private Label VaccineName2Lable1;

    @FXML
    private Label VaccineName2Lable11;

    @FXML
    private Label VaccineName3Lable;

    @FXML
    private Label WeightLable;

    @FXML
    private Label adverseReactLabe;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private AnchorPane patientReportAnchorpane;

    private List<PatientDTO> patientList = new ArrayList<>();
    PatientBO patientBO  = (PatientBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Patient);

    public void initialize() {
        getParentId();


    }

    private void getParentId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> pIdList = patientBO.getPIds();

            for (String id : pIdList) {
                obList.add(id);
            }
            PatientIdComboBox.setItems(obList);


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void PatientIdComboBoxOnAction(ActionEvent event) {
        String id = PatientIdComboBox.getValue();
        try {
            Patient patient = patientBO.searchPatient(id);
            if (patient != null) {
                PatientIdLable.setText(patient.getPatient_id());
                PatientNameLable.setText(patient.getFirst_name());
                VaccineName1Lable.setText(patient.getVaccine_name());
                WeightLable.setText(patient.getWeight_value());
                HeightLable.setText(patient.getHight_value());
                adverseReactLabe.setText(patient.getAdverse_reaction());
                DateLable.setText(patient.getDate_administer());


            }
        }  catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void PrintButtonOnAction(ActionEvent event) {
        String date = String.valueOf(DateLable.getText());
        HashMap hashMap = new HashMap<>();
        hashMap.put("ParentId",PatientIdLable.getText());
        hashMap.put("ChildName",PatientNameLable.getText());
        hashMap.put("VaccineName",VaccineName1Lable.getText());
        hashMap.put("WeightValue",WeightLable.getText());
        hashMap.put("HieghtValue",HeightLable.getText());
        hashMap.put("AdverseReaction",adverseReactLabe.getText());
        hashMap.put("Date",date);

        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/Report/Patient.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void SendButtonOnAction(ActionEvent event) {

    }

}
