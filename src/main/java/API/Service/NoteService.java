package API.Service;

import API.Entity.Entity.Note;
import API.Repository.NoteRepository;
import API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    NoteRepository noteRepository;

    /**
     * Find all evaluation with chateau_id
     * @param id
     * @return List<Note>
     */
    public List findByChateau_id(int id) {
        logger.info("find Notes On chateau_id : " + id);
        return noteRepository.findByChateau_Id(id);
    }

    /**
     * Add new Note with check if the user have already done a evaluation
     * @param note
     * @return note if its ok
     * @return null if its not ok
     */
    public Note saveNewNote(Note note) {
        List<Note> notesCheck = noteRepository.findByChateau_IdAndUser_Id(note.getChateau().getId(), note.getUser().getId());
        if (notesCheck.size() == 0) {
            noteRepository.save(note);
            logger.info("save new note : " + note.getId());
            return note;
        }
        return null;
    }


}
