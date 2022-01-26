package API.Controller;

import API.Entity.DTO.CommentaireDto;
import API.Entity.Entity.Commentaire;
import API.Entity.Mapper.CommentaireMapper;
import API.Service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentaire")
public class CommentaireController {

    @Autowired
    CommentaireService commentaireService;

    @Autowired
    CommentaireMapper commentaireMapper;

    // Get by chateau_id
    @GetMapping("/chateau/{id}")
    public ResponseEntity<List<CommentaireDto>> commentairesByChateau(@PathVariable int id) {
        List<Commentaire> commentaires = commentaireService.findByChateau_Id(id);
        return new ResponseEntity<>(commentaireMapper.toDto(commentaires), HttpStatus.OK);
    }

    // get by user_id en admin / modo only
    //todo : méthode a sécuriser modo / admin only.
    @GetMapping("/gestion/{id}")
    public ResponseEntity<List<CommentaireDto>> commentaireByUser_Id(@PathVariable int id) {
        List<Commentaire> commentaires = commentaireService.findByUser_Id(id);
        return new ResponseEntity<>(commentaireMapper.toDto(commentaires), HttpStatus.OK);
    }

    // post
    @PostMapping("/")
    public ResponseEntity<Commentaire> newCommentaire(@RequestBody CommentaireDto commentaireDto) {
        Commentaire commentaire = commentaireService.newCommentaire(commentaireMapper.toEntity(commentaireDto));
        if (commentaire != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    // update by creator only
@PutMapping("/{id}")
    public ResponseEntity<Commentaire> updateCommentaire(@PathVariable int id){


        return new ResponseEntity<>(HttpStatus.OK);
}

    // delete by Modo / Admin only

    // update report by user



}
