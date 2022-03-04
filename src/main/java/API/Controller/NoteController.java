package API.Controller;


import API.Entity.DTO.NewNoteDTO;
import API.Entity.DTO.NoteSecureDTO;
import API.Entity.Entity.Note;
import API.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping("/{id}")
    public List<Note> noteByChateau (@PathVariable int id){
        List notes = noteService.findByChateau_id(id);
        return notes;
    }

    @PostMapping("/")
    public ResponseEntity<Note> addNote (@RequestBody NewNoteDTO newNoteDTO, @RequestHeader("Authorization") String token){
        Note note1 = this.noteService.saveNewNote(newNoteDTO, token);
        if(note1 != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

// delete note by id
    @DeleteMapping("/{id}")
    public ResponseEntity<NoteSecureDTO> deleteNote (@PathVariable int id){
        Note note = noteService.findById(id);
        if(note != null) {
            noteService.deleteNote(note);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable int id, @RequestBody Note note){
        Note note1 = noteService.findById(id);
        if(note1 != null){
            note1.setNote(note.getNote());
            noteService.updateNote(note1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}






