package lk.ijse.vaxhub.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.vaxhub.model.Patient;
import lk.ijse.vaxhub.model.Tm.PatientTm;
import lk.ijse.vaxhub.repository.PatientRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientTableController {

    @FXML
    private TableColumn<?, ?> AdverseReaction;

    @FXML
    private JFXButton BackPatientButton;

    @FXML
    private TableColumn<?, ?> BirthdayColumn;

    @FXML
    private TableColumn<?, ?> DateAdministerColumn;

    @FXML
    private TableColumn<?, ?> EmailColumn;

    @FXML
    private TableColumn<?, ?> GenderColumn;

    @FXML
    private JFXButton MoveVaccinationButton;

    @FXML
    private TableColumn<?, ?> PatientAddressColumn;

    @FXML
    private TableColumn<?, ?> PatientCintactNumberColumn;

    @FXML
    private TableColumn<?, ?> PatientFirstNameColumn;

    @FXML
    private TableColumn<?, ?> PatientIdColumn;

    @FXML
    private TableColumn<?, ?> PatientLastNameColumn;

    @FXML
    private TableColumn<?, ?> hieghtValueClm;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private TableView<PatientTm> patientTable;

    @FXML
    private TableColumn<?, ?> regIdColumn;

    @FXML
    private TableColumn<?, ?> vaccineNameClm;

    @FXML
    private TableColumn<?, ?> weightValueClm;


        private List<Patient> patientList = new ArrayList<>();

        public void initialize() {
            this.patientList = getAllPatient();
            setCellDataFactory();
            loadAllPatients();
        }

        private void loadAllPatients() {
            ObservableList<PatientTm> tmList = FXCollections.observableArrayList();

            for (Patient patient : patientList) {
                PatientTm patientTm = new PatientTm(

                        patient.getPatient_id(),
                        patient.getRegister_id(),
                        patient.getFirst_name(),
                        patient.getLast_name(),
                        patient.getGender(),
                        patient.getBirth_day(),
                        patient.getEmail(),
                        patient.getAddress(),
                        patient.getContact_number(),
                        patient.getDate_administer(),
                        patient.getAdverse_reaction(),
                        patient.getVaccine_name(),
                        patient.getWeight_value(),
                        patient.getHight_value()


                );
                tmList.add(patientTm);

            }
            patientTable.setItems(tmList);
            PatientTm selectedItem = patientTable.getSelectionModel().getSelectedItem();
            System.out.println("selectedItem = " + selectedItem);


        }

        private List<Patient> getAllPatient() {
            List<Patient> patientList = null;
            try {
                patientList = PatientRepo.getAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return patientList;
        }

        private void setCellDataFactory() {
            PatientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
            regIdColumn.setCellValueFactory(new PropertyValueFactory<>("register_id"));
            PatientFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            PatientLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            GenderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
            BirthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birth_day"));
            EmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            PatientAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            PatientCintactNumberColumn.setCellValueFactory(new PropertyValueFactory<>("contact_number"));
            DateAdministerColumn.setCellValueFactory(new PropertyValueFactory<>("date_administer"));
            AdverseReaction.setCellValueFactory(new PropertyValueFactory<>("adverse_reaction"));
            vaccineNameClm.setCellValueFactory(new PropertyValueFactory<>("vaccine_name"));
            weightValueClm.setCellValueFactory(new PropertyValueFactory<>("weight_value"));
            hieghtValueClm.setCellValueFactory(new PropertyValueFactory<>("hight_value"));

        }


        @FXML
        void BackPatientButtonOnAction(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/patient_form.fxml"));
            Pane registePane = (Pane) fxmlLoader.load();
            paneHolder.getChildren().clear();
            paneHolder.getChildren().add(registePane);
        }


        @FXML
        void MoveVaccinationButtonOnAction(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/vaccination_form.fxml"));
            Pane registePane = (Pane) fxmlLoader.load();
            paneHolder.getChildren().clear();
            paneHolder.getChildren().add(registePane);
        }


    }


