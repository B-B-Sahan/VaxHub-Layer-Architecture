package lk.ijse.vaxhub.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaymentTm {
    private String payment_id;
    private String appoiment_id;
    private String patient_id;
    private String amount;
    private String payment_date;


}
