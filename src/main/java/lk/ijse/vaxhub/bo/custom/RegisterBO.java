package lk.ijse.vaxhub.bo.custom;

import lk.ijse.vaxhub.bo.SuperBO;
import lk.ijse.vaxhub.dto.PaymentDTO;
import lk.ijse.vaxhub.dto.RegisterDTO;
import lk.ijse.vaxhub.entity.Appoiment;
import lk.ijse.vaxhub.entity.Register;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RegisterBO extends SuperBO {
    public ArrayList<RegisterDTO> getAllRegister() throws SQLException, ClassNotFoundException;
    public boolean saveRegister(RegisterDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateRegister(RegisterDTO dto) throws SQLException, ClassNotFoundException;
    public boolean existRegister(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteRegister(String id) throws SQLException, ClassNotFoundException;
    public Register searchRegister(String id) throws SQLException, ClassNotFoundException;
    List<String> getRIds() throws SQLException, ClassNotFoundException;
}
