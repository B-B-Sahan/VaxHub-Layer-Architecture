package lk.ijse.vaxhub.bo.custom.Impl;

import lk.ijse.vaxhub.bo.custom.VaccineBO;
import lk.ijse.vaxhub.dao.DAOFactory;
import lk.ijse.vaxhub.dao.custom.RegisterDAO;
import lk.ijse.vaxhub.dao.custom.VaccinationDAO;
import lk.ijse.vaxhub.dao.custom.VaccineDAO;
import lk.ijse.vaxhub.dto.RegisterDTO;
import lk.ijse.vaxhub.dto.VaccineDTO;
import lk.ijse.vaxhub.entity.Register;
import lk.ijse.vaxhub.entity.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VaccineBOImpl implements VaccineBO {
    VaccineDAO vaccineDAO = (VaccineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VACCINE);


    @Override
    public ArrayList<VaccineDTO> getAllVaccine() throws SQLException, ClassNotFoundException {
        ArrayList<VaccineDTO> allVaccine= new ArrayList<>();
        ArrayList<Vaccine> all = vaccineDAO.getAll();
        for (Vaccine c : all) {
            allVaccine.add(new VaccineDTO(c.getVaccine_id(), c.getEmployee_id(),c.getVaccine_name(),c.getVaccine_date(),c.getManufacture(),c.getQuantity()));
        }
        return allVaccine;
    }

    @Override
    public boolean saveVaccine(VaccineDTO dto) throws SQLException, ClassNotFoundException {
        return vaccineDAO.save(new Vaccine(dto.getVaccine_id(),dto.getEmployee_id(), dto.getVaccine_name(), dto.getVaccine_date(), dto.getManufacture(),dto.getQuantity()));
    }

    @Override
    public boolean updateVaccine(VaccineDTO dto) throws SQLException, ClassNotFoundException {
        return vaccineDAO.update(new Vaccine(dto.getEmployee_id(), dto.getVaccine_name(), dto.getVaccine_date(), dto.getManufacture(),dto.getQuantity(),dto.getVaccine_id()));
    }

    @Override
    public boolean existVaccine(String id) throws SQLException, ClassNotFoundException {
        return vaccineDAO.exist(id);
    }

    @Override
    public boolean deleteVaccine(String id) throws SQLException, ClassNotFoundException {
        return vaccineDAO.delete(id);
    }

    @Override
    public Vaccine searchVaccine(String id) throws SQLException, ClassNotFoundException {
        return vaccineDAO.search(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return vaccineDAO.getIds();
    }

}


