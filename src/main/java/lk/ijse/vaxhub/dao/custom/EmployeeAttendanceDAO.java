package lk.ijse.vaxhub.dao.custom;

import lk.ijse.vaxhub.dao.CrudDAO;
import lk.ijse.vaxhub.entity.EmployeeAttendance;

import java.sql.SQLException;

public interface EmployeeAttendanceDAO extends CrudDAO<EmployeeAttendance> {
    public int getEmpolyeeAttendance() throws SQLException, ClassNotFoundException;
}
