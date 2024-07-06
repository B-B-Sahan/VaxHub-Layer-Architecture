package lk.ijse.vaxhub.dao;

import lk.ijse.vaxhub.dao.custom.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
       APPOIMENT,EMPLOYEE_ATTENDANCE,EMPLOYEE,HIEGHT,INFORMATION_SYSTEM,PATIENT,PAYMENT,PLACE_APPOIMENT,PLACE_VACCINATION,REGISTER,REPORT,USER,VACCINATION,VACCINE
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case APPOIMENT:
                return new AppoimentDAOImpl();
            case EMPLOYEE_ATTENDANCE:
                return new EmployeeAttendanceDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case HIEGHT:
                return new HieghtDAOImpl();
            case INFORMATION_SYSTEM:
                return new InformationSystemDAOImpl();
            case PATIENT:
                return new PatientDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PLACE_APPOIMENT:
                return new PlaceAppoimentDAOImpl();
            case PLACE_VACCINATION:
                return new PlaceVaccinationDAOImpl();
            case REGISTER:
                return new RegisterDAOImpl();
            case REPORT:
                return new ReportDAOImpl();
            case USER:
                return new UserDAOImpl();
            case VACCINATION:
                return new VaccinationDAOImpl();
            case VACCINE:
                return new VaccineDAOImpl();



            default:
                return null;
        }
    }
}
