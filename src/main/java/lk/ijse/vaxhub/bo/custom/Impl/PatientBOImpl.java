package lk.ijse.vaxhub.bo.custom.Impl;

import lk.ijse.vaxhub.bo.custom.PatientBO;
import lk.ijse.vaxhub.dao.DAOFactory;
import lk.ijse.vaxhub.dao.custom.EmployeeDAO;
import lk.ijse.vaxhub.dao.custom.PatientDAO;
import lk.ijse.vaxhub.dto.EmployeeDTO;
import lk.ijse.vaxhub.dto.PatientDTO;
import lk.ijse.vaxhub.entity.Appoiment;
import lk.ijse.vaxhub.entity.Employee;
import lk.ijse.vaxhub.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PatientBOImpl implements PatientBO {

    PatientDAO patientDAO = (PatientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENT);

    @Override
    public ArrayList<PatientDTO> getAllPatient() throws SQLException, ClassNotFoundException {
        ArrayList<PatientDTO> allPatient= new ArrayList<>();
        ArrayList<Patient> all = patientDAO.getAll();
        for (Patient c : all) {
            allPatient.add(new PatientDTO(c.getPatient_id(), c.getRegister_id(),c.getFirst_name(),c.getLast_name(),c.getGender(),c.getBirth_day(),c.getEmail(),c.getAddress(),c.getContact_number(),c.getDate_administer(),c.getAdverse_reaction(),c.getVaccine_name(),c.getWeight_value(),c.getHight_value()));
        }
        return allPatient;
    }

    @Override
    public boolean savePatient(PatientDTO dto) throws SQLException, ClassNotFoundException {
        return patientDAO.save(new Patient(dto.getPatient_id(),dto.getRegister_id(), dto.getFirst_name(), dto.getLast_name(), dto.getGender(),dto.getBirth_day(),dto.getEmail(),dto.getAddress(),dto.getContact_number(),dto.getDate_administer(),dto.getAdverse_reaction(),dto.getVaccine_name(),dto.getWeight_value(),dto.getHight_value()));
    }

    @Override
    public boolean updatePatient(PatientDTO dto) throws SQLException, ClassNotFoundException {
        return patientDAO.update(new Patient(dto.getRegister_id(), dto.getFirst_name(), dto.getLast_name(), dto.getGender(),dto.getBirth_day(),dto.getEmail(),dto.getAddress(),dto.getContact_number(),dto.getDate_administer(),dto.getAdverse_reaction(),dto.getVaccine_name(),dto.getWeight_value(),dto.getHight_value(),dto.getPatient_id()));
    }

    @Override
    public boolean existPatient(String id) throws SQLException, ClassNotFoundException {
        return patientDAO.exist(id);
    }

    @Override
    public boolean deletePatient(String id) throws SQLException, ClassNotFoundException {
        return patientDAO.delete(id);
    }

    @Override
    public Patient searchPatient(String id) throws SQLException, ClassNotFoundException {
        return patientDAO.search(id);
    }

    @Override
    public List<String> getPIds() throws SQLException, ClassNotFoundException {
        return patientDAO.getPIds();
    }
}


