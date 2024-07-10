package lk.ijse.vaxhub.bo.custom.Impl;
import lk.ijse.vaxhub.bo.custom.RegisterBO;
import lk.ijse.vaxhub.dao.DAOFactory;
import lk.ijse.vaxhub.dao.custom.PaymentDAO;
import lk.ijse.vaxhub.dao.custom.RegisterDAO;
import lk.ijse.vaxhub.dto.PaymentDTO;
import lk.ijse.vaxhub.dto.RegisterDTO;
import lk.ijse.vaxhub.entity.Payment;
import lk.ijse.vaxhub.entity.Register;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RegisterBOImpl implements RegisterBO {
    RegisterDAO registerDAO = (RegisterDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTER);

    @Override
    public ArrayList<RegisterDTO> getAllRegister() throws SQLException, ClassNotFoundException {
        ArrayList<RegisterDTO> allRegister= new ArrayList<>();
        ArrayList<Register> all = registerDAO.getAll();
        for (Register c : all) {
            allRegister.add(new RegisterDTO(c.getRegister_id(), c.getFirst_name(),c.getLast_name(),c.getAddress(),c.getContact_number()));
        }
        return allRegister;
    }

    @Override
    public boolean saveRegister(RegisterDTO dto) throws SQLException, ClassNotFoundException {
        return registerDAO.save(new Register(dto.getRegister_id(),dto.getFirst_name(), dto.getLast_name(), dto.getAddress(), dto.getContact_number()));
    }


    @Override
    public boolean updateRegister(RegisterDTO dto) throws SQLException, ClassNotFoundException {
        return registerDAO.update(new Register(dto.getFirst_name(), dto.getLast_name(), dto.getAddress(), dto.getContact_number(),dto.getRegister_id()));
    }

    @Override
    public boolean existRegister(String id) throws SQLException, ClassNotFoundException {
        return registerDAO.exist(id);
    }


    @Override
    public boolean deleteRegister(String id) throws SQLException, ClassNotFoundException {
        return registerDAO.delete(id);
    }

    @Override
    public Register searchRegister(String id) throws SQLException, ClassNotFoundException {
        return registerDAO.search(id);
    }

    @Override
    public List<String> getRIds() throws SQLException, ClassNotFoundException {
        return registerDAO.getRIds();
    }
}




