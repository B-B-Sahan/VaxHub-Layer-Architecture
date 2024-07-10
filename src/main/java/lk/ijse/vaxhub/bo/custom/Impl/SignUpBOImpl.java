package lk.ijse.vaxhub.bo.custom.Impl;

import lk.ijse.vaxhub.bo.custom.SignUpBO;
import lk.ijse.vaxhub.dao.DAOFactory;
import lk.ijse.vaxhub.dao.custom.Impl.UserDAOImpl;
import lk.ijse.vaxhub.dao.custom.UserDAO;
import lk.ijse.vaxhub.entity.User;

import java.sql.SQLException;

public class SignUpBOImpl  implements SignUpBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    public boolean save(User user) throws SQLException, ClassNotFoundException{
        return userDAO.save(user);
    }
    public User checkCredential(String userId, String password) throws SQLException, ClassNotFoundException{
        return userDAO.checkCredential(userId,password);
    }
}
