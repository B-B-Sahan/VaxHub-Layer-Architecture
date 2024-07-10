package lk.ijse.vaxhub.dao.custom.Impl;

import lk.ijse.vaxhub.dao.SQLUtil;
import lk.ijse.vaxhub.dao.custom.EmployeeDAO;
import lk.ijse.vaxhub.entity.Appoiment;
import lk.ijse.vaxhub.entity.Employee;
import lk.ijse.vaxhub.entity.EmployeeAttendance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployee = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        while (rst.next()) {
            Employee employee = new Employee(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7));
            allEmployee.add(employee);
        }
        return allEmployee;
    }

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee (employee_id,first_name,last_name,role,email,address,contact_number) VALUES (?, ?, ?, ?, ?, ?, ?)", entity.getEmployee_id(),entity.getFirst_name(),entity.getLast_name(),entity.getRole(),entity.getEmail(),entity.getAddress(),entity.getContact_number());

    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "UPDATE employee SET  first_name=?,last_name=?, role=?, email=?, address=?, contact_number=? WHERE employee_id=?",entity.getFirst_name(),entity.getLast_name(),entity.getRole(),entity.getEmail(),entity.getAddress(),entity.getAddress(),entity.getContact_number(),entity.getEmployee_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM employee WHERE employee_id=?", id);
    }

    @Override
    public Employee search(String employee_id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM employee WHERE employee_id=?", employee_id + "");
        rst.next();
        return new Employee(employee_id + "", rst.getString("first_name"), rst.getString("last_name"),rst.getString("role"),rst.getString("email"),rst.getString("address"),rst.getString("contact_number"));
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute( "SELECT employee_id FROM employee");
        List<String> idList = new ArrayList<>();
        while (rst.next()) {
            idList.add(rst.getString("employee_id"));
        }
        return idList;
    }
}

