package API.Service;

import API.Entity.DTO.FilesDTO;
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
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ChateauService chateauService;


    public Files store (MultipartFile file, int id) throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Files fileDB = new Files();
        fileDB.setName(fileName);
        fileDB.setData(file.getBytes());
        fileDB.setType(file.getContentType());
        fileDB.setChateau(chateauService.findById(id));
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

    public void deleteFile(int id) throws IOException {
        fileRepository.delete(fileRepository.findById(id).get());
    }

    public Stream<Files> getFilesByChateau(int id){
        logger.info("files by chateau : " + id );
        return fileRepository.findByChateau_id(id).stream();
    }

    public List<Files> getFilesByChateauId(int id) {
        logger.info("files by chateau id sans streamn : " + id);
        return  fileRepository.findByChateau_id(id);
    }

    public void newFile(FilesDTO filesDTO, int id) {
        logger.info("test new save image");
        Files files = new Files();
        files.setChateau(chateauService.findById(id));
        files.setName(filesDTO.getName());
        files.setImage(filesDTO.getImage());
        fileRepository.save(files);
    }
}
