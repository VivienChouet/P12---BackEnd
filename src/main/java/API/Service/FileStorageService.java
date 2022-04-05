package API.Service;

import API.Entity.Entity.Files;
import API.Repository.FileRepository;
import API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    private FileRepository fileRepository;



    public Files store (MultipartFile file) throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Files fileDB = new Files();
        fileDB.setName(fileName);
        fileDB.setData(file.getBytes());
        fileDB.setType(file.getContentType());
        fileRepository.save(fileDB);
        logger.info("upload file done : " + fileName);
        return fileDB;
    }

    public Files getFile (Integer id){
        logger.info("get file Id : " + id);
        return fileRepository.findById(id).get();
    }

    public Stream<Files> getAllFiles(){
        logger.info("get all files with stream");
        return fileRepository.findAll().stream();
    }

}
