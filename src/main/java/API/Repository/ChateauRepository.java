package API.Repository;

import API.Entity.Entity.Chateau;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChateauRepository extends JpaRepository<Chateau, Integer> {
}