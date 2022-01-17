package API.Entity.Entity;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity(name = "User")
@Table(name = "user")
public class User {

    @Id
    Integer id;

    String firstName;
    String lastName;
    String email;
    String password;
    String token;


}