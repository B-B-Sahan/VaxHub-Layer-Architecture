package lk.ijse.vaxhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class PlaceAppoimentDTO {
        private AppoimentDTO appoiment;
        private PaymentDTO payment;


    }



