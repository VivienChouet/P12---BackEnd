package API.Controller;

import API.Entity.DTO.ChateauDto;
import API.Entity.DTO.ChateauSecureDTO;
import API.Entity.Entity.Chateau;
import API.Entity.Mapper.ChateauMapper;
import API.Service.ChateauService;
import API.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/chateau")
public class ChateauController {

    @Autowired
    ChateauService chateauService;

    @Autowired
    UserService userService;

    @Autowired
    ChateauMapper chateauMapper;


    @PostMapping("/")
    public ResponseEntity<ChateauDto> newChateau (@RequestBody ChateauSecureDTO chateauDto, @RequestHeader("Authorization") String token){

        chateauService.saveNewChateau(chateauDto, token);
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
        List chateaux = chateauService.findByName(chateauDto.getName());
        if (chateaux.size()!=0){
            return new ResponseEntity<>(chateauMapper.toDto(chateaux),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Chateau> deleteChateau (@PathVariable int id, @RequestHeader("Authorization") String token){
        if(userService.verificationAdmin(token)){
            chateauService.deleteChateau(id);
            return new ResponseEntity(HttpStatus.OK);
        }
return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/list/name")
    public ResponseEntity<List<String>> listNameChateau(){
        return new ResponseEntity<>(chateauService.listChateauName(), HttpStatus.OK);
    }

    @GetMapping("/mychateau")
    public ResponseEntity<List<ChateauSecureDTO>> listMyChateau(@RequestHeader("Authorization") String token){
        return new ResponseEntity<>(chateauService.listMyChateau(token),HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ChateauDto> updateChateau (@RequestHeader("Authorization") String token, @RequestBody ChateauSecureDTO chateauSecureDTO ){
        chateauService.updateChateau(token,chateauSecureDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
