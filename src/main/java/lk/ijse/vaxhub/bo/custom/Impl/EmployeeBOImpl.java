package lk.ijse.vaxhub.bo.custom.Impl;

import lk.ijse.vaxhub.bo.custom.EmployeeBO;
import lk.ijse.vaxhub.dao.DAOFactory;
import lk.ijse.vaxhub.dao.custom.AppoimentDAO;
import lk.ijse.vaxhub.dao.custom.EmployeeDAO;
import lk.ijse.vaxhub.dto.EmployeeAttendanceDTO;
import lk.ijse.vaxhub.dto.EmployeeDTO;
import lk.ijse.vaxhub.entity.Appoiment;
import lk.ijse.vaxhub.entity.Employee;
import lk.ijse.vaxhub.entity.EmployeeAttendance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);


    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allEmployee= new ArrayList<>();
        ArrayList<Employee> all = employeeDAO.getAll();
        for (Employee c : all) {
            allEmployee.add(new EmployeeDTO(c.getEmployee_id(), c.getFirst_name(),c.getLast_name(),c.getRole(),c.getEmail(),c.getAddress(),c.getContact_number()));
        }
        return allEmployee;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(dto.getEmployee_id(),dto.getFirst_name(), dto.getLast_name(), dto.getRole(), dto.getEmail(),dto.getAddress(),dto.getContact_number()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getFirst_name(),dto.getLast_name(), dto.getRole(), dto.getEmail(),dto.getAddress(),dto.getContact_number(),dto.getEmployee_id()));
    }

    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.exist(id);
    }


    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public Employee searchEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return employeeDAO.getIds();
    }
}

