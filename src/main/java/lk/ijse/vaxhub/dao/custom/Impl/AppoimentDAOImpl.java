package lk.ijse.vaxhub.dao.custom.Impl;

import lk.ijse.vaxhub.dao.SQLUtil;
import lk.ijse.vaxhub.dao.custom.AppoimentDAO;
import lk.ijse.vaxhub.entity.Appoiment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppoimentDAOImpl implements AppoimentDAO {

    @Override
    public ArrayList<Appoiment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Appoiment> allAppoiment = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM appoiment");
        while (rst.next()) {
            Appoiment appoiment = new Appoiment(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5));
            allAppoiment.add(appoiment);
        }
        return allAppoiment;
    }

    @Override
    public boolean save(Appoiment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO appoiment (appoiment_id,employee_id,patient_id,appoiment_date,appoiment_time) VALUES (?,?,?,?,?)", entity.getAppoiment_id(),entity.getEmployee_id(),entity.getPatient_id(),entity.getAppoiment_date(),entity.getAppoiment_time());



    }

    @Override
    public boolean update(Appoiment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE  appoiment SET employee_id=?,patient_id=?, appoiment_date=?, appoiment_time=? WHERE appoiment_id=?",entity.getEmployee_id(),entity.getPatient_id(),entity.getAppoiment_date(),entity.getAppoiment_time(),entity.getAppoiment_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM appoiment WHERE appoiment_id=?",id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM patient WHERE appoiment_id=?",id);
    }

    @Override
    public Appoiment search(String patient_id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM appoiment WHERE  patient_id=?", patient_id + "");
        rst.next();
        return new Appoiment(patient_id + "", rst.getString("appoiment_id"), rst.getString("employee_id"),rst.getString("appoiment_date"),rst.getString("appoiment_time"));
    }


    @Override
    public int getAppoimentCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(appoiment_id) FROM appoiment");
        int appoimentCount = 0;
        if (rst.next()) {
            appoimentCount = rst.getInt("appoiment_id");
        }
        return appoimentCount;
    }

    @Override
    public List<String> getAIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute( "SELECT appoiment_id  FROM appoiment");
        List<String> idList = new ArrayList<>();
        while (rst.next()) {
            idList.add(rst.getString("appoiment_id"));
        }
        return idList;
    }
}
