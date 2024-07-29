package lk.ijse.vaxhub.bo.custom;

import lk.ijse.vaxhub.bo.SuperBO;
import lk.ijse.vaxhub.dto.VaccinationDTO;
import lk.ijse.vaxhub.dto.VaccineDTO;
import lk.ijse.vaxhub.entity.Appoiment;
import lk.ijse.vaxhub.entity.Vaccine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VaccineBO extends SuperBO {
    public ArrayList<VaccineDTO> getAllVaccine() throws SQLException, ClassNotFoundException;
    public boolean saveVaccine(VaccineDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateVaccine(VaccineDTO dto) throws SQLException, ClassNotFoundException;
    public boolean existVaccine(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteVaccine(String id) throws SQLException, ClassNotFoundException;
    public Vaccine searchVaccine(String id) throws SQLException, ClassNotFoundException;
    List<String> getIds() throws SQLException, ClassNotFoundException;
    public  boolean qtyupdate(Vaccine vaccine) throws SQLException,ClassNotFoundException;
}
