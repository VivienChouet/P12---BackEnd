package API.Entity.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDto implements Serializable {
public Integer id;
    public  Boolean admin;
    public  Boolean moderateur;
    public  UserDto user;
}
