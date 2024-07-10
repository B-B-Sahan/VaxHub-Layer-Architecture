package lk.ijse.vaxhub.bo.custom.Impl;

import lk.ijse.vaxhub.bo.custom.AppoimentBO;
import lk.ijse.vaxhub.controller.DashboardFormController;
import lk.ijse.vaxhub.dao.DAOFactory;
import lk.ijse.vaxhub.dao.custom.AppoimentDAO;
import lk.ijse.vaxhub.dto.AppoimentDTO;
import lk.ijse.vaxhub.entity.Appoiment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AppoimentBOImpl implements AppoimentBO {
    AppoimentDAO appoimentDAO = (AppoimentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.APPOIMENT);


    @Override
    public ArrayList<AppoimentDTO> getAllAppoiment() throws SQLException, ClassNotFoundException {
        ArrayList<AppoimentDTO> allAppoiment= new ArrayList<>();
        ArrayList<Appoiment> all = appoimentDAO.getAll();
        for (Appoiment c : all) {
            allAppoiment.add(new AppoimentDTO(c.getAppoiment_id(), c.getEmployee_id(),c.getPatient_id(),c.getAppoiment_date(),c.getAppoiment_time()));
        }
        return allAppoiment;
    }

    @Override
    public boolean saveAppoiment(AppoimentDTO dto) throws SQLException, ClassNotFoundException {
        return appoimentDAO.save(new Appoiment(dto.getAppoiment_id(), dto.getEmployee_id(), dto.getPatient_id(), dto.getAppoiment_date(),dto.getAppoiment_time()));
    }

    @Override
    public boolean updateAppoiment(AppoimentDTO dto) throws SQLException, ClassNotFoundException {
        return appoimentDAO.update(new Appoiment(dto.getEmployee_id(), dto.getPatient_id(), dto.getAppoiment_date(), dto.getAppoiment_time(),dto.getAppoiment_id()));
    }

    @Override
    public boolean existAppoiment(String id) throws SQLException, ClassNotFoundException {
        return appoimentDAO.exist(id);
    }

    @Override
    public boolean deleteAppoiment(String id) throws SQLException, ClassNotFoundException {
        return appoimentDAO.delete(id);
    }

    @Override
    public Appoiment searchAppoiment(String id) throws SQLException, ClassNotFoundException {
        return appoimentDAO.search(id);
    }

    @Override
    public int getAppoimentCount() throws SQLException, ClassNotFoundException {
        return appoimentDAO.getAppoimentCount();
    }

    @Override
    public List<String> getAIds() throws SQLException, ClassNotFoundException {
        return appoimentDAO.getAIds();
    }
}



