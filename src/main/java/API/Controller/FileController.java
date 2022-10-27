package API.Controller;

import API.Entity.DTO.FilesDTO;
import API.Entity.DTO.FilesResponseDTO;
import API.Entity.Entity.Files;
import API.Service.FileStorageService;
import API.Utility.ResponseFile;
import API.Utility.ResponseMessage;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController

@RequestMapping("api/files/")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;


    @PostMapping("api/upload/{id}")
    public ResponseEntity<ResponseMessage> uploadFiles (@RequestParam ("file") MultipartFile files, @PathVariable int id) throws IOException {
        String message = "";
        try {
            fileStorageService.store(files, id);
            message = " uploaded the file successfully : " + files.getName() ;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }
        catch (IOException e) {
            message = "could not upload the file : " + files.getName();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("details/{id}")
    public ResponseEntity<?> fileId (@PathVariable int id){
       Files files = fileStorageService.getFile(id);
       return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("chateau/{id}")
    public ResponseEntity<?> filesByChateauId (@PathVariable int id){
        List<Files> files = fileStorageService.getFilesByChateauId(id);
        return  ResponseEntity.status(HttpStatus.OK).body(files);
    }

}
