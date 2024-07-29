package lk.ijse.vaxhub.bo.custom;

import lk.ijse.vaxhub.bo.SuperBO;

import java.sql.SQLException;

public interface DashBoardBO extends SuperBO {
    public int getAppoimentCount() throws SQLException, ClassNotFoundException;
    public int getEmpolyeeAttendance() throws SQLException, ClassNotFoundException;
}
