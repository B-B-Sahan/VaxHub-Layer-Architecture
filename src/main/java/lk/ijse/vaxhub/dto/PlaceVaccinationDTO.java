package lk.ijse.vaxhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceVaccinationDTO {
    private VaccineDTO vaccine;
    private VaccinationDTO vaccination;
}
