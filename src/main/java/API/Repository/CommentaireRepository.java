package API.Repository;

import API.Entity.Entity.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
    List<Commentaire> findByChateau_Id(int id);

    List<Commentaire> findByUser_Id(int id);

    List<Commentaire> findByUser_IdAndChateau_Id(Integer id, Integer id1);
}