package API.Entity.Entity;


import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "chateau")
public class Chateau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Integer id;

   public String name;
   public Integer numeroAdresse;
   public Integer codePostal;
   public String adresse;

    @ManyToOne
    private User responsable;

}
