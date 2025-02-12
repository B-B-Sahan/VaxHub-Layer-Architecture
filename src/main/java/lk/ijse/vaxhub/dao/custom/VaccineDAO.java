package lk.ijse.vaxhub.dao.custom;

import lk.ijse.vaxhub.dao.CrudDAO;
import lk.ijse.vaxhub.entity.Vaccine;

import java.sql.SQLException;
import java.util.List;

public interface VaccineDAO extends CrudDAO<Vaccine> {
    List<String> getIds() throws SQLException, ClassNotFoundException;
    public  boolean qtyupdate(Vaccine vaccine) throws SQLException ;
}
