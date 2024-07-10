package lk.ijse.vaxhub.dao.custom;

import lk.ijse.vaxhub.dao.CrudDAO;
import lk.ijse.vaxhub.entity.Appoiment;

import java.sql.SQLException;
import java.util.List;

public interface AppoimentDAO extends CrudDAO<Appoiment> {
    public int getAppoimentCount() throws SQLException, ClassNotFoundException;
    List<String> getAIds() throws SQLException, ClassNotFoundException;
}
