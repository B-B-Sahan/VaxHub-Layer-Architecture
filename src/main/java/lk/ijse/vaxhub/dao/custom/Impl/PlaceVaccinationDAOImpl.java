package lk.ijse.vaxhub.dao.custom.Impl;

import lk.ijse.vaxhub.dao.custom.PlaceVaccinationDAO;
import lk.ijse.vaxhub.entity.PlaceVaccination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;


public class PlaceVaccinationDAOImpl implements PlaceVaccinationDAO {

    @Override
    public ArrayList<PlaceVaccination> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(PlaceVaccination entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(PlaceVaccination entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PlaceVaccination search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
