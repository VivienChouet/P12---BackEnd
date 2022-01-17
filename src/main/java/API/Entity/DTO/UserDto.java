package API.Entity.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    public  Integer id;
    public  String firstName;
    public  String lastName;
    public  String email;
    public  String password;
    public  String token;
}
