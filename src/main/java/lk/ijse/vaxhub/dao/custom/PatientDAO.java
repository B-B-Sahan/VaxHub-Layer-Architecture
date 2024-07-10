package lk.ijse.vaxhub.dao.custom;

import lk.ijse.vaxhub.dao.CrudDAO;
import lk.ijse.vaxhub.entity.Patient;

import java.sql.SQLException;
import java.util.List;

public interface PatientDAO extends CrudDAO<Patient> {
    List<String> getPIds() throws SQLException, ClassNotFoundException;
}
