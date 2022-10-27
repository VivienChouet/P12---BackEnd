package API.Repository;

import API.Entity.Entity.Chateau;
import API.Entity.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChateauRepository extends JpaRepository<Chateau, Integer> {
    List findByName(String name);

    List<Chateau> findByResponsable(User user);
}