package lk.ijse.vaxhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class userDTO {
    private String user_id;
    private String name;
    private String password;
    private String email;
}
