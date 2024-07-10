package lk.ijse.vaxhub.bo.custom;

import lk.ijse.vaxhub.bo.SuperBO;
import lk.ijse.vaxhub.dto.AppoimentDTO;
import lk.ijse.vaxhub.entity.Appoiment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AppoimentBO extends SuperBO {

    public ArrayList<AppoimentDTO> getAllAppoiment() throws SQLException, ClassNotFoundException;
    public boolean saveAppoiment(AppoimentDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateAppoiment(AppoimentDTO dto) throws SQLException, ClassNotFoundException;
    public boolean existAppoiment(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteAppoiment(String id) throws SQLException, ClassNotFoundException;
    public Appoiment searchAppoiment(String id) throws SQLException, ClassNotFoundException;
    public int getAppoimentCount() throws SQLException, ClassNotFoundException;
    List<String> getAIds() throws SQLException, ClassNotFoundException;
}

