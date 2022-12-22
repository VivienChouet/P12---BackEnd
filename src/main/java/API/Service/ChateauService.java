package API.Service;

import API.Entity.DTO.ChateauSecureDTO;
import API.Entity.Entity.Chateau;
import API.Entity.Entity.User;
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
    public Chateau saveNewChateau(ChateauSecureDTO chateauDto, String token) {
        logger.info("chateau DTO test = " + chateauDto);
        logger.info("Chateau DTO test id = " + chateauDto.getResponsable());
        Chateau chateau = new Chateau();
        chateau.setAdresse(chateauDto.getAdresse());
        chateau.setNumeroAdresse(chateauDto.getNumeroAdresse());
        chateau.setCodePostal(chateauDto.getCodePostal());
        chateau.setName(chateauDto.getName());
        chateau.setDescription(chateauDto.getDescription());
        chateau.setResponsable(userService.findUserByToken(token));
        chateau.setLat(chateauDto.getLat());
        chateau.setLng(chateauDto.getLng());
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
     * Convert list Chateau to list ChateauSecureDTO
     * @param chateaux
     * @return
     */
    public List<ChateauSecureDTO> convertToChateauSecureDTO(List<Chateau> chateaux) {
        List<ChateauSecureDTO> secureDTOs = new java.util.ArrayList<>();
        for (Chateau chateau : chateaux) {
            secureDTOs.add(convertToSecure(chateau));
        }
        return secureDTOs;
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

    public ChateauSecureDTO convertToSecure(Chateau chateau) {
        ChateauSecureDTO secureDTO = new ChateauSecureDTO();
        secureDTO.setId(chateau.getId());
        secureDTO.setName(chateau.getName());
        secureDTO.setAdresse(chateau.getAdresse());
        secureDTO.setCodePostal(chateau.getCodePostal());
        secureDTO.setVille(chateau.getVille());
        secureDTO.setNumeroAdresse(chateau.getNumeroAdresse());
        secureDTO.setDescription(chateau.getDescription());
        secureDTO.setLat(chateau.getLat());
        secureDTO.setLng(chateau.getLng());
        secureDTO.setResponsable(userService.convertToSecure(chateau.getResponsable()));
        logger.info("convert DTO Chateau to a Secure DTO");
        return secureDTO;
    }

    public List<String> listChateauName(){
        List<String> listChateauName = null;
        List<Chateau> chateaux = findAll();
        for (Chateau chateau : chateaux) {
            listChateauName.add(chateau.getName());
        }
        return listChateauName;
    }

    public List<ChateauSecureDTO>listMyChateau(String token){
        User user = userService.connectedUserId(token);
        List<Chateau> MyChateau = chateauRepository.findByResponsable(user);
        List<ChateauSecureDTO> MyChateauSecure = convertToChateauSecureDTO(MyChateau);
        return MyChateauSecure;
    }

    public Chateau updateChateau(String token, ChateauSecureDTO chateauSecureDTO){
        logger.info("chateau DTO test = " + chateauSecureDTO.toString());
        Chateau oldChateau = findById(chateauSecureDTO.id);
        User user = userService.connectedUserId(token);
        if(user == oldChateau.getResponsable()) {
            oldChateau.setAdresse(chateauSecureDTO.getAdresse());
            oldChateau.setNumeroAdresse(chateauSecureDTO.getNumeroAdresse());
            oldChateau.setCodePostal(chateauSecureDTO.getCodePostal());
            oldChateau.setVille(chateauSecureDTO.getVille());
            oldChateau.setName(chateauSecureDTO.getName());
            oldChateau.setDescription(chateauSecureDTO.getDescription());
            oldChateau.setResponsable(userService.findUserByToken(token));
            oldChateau.setLat(chateauSecureDTO.getLat());
            oldChateau.setLng(chateauSecureDTO.getLng());
            chateauRepository.save(oldChateau);
            logger.info("save New Chateau = " + oldChateau.getName());
            return oldChateau;
        }
    return  null;
    }

}
