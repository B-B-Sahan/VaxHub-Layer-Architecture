package lk.ijse.vaxhub.dao.custom;

import lk.ijse.vaxhub.dao.CrudDAO;
import lk.ijse.vaxhub.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {
    List<String> getIds() throws SQLException, ClassNotFoundException;
}
