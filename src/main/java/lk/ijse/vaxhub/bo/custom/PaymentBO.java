package lk.ijse.vaxhub.bo.custom;

import lk.ijse.vaxhub.bo.SuperBO;
import lk.ijse.vaxhub.dto.PatientDTO;
import lk.ijse.vaxhub.dto.PaymentDTO;
import lk.ijse.vaxhub.entity.Appoiment;
import lk.ijse.vaxhub.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    public ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException;
    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;
    public boolean existPayment(String id) throws SQLException, ClassNotFoundException;
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException;
    public Payment searchPayment(String id) throws SQLException, ClassNotFoundException;
}


