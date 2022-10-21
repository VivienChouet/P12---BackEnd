package API.Entity.DTO;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class FilesDTO implements Serializable {

    Integer id;
    String name;
    String image;

}
