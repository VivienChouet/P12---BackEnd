package API.Service;

import API.Entity.Entity.Commentaire;
import API.Repository.CommentaireRepository;
import API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    CommentaireRepository commentaireRepository;

    /**
     * Find all commentaire by Chateau_Id
     * @param id
     * @return List<Commentaire>
     */
    public List<Commentaire> findByChateau_Id(int id) {
        logger.info("find commentaire by Chateau-id : " + id);
        List<Commentaire> commentaires = commentaireRepository.findByChateau_Id(id);
        return  commentaires;
    }

    /**
     * List Commentaire by User_id
     * @param id
     * @return
     */
    public List<Commentaire> findByUser_Id(int id) {
        logger.info("find Commentaire By User_Id id : " + id);
        List<Commentaire> commentaires = commentaireRepository.findByUser_Id(id);
        return commentaires;
    }

    /**
     * Add New commentaire
     * @param commentaire
     * @return
     */
    public Commentaire newCommentaire(Commentaire commentaire) {
        List<Commentaire> commentaires = commentaireRepository.findByUser_IdAndChateau_Id(commentaire.getUser().getId(), commentaire.getChateau().getId());
        if(commentaires.size() != 0){
            commentaireRepository.save(commentaire);
            logger.info("new commentaire id : " + commentaire.getId());
            return commentaire;
        }
        return null;
    }
}
