package API.Entity.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "chateau_id")
    private Chateau chateau;

}
