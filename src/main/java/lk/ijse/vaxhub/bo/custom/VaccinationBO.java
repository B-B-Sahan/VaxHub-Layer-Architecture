package lk.ijse.vaxhub.bo.custom;

import lk.ijse.vaxhub.bo.SuperBO;
import lk.ijse.vaxhub.dto.RegisterDTO;
import lk.ijse.vaxhub.dto.VaccinationDTO;
import lk.ijse.vaxhub.entity.Appoiment;
import lk.ijse.vaxhub.entity.Vaccination;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VaccinationBO extends SuperBO {
    public ArrayList<VaccinationDTO> getAllVaccination() throws SQLException, ClassNotFoundException;
    public boolean saveVaccination(VaccinationDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateVaccination(VaccinationDTO dto) throws SQLException, ClassNotFoundException;
    public boolean existVaccination(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteVaccination(String id) throws SQLException, ClassNotFoundException;
    public Vaccination searchVaccination(String id) throws SQLException, ClassNotFoundException;
}
