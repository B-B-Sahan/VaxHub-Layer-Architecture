package lk.ijse.vaxhub.dao.custom.Impl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VaccinationDAOImpl {
    private String patient_id;
    private String vaccine_id;
    private String vaccine_name;
    private String date;
}
