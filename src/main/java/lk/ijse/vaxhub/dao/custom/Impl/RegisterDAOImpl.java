package lk.ijse.vaxhub.dao.custom.Impl;
import lk.ijse.vaxhub.dao.SQLUtil;
import lk.ijse.vaxhub.dao.custom.RegisterDAO;
import lk.ijse.vaxhub.entity.Payment;
import lk.ijse.vaxhub.entity.Register;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RegisterDAOImpl implements RegisterDAO {

    @Override
    public ArrayList<Register> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Register> allRegister = new ArrayList<>();
        ResultSet rst = SQLUtil.execute( "SELECT * FROM register");
        while (rst.next()) {
            Register register = new Register(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5));
            allRegister.add(register);
        }
        return allRegister;
    }

    @Override
    public boolean save(Register entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(" INSERT INTO  register (register_id,first_name,last_name,address,contact_number) VALUES (?,?,?,?,?)", entity.getRegister_id(),entity.getFirst_name(),entity.getLast_name(),entity.getAddress(),entity.getContact_number());
    }

    @Override
    public boolean update(Register entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(  "UPDATE register SET first_name=?,last_name=?, address=?, contact_number=? WHERE register_id=?,",entity.getFirst_name(),entity.getLast_name(),entity.getAddress(),entity.getContact_number(),entity.getRegister_id());
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
        return SQLUtil.execute( "DELETE FROM register WHERE register_id=?",id);
    }

    @Override
    public Register search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM register WHERE register_id=?", id + "");
        rst.next();
        return new Register(id + "", rst.getString("first_name"), rst.getString("last_name"),rst.getString("address"),rst.getString("contact_number"));
    }

    @Override
    public List<String> getRIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute( "SELECT register_id FROM register");
        List<String> idList = new ArrayList<>();
        while (rst.next()) {
            idList.add(rst.getString("register_id"));
        }
        return idList;
    }
}




