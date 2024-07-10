package lk.ijse.vaxhub.bo.custom.Impl;

import lk.ijse.vaxhub.bo.custom.LoginBO;
import lk.ijse.vaxhub.dao.custom.Impl.UserDAOImpl;
import lk.ijse.vaxhub.dao.custom.UserDAO;
import lk.ijse.vaxhub.entity.User;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO = new UserDAOImpl();

    public User checkCredential(String userId, String password) throws SQLException, ClassNotFoundException {
        return userDAO.checkCredential(userId, password);
    }

}
