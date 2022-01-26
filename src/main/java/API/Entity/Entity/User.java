package API.Entity.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public String firstName;
    public String lastName;
    public String email;
    public String password;
    private String token;


}