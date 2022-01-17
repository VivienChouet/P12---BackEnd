package API.Entity.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentaireDto implements Serializable {

    public  Integer id;
    public  String commentaire;
    public  Date created_at;
    public  ChateauDto chateau;
    public  UserDto user;
}
