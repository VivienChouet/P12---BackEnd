package API.Repository;

import API.Entity.Entity.Chateau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChateauRepository extends JpaRepository<Chateau, Integer> {
    List findByName(String name);
}