package API.Entity.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChateauDto implements Serializable {

    public  Integer id;
    public  String name;
    public  Integer numeroAdresse;
    public  Integer codePostal;
    public  String adresse;
    public  String description;
    public  String localisation;
    public  UserDto responsable;
}
