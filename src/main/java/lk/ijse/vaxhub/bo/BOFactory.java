package lk.ijse.vaxhub.bo;


import lk.ijse.vaxhub.bo.custom.Impl.*;

public class BOFactory {
        private static BOFactory boFactory;
        private BOFactory(){
        }
        public static BOFactory getBoFactory(){
            return (boFactory==null)? boFactory=new BOFactory() : boFactory;
        }



    public enum BOTypes{
            Appoiment,DashBoard,Employee_Attendance,Employee,Patient,Payment,Place_Vaccination,Register,Vaccination,Vaccine,Login,SignUp
        }

        //Object creation logic for BO objects
        public SuperBO getBO(BOTypes types){
            switch (types){
                case Appoiment:
                    return new AppoimentBOImpl();
                case DashBoard:
                    return new DashBoardBOImpl();
                    case Employee_Attendance:
                    return new EmployeeAttendanceBOImpl();
                case Employee:
                    return new EmployeeBOImpl();
                case Patient:
                    return new PatientBOImpl();
                case Payment:
                    return new PaymenBOImpl();
                case Register:
                    return new RegisterBOImpl();
              case Place_Vaccination:
                    return new PlaceVaccinationBOImpl();
                case Vaccination:
                    return new VaccinationBOImpl();
                case Vaccine:
                    return new VaccineBOImpl();
                case Login:
                    return new LoginBOImpl();
                case SignUp:
                    return new SignUpBOImpl();
                    default:
                    return null;
            }
        }

    }

