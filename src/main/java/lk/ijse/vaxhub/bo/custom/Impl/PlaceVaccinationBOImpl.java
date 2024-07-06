package lk.ijse.vaxhub.bo.custom.Impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceVaccinationBOImpl {
    private VaccineBOImpl vaccine;
    private VaccinationBOImpl vaccination;
}
