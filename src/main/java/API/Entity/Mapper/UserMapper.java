package API.Entity.Mapper;
import API.Entity.DTO.UserDto;
import API.Entity.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User>  {
}
