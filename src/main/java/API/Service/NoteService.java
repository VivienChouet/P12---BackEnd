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

// delete note by id

    /**
     * Delete note by id
     * @param note
     */
    public void deleteNote(Note note) {
        logger.info("delete note : " + note.getId());
        noteRepository.delete(note);
    }

// find by id note

    /**
     * Find note by id
     * @param id
     * @return
     */
    public Note findById(int id) {
        logger.info("find note by id : " + id);
        return noteRepository.findById(id).get();
    }

//update Note only if the user is the owner of the note

    /**
     * Update note only if the user is the owner of the note
     * @param note
     * @return
     */
    public Note updateNote(Note note) {
        Note noteCheck = noteRepository.findById(note.getId()).get();
        if (noteCheck.getUser().getId() == note.getUser().getId()) {
            logger.info("update note : " + note.getId());
            noteRepository.save(note);
            return note;
        }
        return null;
    }
}



