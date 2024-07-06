package lk.ijse.vaxhub.dao.custom.Impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientDAOImpl {

    private String patient_id;
    private String register_id;
    private String first_name;
    private String last_name;
    private String gender;
    private String birth_day;
    private String email;
    private String address;
    private String contact_number;
    private String  date_administer;
    private String adverse_reaction;
    private String vaccine_name;
    private String  weight_value;
    private String hight_value;

}


