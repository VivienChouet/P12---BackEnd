package API.Controller;

import API.Entity.DTO.CommentaireSecureDTO;
import API.Entity.DTO.NewCommentaireDTO;
import API.Entity.Entity.Commentaire;
import API.Entity.Mapper.CommentaireMapper;
import API.Service.CommentaireService;
import API.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("api/commentaire")
public class CommentaireController {

    @Autowired
    CommentaireService commentaireService;

    @Autowired
    UserService userService;

    @Autowired
    CommentaireMapper commentaireMapper;

    @GetMapping("/chateau/{id}")
    public ResponseEntity<List<CommentaireSecureDTO>> commentairesByChateau(@PathVariable int id) {
        List<CommentaireSecureDTO> commentaires = commentaireService.findByChateau_Id(id);
        return new ResponseEntity<>(commentaires, HttpStatus.OK);
    }


    @GetMapping("/gestion/{id}")
    public ResponseEntity<List<CommentaireSecureDTO>> commentaireByUser_Id(@PathVariable int id) {
        List<CommentaireSecureDTO> commentaires = commentaireService.findByUser_Id(id);
        return new ResponseEntity<>(commentaires, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<Commentaire> newCommentaire(@RequestBody NewCommentaireDTO newCommentaireDTO, @RequestHeader("Authorization") String token) {
        Commentaire commentaire = commentaireService.newCommentaire(newCommentaireDTO, token);
        if (commentaire != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Commentaire> updateCommentaire(@PathVariable int id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/chateau/{id}")
    public ResponseEntity<Commentaire> deleteComment(@PathVariable int id ,@RequestHeader("Authorization") String token) {
        if(userService.verificationAdmin(token)) {
            commentaireService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }



}
