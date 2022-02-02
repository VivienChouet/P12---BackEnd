package API.Controller;

import API.Entity.DTO.ChateauDto;
import API.Entity.DTO.ChateauSecureDTO;
import API.Entity.Entity.Chateau;
import API.Entity.Mapper.ChateauMapper;
import API.Service.ChateauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/chateau")
public class ChateauController {

    @Autowired
    ChateauService chateauService;

    @Autowired
    ChateauMapper chateauMapper;


    @PostMapping("/")
    public ResponseEntity<ChateauDto> newChateau (@RequestBody ChateauSecureDTO chateauDto){
      //todo securiser cette methode en amdin only
        chateauService.saveNewChateau(chateauDto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChateauSecureDTO> chateauById (@PathVariable int id){
        Chateau chateau = chateauService.findById(id);
        if (chateau != null) {
        ChateauSecureDTO secureDTO = chateauService.convertToSecure(chateau);
            return new ResponseEntity<>(secureDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<ChateauDto>> listAllChateau (){
        List chateaux = chateauService.findAll();
        List chateauSecureDTO = chateauService.convertToChateauSecureDTO(chateaux);
        return new ResponseEntity<>(chateauSecureDTO, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<ChateauDto>> searchChateauByName (@RequestBody ChateauDto chateauDto){
        System.out.println("ca plante ici ou après ?");
        List chateaux = chateauService.findByName(chateauDto.getName());
        if (chateaux.size()!=0){
            return new ResponseEntity<>(chateauMapper.toDto(chateaux),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Chateau> deleteChateau (@PathVariable int id){
        chateauService.deleteChateau(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
