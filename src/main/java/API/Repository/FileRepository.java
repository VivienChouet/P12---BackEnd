package API.Repository;

import API.Entity.Entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

public interface FileRepository extends JpaRepository<Files, Integer> {

    List<Files> findByChateau_id(int id);
}