package lk.ijse.vaxhub.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VaccinationDTO {
    private String patient_id;
    private String vaccine_id;
    private String vaccine_name;
    private String date;
}
