package API.Entity.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private Integer id;

    public String firstName;
    public String lastName;
    public String email;
    public String password;
    private String token;

}
