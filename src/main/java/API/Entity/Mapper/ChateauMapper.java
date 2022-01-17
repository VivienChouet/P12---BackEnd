package API.Entity.Mapper;

import API.Entity.DTO.ChateauDto;
import API.Entity.Entity.Chateau;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChateauMapper extends EntityMapper<ChateauDto, Chateau>{
}
