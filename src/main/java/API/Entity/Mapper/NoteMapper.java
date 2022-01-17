package API.Entity.Mapper;

import API.Entity.DTO.NoteDto;
import API.Entity.Entity.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper extends EntityMapper<NoteDto, Note>{
}
