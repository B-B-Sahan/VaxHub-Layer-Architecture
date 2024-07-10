package lk.ijse.vaxhub.bo.custom;

import lk.ijse.vaxhub.bo.SuperBO;
import lk.ijse.vaxhub.dto.EmployeeDTO;
import lk.ijse.vaxhub.dto.PatientDTO;
import lk.ijse.vaxhub.entity.Appoiment;
import lk.ijse.vaxhub.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PatientBO extends SuperBO {
    public ArrayList<PatientDTO> getAllPatient() throws SQLException, ClassNotFoundException;
    public boolean savePatient(PatientDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updatePatient(PatientDTO dto) throws SQLException, ClassNotFoundException;
    public boolean existPatient(String id) throws SQLException, ClassNotFoundException;
    public boolean deletePatient(String id) throws SQLException, ClassNotFoundException;
    public Patient searchPatient(String id) throws SQLException, ClassNotFoundException;
    List<String> getPIds() throws SQLException, ClassNotFoundException;
}
