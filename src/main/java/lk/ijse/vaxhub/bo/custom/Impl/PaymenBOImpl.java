package lk.ijse.vaxhub.bo.custom.Impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaymenBOImpl {
    private String payment_id;
    private String appoiment_id;
    private String patient_id;
    private String amount;
    private String payment_date;


}