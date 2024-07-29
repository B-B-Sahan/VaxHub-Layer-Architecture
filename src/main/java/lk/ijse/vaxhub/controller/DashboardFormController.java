package lk.ijse.vaxhub.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import lk.ijse.vaxhub.bo.BOFactory;
import lk.ijse.vaxhub.bo.custom.AppoimentBO;
import lk.ijse.vaxhub.bo.custom.DashBoardBO;

import java.sql.SQLException;

public class DashboardFormController {
    @FXML
    private BarChart<?, ?> paymentBarchart;

    @FXML
    private Label appoimentcountLable;

    @FXML
    private Label employeecountLable;

    @FXML
    private NumberAxis paymentValueColumn;

    @FXML
    private AreaChart<?, ?> paymentchart;

    @FXML
    private Label useridLable;

    @FXML
    private Label usernameLable;

    DashBoardBO dashBoardBO = (DashBoardBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DashBoard);

    public void initialize() {

        try {
            int appoimentCount = dashBoardBO.getAppoimentCount();
            setappoimentCount(appoimentCount);

            int employeeCount = dashBoardBO.getEmpolyeeAttendance();
            setEmoployeeCount(employeeCount);


        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void setappoimentCount(int appoimentCount){appoimentcountLable.setText(String.valueOf(appoimentCount));}

    private void setEmoployeeCount(int employeeCount) {employeecountLable.setText(String.valueOf(employeeCount));}
}