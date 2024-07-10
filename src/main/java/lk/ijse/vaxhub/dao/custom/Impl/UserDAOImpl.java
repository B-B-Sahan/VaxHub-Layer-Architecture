package lk.ijse.vaxhub.dao.custom.Impl;

import lk.ijse.vaxhub.dao.SQLUtil;
import lk.ijse.vaxhub.dao.custom.UserDAO;
import lk.ijse.vaxhub.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO user (user_id, name,  password, email) VALUES (?,?,?,?)",entity.getUser_id(),entity.getName(),entity.getPassword(),entity.getEmail());
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
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
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
    public User checkCredential(String userId, String password) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT user_id, name, password, email FROM user WHERE user_id = ? AND password = ?", userId, password);
        if (rst.next()) { // Ensure the cursor is moved to the first row
            return new User(
                    rst.getString("user_id"),
                    rst.getString("name"),
                    rst.getString("password"),
                    rst.getString("email"));
        } else {
            return null; // Return null if no user is found
        }
    }
}
