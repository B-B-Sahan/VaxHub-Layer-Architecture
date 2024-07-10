package lk.ijse.vaxhub.bo.custom;

import lk.ijse.vaxhub.bo.SuperBO;
import lk.ijse.vaxhub.dto.AppoimentDTO;
import lk.ijse.vaxhub.dto.EmployeeAttendanceDTO;
import lk.ijse.vaxhub.entity.Appoiment;
import lk.ijse.vaxhub.entity.EmployeeAttendance;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeAttendanceBO extends SuperBO {
    public ArrayList<EmployeeAttendanceDTO> getAllEmployeeAttendance() throws SQLException, ClassNotFoundException;
    public boolean saveEmployeeAttendance(EmployeeAttendanceDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateEmployeeAttendance(EmployeeAttendanceDTO dto) throws SQLException, ClassNotFoundException;
    public boolean existEmployeeAttendance(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteEmployeeAttendance(String id) throws SQLException, ClassNotFoundException;
    public EmployeeAttendance searchEmployeeAttendance(String id) throws SQLException, ClassNotFoundException;
}
