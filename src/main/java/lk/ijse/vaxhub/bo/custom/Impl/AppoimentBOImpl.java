package lk.ijse.vaxhub.bo.custom.Impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AppoimentBOImpl {
    private String appoiment_id;
    private String employee_id;
    private String patient_id;
    private String appoiment_date;
    private String appoiment_time;


}



