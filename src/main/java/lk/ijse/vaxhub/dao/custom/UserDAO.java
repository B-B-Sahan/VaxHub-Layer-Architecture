package lk.ijse.vaxhub.dao.custom;

import lk.ijse.vaxhub.dao.CrudDAO;
import lk.ijse.vaxhub.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends CrudDAO <User>{
    public  User checkCredential(String userId, String password) throws SQLException, ClassNotFoundException;




}
