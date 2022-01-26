package API.Entity.Entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "commentaire")
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String commentaire;

    @CreationTimestamp
    private Date created_at = new Date();

    @ManyToOne
    private Chateau chateau;

    @ManyToOne
    private User user;

}
