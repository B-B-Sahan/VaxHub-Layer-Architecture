package lk.ijse.vaxhub.bo.custom.Impl;

import lk.ijse.vaxhub.bo.custom.PaymentBO;
import lk.ijse.vaxhub.dao.DAOFactory;
import lk.ijse.vaxhub.dao.custom.PatientDAO;
import lk.ijse.vaxhub.dao.custom.PaymentDAO;
import lk.ijse.vaxhub.dto.PatientDTO;
import lk.ijse.vaxhub.dto.PaymentDTO;
import lk.ijse.vaxhub.entity.Employee;
import lk.ijse.vaxhub.entity.Patient;
import lk.ijse.vaxhub.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;


public class PaymenBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> allPayment= new ArrayList<>();
        ArrayList<Payment> all = paymentDAO.getAll();
        for (Payment c : all) {
            allPayment.add(new PaymentDTO(c.getPayment_id(), c.getAppoiment_id(),c.getPatient_id(),c.getAmount(),c.getPayment_date()));
        }
        return allPayment;
    }

    @Override
    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(dto.getPayment_id(),dto.getAppoiment_id(), dto.getPatient_id(), dto.getAmount(), dto.getPayment_date()));
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getAppoiment_id(), dto.getPatient_id(), dto.getAmount(), dto.getPayment_date(),dto.getPayment_id()));
    }

    @Override
    public boolean existPayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.exist(id);
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }


    @Override
    public Payment searchPayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.search(id);
    }
}