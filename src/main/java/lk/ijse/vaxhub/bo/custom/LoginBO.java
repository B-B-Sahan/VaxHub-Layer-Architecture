package lk.ijse.vaxhub.bo.custom;

import lk.ijse.vaxhub.bo.SuperBO;
import lk.ijse.vaxhub.entity.User;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    public User checkCredential(String userId, String password) throws SQLException, ClassNotFoundException;
}
