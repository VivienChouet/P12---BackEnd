package API.Entity.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChateauWithoutLinkDTO implements Serializable {

    public  Integer id;
    public  String name;
    public  Integer numeroAdresse;
    public  Integer codePostal;
    public  String adresse;
    public  Integer responsable;
}
