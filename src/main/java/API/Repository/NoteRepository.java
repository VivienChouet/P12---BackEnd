package API.Repository;

import API.Entity.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List findByChateau_Id(int id);

    List<Note> findByChateau_IdAndUser_Id(Integer id, Integer id1);
}