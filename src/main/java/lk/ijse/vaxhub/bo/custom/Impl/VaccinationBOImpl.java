package lk.ijse.vaxhub.bo.custom.Impl;
import lk.ijse.vaxhub.bo.custom.VaccinationBO;
import lk.ijse.vaxhub.dao.DAOFactory;
import lk.ijse.vaxhub.dao.custom.RegisterDAO;
import lk.ijse.vaxhub.dao.custom.VaccinationDAO;
import lk.ijse.vaxhub.dto.RegisterDTO;
import lk.ijse.vaxhub.dto.VaccinationDTO;
import lk.ijse.vaxhub.entity.Register;
import lk.ijse.vaxhub.entity.Vaccination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;

public class VaccinationBOImpl implements VaccinationBO {
    VaccinationDAO vaccinationDAO = (VaccinationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VACCINATION);

    @Override
    public ArrayList<VaccinationDTO> getAllVaccination() throws SQLException, ClassNotFoundException {
        ArrayList<VaccinationDTO> allVaccination= new ArrayList<>();
        ArrayList<Vaccination> all = vaccinationDAO.getAll();
        for (Vaccination c : all) {
            allVaccination.add(new VaccinationDTO(c.getPatient_id(), c.getVaccine_id(),c.getVaccine_name(),c.getDate()));
        }
        return allVaccination;
    }

    @Override
    public boolean saveVaccination(VaccinationDTO dto) throws SQLException, ClassNotFoundException {
        return vaccinationDAO.save(new Vaccination(dto.getPatient_id(),dto.getVaccine_id(), dto.getVaccine_name(), dto.getDate()));
    }

    @Override
    public boolean updateVaccination(VaccinationDTO dto) throws SQLException, ClassNotFoundException {
        return vaccinationDAO.update(new Vaccination(dto.getVaccine_id(), dto.getVaccine_name(), dto.getDate(),dto.getPatient_id()));
    }


    @Override
    public boolean existVaccination(String id) throws SQLException, ClassNotFoundException {
        return vaccinationDAO.exist(id);
    }

    @Override
    public boolean deleteVaccination(String id) throws SQLException, ClassNotFoundException {
        return vaccinationDAO.delete(id);
    }

    @Override
    public Vaccination searchVaccination(String id) throws SQLException, ClassNotFoundException {
        return vaccinationDAO.search(id);
    }
}
