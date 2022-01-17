package API.Entity.Mapper;

import API.Entity.DTO.RoleDto;
import API.Entity.Entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDto, Role> {
}
