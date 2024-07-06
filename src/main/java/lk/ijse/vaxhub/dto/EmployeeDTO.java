package lk.ijse.vaxhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeDTO {
    private String employee_id;
    private String first_name;
    private String last_name;
    private String role;
    private String email;
    private String address;
    private String contact_number;


    }
