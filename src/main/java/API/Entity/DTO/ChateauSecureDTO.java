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
    public UserSecureDTO responsable;
}
