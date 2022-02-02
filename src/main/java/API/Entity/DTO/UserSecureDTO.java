package API.Entity.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserSecureDTO implements Serializable {

     public Integer id;
     public String firstName;
     public String lastName;
     public String email;
     public String role;

}
