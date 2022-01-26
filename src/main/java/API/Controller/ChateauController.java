package API.Controller;

import API.Entity.DTO.ChateauDto;
import API.Entity.DTO.ChateauWithoutLinkDTO;
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
    public ResponseEntity<ChateauDto> newChateau (@RequestBody ChateauWithoutLinkDTO chateauDto){
      //todo securiser cette methode en amdin only
        chateauService.saveNewChateau(chateauDto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChateauDto> chateauById (@PathVariable int id){
        Chateau chateau = chateauService.findById(id);
        if (chateau != null) {
            return new ResponseEntity<ChateauDto>(chateauMapper.toDto(chateau), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<ChateauDto>> listAllChateau (){
        List chateaux = chateauService.findAll();
        return new ResponseEntity<>(chateauMapper.toDto(chateaux), HttpStatus.OK);
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
