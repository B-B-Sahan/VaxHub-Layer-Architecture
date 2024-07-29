package lk.ijse.vaxhub.bo.custom;

import lk.ijse.vaxhub.bo.SuperBO;
import lk.ijse.vaxhub.dto.PlaceVaccinationDTO;
import lk.ijse.vaxhub.entity.PlaceVaccination;

import java.sql.SQLException;

public interface PlaceVaccinationBO extends SuperBO {
    public boolean placeVaccination(PlaceVaccination po) throws SQLException, ClassNotFoundException;
}
