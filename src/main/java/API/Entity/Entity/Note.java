package API.Entity.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "note")
public class Note {

    @Id
    Integer id;

    Integer note;

    @ManyToOne
    private User user;

    @ManyToOne
    private Chateau chateau;

}
