package API.Controller;

import API.Entity.DTO.CommentaireSecureDTO;
import API.Entity.DTO.NewCommentaireDTO;
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
    public ResponseEntity<List<CommentaireSecureDTO>> commentairesByChateau(@PathVariable int id) {
        List<CommentaireSecureDTO> commentaires = commentaireService.findByChateau_Id(id);
        return new ResponseEntity<>(commentaires, HttpStatus.OK);
    }

    // get by user_id en admin / modo only
    //todo : méthode a sécuriser modo / admin only.
    @GetMapping("/gestion/{id}")
    public ResponseEntity<List<CommentaireSecureDTO>> commentaireByUser_Id(@PathVariable int id) {
        List<CommentaireSecureDTO> commentaires = commentaireService.findByUser_Id(id);
        return new ResponseEntity<>(commentaires, HttpStatus.OK);
    }

    // post
    @PostMapping("/")
    public ResponseEntity<Commentaire> newCommentaire(@RequestBody NewCommentaireDTO newCommentaireDTO, @RequestHeader("Authorization") String token) {
        Commentaire commentaire = commentaireService.newCommentaire(newCommentaireDTO, token);
        if (commentaire != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    // update by creator only
    @PutMapping("/{id}")
    public ResponseEntity<Commentaire> updateCommentaire(@PathVariable int id) {


        return new ResponseEntity<>(HttpStatus.OK);
    }

    // delete by Modo / Admin only
    @DeleteMapping("{id}")
    public ResponseEntity<Commentaire> deleteComment(@PathVariable int id) {
        commentaireService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // update report by user


}
