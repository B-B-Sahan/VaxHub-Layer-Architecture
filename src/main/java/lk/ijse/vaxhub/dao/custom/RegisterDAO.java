package lk.ijse.vaxhub.dao.custom;

import lk.ijse.vaxhub.dao.CrudDAO;
import lk.ijse.vaxhub.entity.Register;

import java.sql.SQLException;
import java.util.List;

public interface RegisterDAO extends CrudDAO<Register> {
    List<String> getRIds() throws SQLException, ClassNotFoundException;
}
