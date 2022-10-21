package API.Service;

import API.Entity.DTO.ChateauSecureDTO;
import API.Entity.DTO.CommentaireSecureDTO;
import API.Entity.DTO.NewCommentaireDTO;
import API.Entity.Entity.Chateau;
import API.Entity.Entity.Commentaire;
import API.Repository.ChateauRepository;
import API.Repository.CommentaireRepository;
import API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentaireService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    CommentaireRepository commentaireRepository;

@Autowired
ChateauService chateauService;

@Autowired
UserService userService;

    /**
     * find commentaire by chateau_id
     * @param id
     * @return
     */
    public List<CommentaireSecureDTO> findByChateau_Id(int id) {
        logger.info("find commentaire by Chateau-id : " + id);
        List<Commentaire> commentaires = commentaireRepository.findByChateau_Id(id);
        List<CommentaireSecureDTO> commentaireSecureDTOS = convertListCommentaireToListCommentaireSecure(commentaires);
        return  commentaireSecureDTOS;
    }

    /**
     * List Commentaire by User_id
     * @param id
     * @return
     */
    public List<CommentaireSecureDTO> findByUser_Id(int id) {
        logger.info("find Commentaire By User_Id id : " + id);
        List<Commentaire> commentaires = commentaireRepository.findByUser_Id(id);
        List<CommentaireSecureDTO> commentaireSecureDTOS = convertListCommentaireToListCommentaireSecure(commentaires);
        return commentaireSecureDTOS;
    }

    /**
     * Create a new commentaire
     * @param newCommentaireDTO
     * @return
     */
    public Commentaire newCommentaire(NewCommentaireDTO newCommentaireDTO, String token) {
        List<Commentaire> commentaires = commentaireRepository.findByUser_IdAndChateau_Id(userService.connectedUserId(token).getId(), newCommentaireDTO.id_chateau);
        if(commentaires.size() == 0){
            Commentaire commentaire = new Commentaire();
            commentaire.setCommentaire(newCommentaireDTO.commentaire);
            commentaire.setChateau(chateauService.findById(newCommentaireDTO.id_chateau));
            commentaire.setUser(userService.findById(userService.connectedUserId(token).getId()));
            commentaireRepository.save(commentaire);
            logger.info("new commentaire id : " + commentaire.getId());
            return commentaire;
        }
        return null;
    }

    /**
     * Delete a commentaire
     * @param id
     */
    public void delete(int id) {
        Commentaire commentaire = commentaireRepository.findById(id).get();
        commentaireRepository.delete(commentaire);
        logger.info("delete commente id : " + id );
    }

    //Update commentaire if user is the owner

    /**
     * Update commentaire
     * @param commentaire
     * @return
     */
    public Commentaire updateCommentaire(Commentaire commentaire, String token) {
        Commentaire commentaireToUpdate = commentaireRepository.findById(commentaire.getId()).get();
        if(commentaireToUpdate.getUser().getId() == userService.connectedUserId(token).getId()){
            commentaireToUpdate.setCommentaire(commentaire.getCommentaire());
            commentaireRepository.save(commentaireToUpdate);
            logger.info("update commentaire id : " + commentaire.getId());
            return commentaireToUpdate;
        }
        return null;
    }


    /**
     * convert Commentaire to CommentaireSecureDTO
     * @param commentaire
     * @return
     */

    public CommentaireSecureDTO convertCommentaireToCommentaireSecure(Commentaire commentaire){
        CommentaireSecureDTO commentaireSecureDTO= new  CommentaireSecureDTO();
        commentaireSecureDTO.setId(commentaire.getId());
        commentaireSecureDTO.setCommentaire(commentaire.getCommentaire());
        commentaireSecureDTO.setCreated_at(commentaire.getCreated_at());
        commentaireSecureDTO.setChateau(chateauService.convertToSecure(commentaire.getChateau()));
        commentaireSecureDTO.setUser(userService.convertToSecure(commentaire.getUser()));
        logger.info("commentaire = " + commentaireSecureDTO.id );
        return commentaireSecureDTO;
    }



    /**
     * Convert List Commentaire to List CommentaireSecureDTO
     * @param commentaires
     * @return
     */

    public List<CommentaireSecureDTO>convertListCommentaireToListCommentaireSecure(List<Commentaire> commentaires){
        List<CommentaireSecureDTO> secureDTOs = new java.util.ArrayList<>();
        for (Commentaire commentaire : commentaires) {
            secureDTOs.add(convertCommentaireToCommentaireSecure(commentaire));
        }
        return secureDTOs;
    }

}
