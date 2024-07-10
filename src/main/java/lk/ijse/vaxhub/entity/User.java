package lk.ijse.vaxhub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String user_id;
    private String name;
    private String password;
    private String email;

}
