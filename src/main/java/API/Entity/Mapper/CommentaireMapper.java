package API.Entity.Mapper;

import API.Entity.DTO.CommentaireDto;
import API.Entity.Entity.Commentaire;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentaireMapper extends EntityMapper<CommentaireDto, Commentaire> {
}
