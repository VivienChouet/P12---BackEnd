package API.Controller;

import API.Entity.Entity.Files;
import API.Service.FileStorageService;
import API.Service.UserService;
import API.Utility.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController

@RequestMapping("api/files/")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserService userService;


    @PostMapping("{id}")
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("file") MultipartFile files, @PathVariable int id) throws IOException {
        String message = "";
        try {
            fileStorageService.store(files, id);
            message = " uploaded the file successfully : " + files.getName();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (IOException e) {
            message = "could not upload the file : " + files.getName();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> fileId(@PathVariable int id) {
        Files files = fileStorageService.getFile(id);
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("chateau/{id}")
    public ResponseEntity<?> filesByChateauId(@PathVariable int id) {
        List<Files> files = fileStorageService.getFilesByChateauId(id);
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Files> deleteFilesById(@PathVariable int id, @RequestHeader("Authorization") String token) throws IOException {
        if (userService.verificationAdmin(token)) {
            fileStorageService.deleteFile(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
