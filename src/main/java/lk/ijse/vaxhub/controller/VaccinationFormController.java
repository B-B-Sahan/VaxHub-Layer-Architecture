package lk.ijse.vaxhub.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.vaxhub.bo.BOFactory;
import lk.ijse.vaxhub.bo.custom.*;
import lk.ijse.vaxhub.dto.PlaceVaccinationDTO;
import lk.ijse.vaxhub.dto.VaccinationDTO;
import lk.ijse.vaxhub.dto.VaccineDTO;
import lk.ijse.vaxhub.entity.Patient;
import lk.ijse.vaxhub.entity.PlaceVaccination;
import lk.ijse.vaxhub.entity.Vaccination;
import lk.ijse.vaxhub.entity.Vaccine;
import lk.ijse.vaxhub.view.tdm.VaccinationTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VaccinationFormController {

    public Label lblEmployeeId;
    @FXML
    private JFXButton BackPatientButton;

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
    private TableView<VaccinationTm> VaccinationTable;

    @FXML
    private JFXComboBox<String> VaccineIdCMB;

    @FXML
    private TableColumn<?, ?> VaccineIdColumn;

    @FXML
    private Label VaccineIdLBL;

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

    private Vaccine vaccine = null;

    PatientBO patientBO  = (PatientBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Patient);
    VaccineBO vaccineBO  = (VaccineBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Vaccine);
    VaccinationBO vaccinationBO  = (VaccinationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Vaccination);
    PlaceVaccinationBO placeVaccinationBO  = (PlaceVaccinationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Place_Vaccination);
    private List<VaccinationDTO> vaccinationList = new ArrayList<>();
    public void initialize() {

        this.vaccinationList = getAllVaccination();
        setCellDataFactory();
        loadAllVaccination();
        getParentId();
        getVaccineId();
        setVaccineName();
        setDate();

    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        DateAdminLable.setText(String.valueOf(now));
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

    private void getVaccineId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = vaccineBO.getIds();

            for (String id : idList) {
                obList.add(id);
            }
            VaccineIdCMB.setItems(obList);


        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }


    @FXML
    void VaccineIdCMBOnAction(ActionEvent event) {
        String id = VaccineIdCMB.getValue();
        System.out.println("432");
        try {
            Vaccine vaccine1 = vaccineBO.searchVaccine(id);
            if (vaccine1 != null) {
                System.out.println("ok "+id);
                VaccineIdLBL.setText(vaccine1.getVaccine_id());
                lblEmployeeId.setText(vaccine1.getEmployee_id());
                vaccine=vaccine1;
            }
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
                ParentNicLBL.setText(patient.getPatient_id());

            }
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllVaccination() {
        ObservableList<VaccinationTm> tmList = FXCollections.observableArrayList();

        for (VaccinationDTO vaccination : vaccinationList) {
            VaccinationTm vaccinationTm = new VaccinationTm(

                    vaccination.getPatient_id(),
                    vaccination.getVaccine_id(),
                    vaccination.getVaccine_name(),
                    vaccination.getDate()


            );
            tmList.add(vaccinationTm);

        }

        VaccinationTable.setItems(tmList);
        VaccinationTm selectedItem = VaccinationTable.getSelectionModel().getSelectedItem();


    }

    private void setCellDataFactory() {
        PatientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        VaccineIdColumn.setCellValueFactory(new PropertyValueFactory<>("vaccine_id"));
        VaccineNameColumn.setCellValueFactory(new PropertyValueFactory<>("vaccine_name"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

    private List<VaccinationDTO> getAllVaccination() {
        List<VaccinationDTO> vaccinationList = null;
        try {
            vaccinationList = vaccinationBO.getAllVaccination();
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return vaccinationList;
    }

    @FXML
    void BackPatientButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/patient_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }

    @FXML
    void ClearButtonOnAction(ActionEvent event) {clearFields();}

    private void clearFields() {
        ParentNICCMB.setValue("");
        VaccineIdCMB.setValue("");
        vaccineNmaeCMB.setValue("");
        DateAdminLable.setText("");
    }

    @FXML
    void DeleteButtonOnAction(ActionEvent event) {
        String patient_id = ParentNICCMB.getValue();

        try {
            boolean isDeleted = vaccinationBO.deleteVaccination(patient_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                loadAllVaccination();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void MoveHightButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/patientTable_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }

    @FXML
    void MoveWeightButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/weight_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }

    @FXML
    void SaveButtonOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String patient_id = ParentNICCMB.getValue();
        String vaccine_id = VaccineIdCMB.getValue();
        String vaccine_name = vaccineNmaeCMB.getValue();
        String date = DateAdminLable.getText();



        Vaccine vaccine1 = vaccineBO.searchVaccine(vaccine_id);
        Vaccination vaccination = new Vaccination(patient_id, vaccine_id,vaccine_name,date);


        try {
            vaccine1.getEmployee_id(); // This will throw NullPointerException
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        Vaccine vaccine = new Vaccine(vaccine_id,lblEmployeeId.getText(),vaccine1.getVaccine_name(),vaccine1.getVaccine_date(),vaccine1.getManufacture(),vaccine1.getQuantity());
        PlaceVaccination pv = new  PlaceVaccination(vaccine,vaccination);

        try {
            boolean isSaved = placeVaccinationBO.placeVaccination(pv);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "saved!").show();
                loadAllVaccination();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void UpdateButtonOnAction(ActionEvent event) {
        String patient_id = ParentNICCMB.getValue();
        String vaccine_id = VaccineIdCMB.getValue();
        String vaccine_name = vaccineNmaeCMB.getValue();
        String date = DateAdminLable.getText();

        Vaccination vaccination = new Vaccination(patient_id, vaccine_id,vaccine_name,date);

        try {
            boolean isUpdated = vaccinationBO.updateVaccination(new VaccinationDTO(patient_id, vaccine_id,vaccine_name,date));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "updated!").show();
                loadAllVaccination();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void patientVaccinationSearchOnAction(ActionEvent event) {
        String  patient_id = ParentNICCMB.getValue();

        try {
            Vaccination vaccination = vaccinationBO.searchVaccination( patient_id);

            if (vaccination != null) {
                ParentNICCMB.setValue(vaccination.getPatient_id());
                VaccineIdCMB.setValue(vaccination.getVaccine_id());
                vaccineNmaeCMB.setValue(vaccination.getVaccine_name());
                DateAdminLable.setText(vaccination.getDate());

            }
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }








}
