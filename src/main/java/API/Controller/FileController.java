package API.Controller;

import API.Entity.Entity.Files;
import API.Service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;



    @PostMapping("/upload")
    public ResponseEntity<Files> upload () {


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/files")
    public ResponseEntity<Files> files () {


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity <Files> idFile (@PathVariable int id){


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
