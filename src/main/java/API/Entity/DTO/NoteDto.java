package API.Entity.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class NoteDto implements Serializable {
    public  Integer id;
    public  Integer note;
    public  UserDto user;
    public  ChateauDto chateau;
}
