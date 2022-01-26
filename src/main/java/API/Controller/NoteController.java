package API.Controller;


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
    public ResponseEntity<Note> addNote (@RequestBody Note note){
        Note note1 = this.noteService.saveNewNote(note);
        if(note1 != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }


}
