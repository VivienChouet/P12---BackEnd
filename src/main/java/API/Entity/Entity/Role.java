package API.Entity.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    Integer id;


    Boolean admin;
    Boolean moderateur;

    @OneToOne
    private User user;


}
