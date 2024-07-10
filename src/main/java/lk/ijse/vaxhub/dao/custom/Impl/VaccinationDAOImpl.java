package lk.ijse.vaxhub.dao.custom.Impl;
import lk.ijse.vaxhub.dao.SQLUtil;
import lk.ijse.vaxhub.dao.custom.VaccinationDAO;
import lk.ijse.vaxhub.entity.Register;
import lk.ijse.vaxhub.entity.Vaccination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class VaccinationDAOImpl implements VaccinationDAO {
    @Override
    public ArrayList<Vaccination> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Vaccination> allVaccination = new ArrayList<>();
        ResultSet rst = SQLUtil.execute( "SELECT * FROM vaccination");
        while (rst.next()) {
            Vaccination vaccination = new Vaccination(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4));
            allVaccination.add(vaccination);
        }
        return allVaccination;
    }

    @Override
    public boolean save(Vaccination entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(" INSERT INTO vaccination (patient_id,vaccine_id,vaccine_name,date) VALUES (?,?,?,?)", entity.getPatient_id(),entity.getVaccine_id(),entity.getVaccine_name(),entity.getDate());
    }

    @Override
    public boolean update(Vaccination entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(  "UPDATE vaccination SET vaccine_id=?,vaccine_name=?, date=? WHERE patient_id=?",entity.getVaccine_id(),entity.getVaccine_name(),entity.getDate(), entity.getPatient_id());
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
        return SQLUtil.execute(  "DELETE FROM vaccination WHERE patient_id=?",id);
    }

    @Override
    public Vaccination search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM vaccination WHERE patient_id=?", id + "");
        rst.next();
        return new Vaccination(id + "",  rst.getString("vaccine_id"),rst.getString("vaccine_name"),rst.getString("date"));
    }
}
