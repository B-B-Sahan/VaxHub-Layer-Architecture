package lk.ijse.vaxhub.dao.custom.Impl;

import lk.ijse.vaxhub.dao.SQLUtil;
import lk.ijse.vaxhub.dao.custom.AppoimentDAO;
import lk.ijse.vaxhub.entity.Appoiment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppoimentDAOImpl implements AppoimentDAO {

    @Override
    public ArrayList<Appoiment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Appoiment> allAppoiment = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM appoiment");
        while (rst.next()) {
            Appoiment appoiment = new Appoiment(rst.getString(), rst.getString("c_name"), rst.getString("c_address"),rst.getString("c_tel"),rst.getString("c_id"));
            allAppoiment.add(appoiment);
        }
        return allAppoiment;
    }

    @Override
    public boolean save(Appoiment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO appoiment (appoiment_id,employee_id,patient_id,payment_id) VALUES (?,?,?,?,?)", entity.getC_mail(),entity.getC_name(),entity.getC_address(),entity.getC_tel(),entity.getC_id());
    }

    @Override
    public boolean update(Appoiment entity) throws SQLException, ClassNotFoundException {
        return false;
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
        return false;
    }

    @Override
    public Appoiment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean clear(Appoiment entity) throws SQLException, ClassNotFoundException {
        return false;
    }
}
