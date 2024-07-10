package lk.ijse.vaxhub.dao.custom.Impl;

import lk.ijse.vaxhub.dao.SQLUtil;
import lk.ijse.vaxhub.dao.custom.PaymentDAO;
import lk.ijse.vaxhub.entity.Appoiment;
import lk.ijse.vaxhub.entity.Employee;
import lk.ijse.vaxhub.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> allPayment = new ArrayList<>();
        ResultSet rst = SQLUtil.execute( "SELECT * FROM payment");
        while (rst.next()) {
            Payment payment = new Payment(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5));
            allPayment.add(payment);
        }
        return allPayment;
    }

    @Override
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(" INSERT INTO payment (payment_id,appoiment_id,patient_id,amount,payment_date) VALUES (?,?,?,?,?)", entity.getPayment_id(),entity.getAppoiment_id(),entity.getPatient_id(),entity.getAmount(),entity.getPayment_date());
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE payment SET appoiment_id=?,patient_id=?,amount=?, payment_date=?WHERE payment_id=?",entity.getAppoiment_id(),entity.getPatient_id(),entity.getAmount(),entity.getPayment_date(),entity.getPayment_id());
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
        return SQLUtil.execute("DELETE FROM payment WHERE patient_id=?",id);
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM appoiment WHERE  patient_id=?", id + "");
        rst.next();
        return new Payment(id + "", rst.getString("payment_id"), rst.getString("appoiment_id"),rst.getString("amount"),rst.getString("payment_date"));
    }
}