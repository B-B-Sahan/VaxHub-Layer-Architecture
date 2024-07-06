package lk.ijse.vaxhub.dao.custom.Impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceVaccinationDAOImpl {
    private VaccineDAOImpl vaccine;
    private VaccinationDAOImpl vaccination;
}
