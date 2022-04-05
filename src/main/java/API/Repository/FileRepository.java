package API.Repository;

import API.Entity.Entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Files, Integer> {
}