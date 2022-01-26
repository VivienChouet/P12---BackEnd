package API.Service;

import API.Entity.DTO.ChateauDto;
import API.Entity.DTO.ChateauWithoutLinkDTO;
import API.Entity.Entity.Chateau;
import API.Repository.ChateauRepository;
import API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChateauService {

  Logger logger = LoggerFactory.getLogger(LoggingController.class);

  @Autowired
    ChateauRepository chateauRepository;

  @Autowired
  UserService userService;

    /**
     * Add new Chateau
     * @param chateauDto
     * @return
     */
    public Chateau saveNewChateau(ChateauWithoutLinkDTO chateauDto) {
        logger.info("chateau DTO test = " + chateauDto);
        logger.info("Chateau DTO test id = " + chateauDto.getResponsable());
        Chateau chateau = new Chateau();
        chateau.setAdresse(chateauDto.getAdresse());
        chateau.setNumeroAdresse(chateauDto.getNumeroAdresse());
        chateau.setCodePostal(chateauDto.getCodePostal());
        chateau.setName(chateauDto.getName());
        chateau.setResponsable(userService.findById(chateauDto.getResponsable()));
        chateauRepository.save(chateau);
        logger.info("save New Chateau = " + chateau.getName());
        return chateau;
    }

    /**
     * Delete a chateau with id
     * @param id
     */
    public void deleteChateau(int id) {
        Chateau chateau = chateauRepository.findById(id).get();
        chateauRepository.delete(chateau);
        logger.info("delete chateau id : " + id);
    }

    /**
     * Find Chateau by Id
     * @param id
     * @return Chateau
     */
    public Chateau findById(int id) {
        logger.info("Chateau Find By id : " + id);
        Chateau chateau = chateauRepository.findById(id).get();
        return  chateau;
    }

    /**
     * Find all Chateau
     * @return List Chateau
     */
    public List findAll() {
        logger.info("Find all Chateau ");
        List chateaux = chateauRepository.findAll();
        return  chateaux;
    }

    /**
     * Search List Chateaux By Name
     * @param name
     * @return List Chateau
     */
    public List findByName(String name) {
        logger.info("search chateau : " + name);
        List chateaux = chateauRepository.findByName(name);
        return chateaux;
    }
}
