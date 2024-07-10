package lk.ijse.vaxhub.bo.custom;

import lk.ijse.vaxhub.bo.SuperBO;
import lk.ijse.vaxhub.dto.PlaceVaccinationDTO;

public interface PlaceVaccinationBO extends SuperBO {
    boolean placeVaccination(PlaceVaccinationDTO placeVaccinationDTO);
}
