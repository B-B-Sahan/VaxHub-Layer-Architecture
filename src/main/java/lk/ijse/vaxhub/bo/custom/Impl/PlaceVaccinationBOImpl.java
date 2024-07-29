package lk.ijse.vaxhub.bo.custom.Impl;

import lk.ijse.vaxhub.bo.custom.PlaceVaccinationBO;
import lk.ijse.vaxhub.dao.custom.Impl.VaccinationDAOImpl;
import lk.ijse.vaxhub.dao.custom.Impl.VaccineDAOImpl;
import lk.ijse.vaxhub.dao.custom.VaccinationDAO;
import lk.ijse.vaxhub.dao.custom.VaccineDAO;
import lk.ijse.vaxhub.db.DbConnection;
import lk.ijse.vaxhub.dto.PlaceVaccinationDTO;
import lk.ijse.vaxhub.entity.PlaceVaccination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaceVaccinationBOImpl implements PlaceVaccinationBO {
    VaccineDAO vaccineDAO = new VaccineDAOImpl();
    VaccinationDAO vaccinationDAO = new VaccinationDAOImpl();
    @Override
    public boolean placeVaccination(PlaceVaccination po) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isVaccineSaved = vaccineDAO.qtyupdate(po.getVaccine());
            if (isVaccineSaved) {
                boolean isVaccinationSaved = vaccinationDAO.save(po.getVaccination());
                if (isVaccinationSaved) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();

            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }



    }

