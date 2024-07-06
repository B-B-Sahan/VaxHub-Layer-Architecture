package lk.ijse.vaxhub.bo.custom.Impl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VaccinationBOImpl {
    private String patient_id;
    private String vaccine_id;
    private String vaccine_name;
    private String date;
}
