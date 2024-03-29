package API.Entity.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChateauSecureDTO implements Serializable {

    public  Integer id;
    public  String name;
    public  Integer numeroAdresse;
    public  Integer codePostal;
    public  String adresse;
    public String description;
    public Double lat;
    public Double lng;
    public String ville;
    public  UserSecureDTO responsable;
}
