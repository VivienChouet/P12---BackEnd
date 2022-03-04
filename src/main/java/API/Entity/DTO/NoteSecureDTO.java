package API.Entity.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class NoteSecureDTO implements Serializable {

    public  Integer id;
    public  Integer note;
    public  UserSecureDTO user;
    public  ChateauSecureDTO chateau;

}



