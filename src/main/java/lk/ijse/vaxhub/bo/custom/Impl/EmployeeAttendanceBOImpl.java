package lk.ijse.vaxhub.bo.custom.Impl;

import lk.ijse.vaxhub.bo.custom.EmployeeAttendanceBO;
import lk.ijse.vaxhub.dao.DAOFactory;
import lk.ijse.vaxhub.dao.custom.AppoimentDAO;
import lk.ijse.vaxhub.dao.custom.EmployeeAttendanceDAO;
import lk.ijse.vaxhub.dto.AppoimentDTO;
import lk.ijse.vaxhub.dto.EmployeeAttendanceDTO;
import lk.ijse.vaxhub.entity.Appoiment;
import lk.ijse.vaxhub.entity.EmployeeAttendance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;


public class EmployeeAttendanceBOImpl implements EmployeeAttendanceBO {
    EmployeeAttendanceDAO employeeAttendanceDAO = (EmployeeAttendanceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE_ATTENDANCE);


    @Override
    public ArrayList<EmployeeAttendanceDTO> getAllEmployeeAttendance() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeAttendanceDTO> allEmployeeAttendance= new ArrayList<>();
        ArrayList<EmployeeAttendance> all = employeeAttendanceDAO.getAll();
        for (EmployeeAttendance c : all) {
            allEmployeeAttendance.add(new EmployeeAttendanceDTO(c.getAttendance_id(), c.getEmployee_id(),c.getDate(),c.getIn_time(),c.getOut_time(),c.getAttendance()));
        }
        return allEmployeeAttendance;
    }


    @Override
    public boolean saveEmployeeAttendance(EmployeeAttendanceDTO dto) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.save(new EmployeeAttendance(dto.getAttendance_id(),dto.getEmployee_id(), dto.getDate(), dto.getIn_time(), dto.getOut_time(),dto.getAttendance()));
    }

    @Override
    public boolean updateEmployeeAttendance(EmployeeAttendanceDTO dto) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.update(new EmployeeAttendance(dto.getEmployee_id(), dto.getDate(), dto.getIn_time(), dto.getOut_time(),dto.getAttendance(),dto.getAttendance_id()));
    }


    @Override
    public boolean existEmployeeAttendance(String id) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.exist(id);
    }

    @Override
    public boolean deleteEmployeeAttendance(String id) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.delete(id);
    }

    @Override
    public EmployeeAttendance searchEmployeeAttendance(String id) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.search(id);
    }
}