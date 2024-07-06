package lk.ijse.vaxhub.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RegisterTm {
    private String register_id;
    private String first_name;
    private String last_name;
    private String address;
    private String contact_number;
}
