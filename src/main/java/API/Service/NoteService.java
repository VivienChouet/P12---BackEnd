package API.Service;

import API.Entity.DTO.NewNoteDTO;
import API.Entity.DTO.NoteSecureDTO;
import API.Entity.Entity.Note;
import API.Repository.NoteRepository;
import API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    ChateauService chateauService;

    @Autowired
    UserService userService;

    /**
     * Find all evaluation with chateau_id
     * @param id
     * @return List<Note>
     */
    public List findByChateau_id(int id) {
        logger.info("find Notes On chateau_id : " + id);
        List<Note> notes = noteRepository.findByChateau_Id(id);
        List<NoteSecureDTO> notesSecure = convertListToSecureNote(notes);
        return notesSecure;
    }

    /**
     * Create new note
     * @param newNoteDTO
     * @return
     */
    public Note saveNewNote(NewNoteDTO newNoteDTO, String token) {
        List<Note> notesCheck = noteRepository.findByChateau_IdAndUser_Id(newNoteDTO.getChateau_id(), userService.connectedUserId(token).getId());
        if (notesCheck.size() == 0) {
            Note note = new Note();
            note.setChateau(chateauService.findById(newNoteDTO.getChateau_id()));
            note.setUser(userService.connectedUserId(token));
            note.setNote(newNoteDTO.getNote());
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
        Note note = noteRepository.findById(id).get();
        return  note;
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

    /**
     * Convert List to secure List note
     * @param notes
     * @return
     */
    public List<NoteSecureDTO> convertListToSecureNote (List<Note> notes) {
        List<NoteSecureDTO> notesSecure = new ArrayList<>();
        for (Note note : notes) {
            NoteSecureDTO noteSecureDTO = new NoteSecureDTO();
            noteSecureDTO.setId(note.getId());
            noteSecureDTO.setNote(note.getNote());
            noteSecureDTO.setUser(userService.convertToSecure(note.getUser()));
            noteSecureDTO.setChateau(chateauService.convertToSecure(note.getChateau()));
            notesSecure.add(noteSecureDTO);
        }
        return notesSecure;
    }

    /**
     * convert to secure note
     * @param note
     * @return
     */
    public NoteSecureDTO convertToSecureNote(Note note) {
        NoteSecureDTO noteSecureDTO = new NoteSecureDTO();
        noteSecureDTO.setId(note.getId());
        noteSecureDTO.setNote(note.getNote());
        noteSecureDTO.setUser(userService.convertToSecure(note.getUser()));
        noteSecureDTO.setChateau(chateauService.convertToSecure(note.getChateau()));
        return noteSecureDTO;
    }


}



