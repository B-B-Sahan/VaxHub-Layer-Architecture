package lk.ijse.vaxhub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class Vaccine {
        private String vaccine_id;
        private String employee_id;
        private String vaccine_name;
        private String vaccine_date;
        private String manufacture;
        private String quantity;



    }


