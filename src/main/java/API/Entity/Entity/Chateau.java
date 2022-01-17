package API.Entity.Entity;


import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "chateau")
public class Chateau {

    @Id
    Integer id;

    String name;

    Integer numeroAdresse;
    Integer codePostal;
    String adresse;

    @ManyToOne
    private User responsable;

}
