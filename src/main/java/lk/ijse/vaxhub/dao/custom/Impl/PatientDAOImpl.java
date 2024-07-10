package lk.ijse.vaxhub.dao.custom.Impl;

import lk.ijse.vaxhub.dao.SQLUtil;
import lk.ijse.vaxhub.dao.custom.PatientDAO;
import lk.ijse.vaxhub.entity.Employee;
import lk.ijse.vaxhub.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PatientDAOImpl implements PatientDAO {

    @Override
    public ArrayList<Patient> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Patient> allPatient = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM patient");
        while (rst.next()) {
            Patient patient = new Patient(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),rst.getString(8), rst.getString(9), rst.getString(10),rst.getString(11),rst.getString(12),rst.getString(13),rst.getString(14));
            allPatient.add(patient);
        }
        return allPatient;
    }

    @Override
    public boolean save(Patient entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO patient (patient_id,register_id,first_name,last_name,gender,birth_day,email,address,contact_number,date_administer,adverse_reaction,vaccine_name,weight_value,hight_value) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)", entity.getPatient_id(),entity.getRegister_id(),entity.getFirst_name(),entity.getLast_name(),entity.getGender(),entity.getBirth_day(), entity.getEmail(),entity.getAddress(),entity.getContact_number(),entity.getDate_administer(),entity.getAdverse_reaction(),entity.getVaccine_name(),entity.getWeight_value(),entity.getHight_value());

    }

    @Override
    public boolean update(Patient entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(  "UPDATE patient SET register_id=?,first_name=?,last_name=?, gender=?, birth_day=?, email=?, address=?, contact_number=?,date_administer=?, adverse_reaction=?,vaccine_name=?, weight_value=?,hight_value=? WHERE patient_id=?",entity.getRegister_id(),entity.getFirst_name(),entity.getLast_name(),entity.getGender(),entity.getBirth_day(), entity.getEmail(),entity.getAddress(),entity.getContact_number(),entity.getDate_administer(),entity.getAdverse_reaction(),entity.getVaccine_name(),entity.getWeight_value(),entity.getHight_value(), entity.getPatient_id());
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
        return SQLUtil.execute( "DELETE FROM patient WHERE patient_id=?", id);
    }

    @Override
    public Patient search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM patient WHERE patient_id=?", id + "");
        rst.next();
        return new Patient(id + "", rst.getString("register_id"), rst.getString("first_name"),rst.getString("last_name"),rst.getString("gender"),rst.getString("birth_day"),rst.getString("email"), rst.getString("address"),rst.getString("contact_number"),rst.getString("date_administer"),rst.getString("adverse_reaction"),rst.getString("vaccine_name"),rst.getString("weight_value"),rst.getString("hight_value"));
    }

    @Override
    public List<String> getPIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(  "SELECT patient_id FROM patient");
        List<String> idList = new ArrayList<>();
        while (rst.next()) {
            idList.add(rst.getString("patient_id"));
        }
        return idList;
    }
}



