package lk.ijse.vaxhub.dao.custom.Impl;

import lk.ijse.vaxhub.dao.SQLUtil;
import lk.ijse.vaxhub.dao.custom.EmployeeAttendanceDAO;
import lk.ijse.vaxhub.entity.EmployeeAttendance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class EmployeeAttendanceDAOImpl implements EmployeeAttendanceDAO {


    @Override
    public ArrayList<EmployeeAttendance> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeAttendance> allEmployeeAttendances = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM attendance");
        while (rst.next()) {
            EmployeeAttendance employeeAttendance = new EmployeeAttendance(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));
            allEmployeeAttendances.add(employeeAttendance);
        }
        return allEmployeeAttendances;
    }

    @Override
    public boolean save(EmployeeAttendance entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO attendance VALUES (?,?,?,?,?,?)", entity.getAttendance_id(),entity.getEmployee_id(),entity.getDate(),entity.getIn_time(),entity.getOut_time(),entity.getAttendance());

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT attendance_id FROM attendance WHERE attendance_id=?",id);
        return rst.next();
    }

    @Override
    public boolean update(EmployeeAttendance entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE attendance SET employee_id=?, date=?,in_time=?, out_time=?,attendance=? WHERE attendance_id=?", entity.getAttendance_id(),entity.getEmployee_id(),entity.getDate(),entity.getIn_time(),entity.getOut_time(),entity.getAttendance());
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM attendance WHERE attendance_id=?", id);
    }

    @Override
    public EmployeeAttendance search(String employee_id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM attendance WHERE employee_id=?", employee_id + "");
        rst.next();
          return new EmployeeAttendance(employee_id + "", rst.getString("attendance_id"), rst.getString("date"),rst.getString("in_time"),rst.getString("out_time"),rst.getString("attendance"));
}

    @Override
    public int getEmpolyeeAttendance() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(employee_id) FROM attendance");
        int employeeCount = 0;
        if (rst.next()) {
            employeeCount = rst.getInt("employee_id");
        }
        return employeeCount;
    }
}