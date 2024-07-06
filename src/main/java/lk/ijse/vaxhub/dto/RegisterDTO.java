package lk.ijse.vaxhub.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RegisterDTO {
    private String register_id;
    private String first_name;
    private String last_name;
    private String address;
    private String contact_number;
}




