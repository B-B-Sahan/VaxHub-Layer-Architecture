package lk.ijse.vaxhub.dao.custom.Impl;

import lk.ijse.vaxhub.dao.SQLUtil;
import lk.ijse.vaxhub.dao.custom.VaccineDAO;
import lk.ijse.vaxhub.entity.EmployeeAttendance;
import lk.ijse.vaxhub.entity.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VaccineDAOImpl implements VaccineDAO {


    @Override
    public ArrayList<Vaccine> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Vaccine> allVaccine = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM vaccine");
        while (rst.next()) {
            Vaccine vaccine = new Vaccine(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));
            allVaccine.add(vaccine);
        }
        return allVaccine;
    }

    @Override
    public boolean save(Vaccine entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO vaccine (vaccine_id,employee_id,vaccine_name,vaccine_date,manufacture,quantity) VALUES (?,?,?,?,?,?)", entity.getVaccine_id(),entity.getEmployee_id(),entity.getVaccine_name(),entity.getVaccine_date(),entity.getManufacture(),entity.getQuantity());

    }

    @Override
    public boolean update(Vaccine entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(  "UPDATE vaccine SET employee_id=?,  vaccine_name=?, vaccine_date=?, manufacture=?, quantity=? WHERE vaccine_id=?",entity.getEmployee_id(),entity.getVaccine_name(),entity.getVaccine_date(),entity.getManufacture(),entity.getQuantity(), entity.getVaccine_id());
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
        return SQLUtil.execute("DELETE FROM vaccine WHERE vaccine_id=?", id);
    }

    @Override
    public Vaccine search(String vaccine_name) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM vaccine WHERE vaccine_id=?", vaccine_name + "");
        rst.next();
        return new Vaccine(vaccine_name + "", rst.getString("vaccine_id"), rst.getString("employee_id"),rst.getString("vaccine_date"),rst.getString("manufacture"),rst.getString("quantity"));
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute( "SELECT vaccine_id FROM vaccine");
        List<String> idList = new ArrayList<>();
        while (rst.next()) {
            idList.add(rst.getString("vaccine_id"));
        }
        return idList;
    }
}


