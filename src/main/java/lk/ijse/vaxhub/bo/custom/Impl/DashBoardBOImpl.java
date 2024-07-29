package lk.ijse.vaxhub.bo.custom.Impl;

import lk.ijse.vaxhub.bo.custom.DashBoardBO;
import lk.ijse.vaxhub.dao.DAOFactory;
import lk.ijse.vaxhub.dao.custom.AppoimentDAO;
import lk.ijse.vaxhub.dao.custom.EmployeeAttendanceDAO;
import lk.ijse.vaxhub.dao.custom.EmployeeDAO;

import java.sql.SQLException;

public class DashBoardBOImpl implements DashBoardBO {
    AppoimentDAO appoimentDAO = (AppoimentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.APPOIMENT);
    EmployeeAttendanceDAO employeeAttendanceDAO = (EmployeeAttendanceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE_ATTENDANCE);
    @Override
    public int getAppoimentCount() throws SQLException, ClassNotFoundException {
        return appoimentDAO.getAppoimentCount();
    }

    @Override
    public int getEmpolyeeAttendance() throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.getEmpolyeeAttendance();
    }
}
