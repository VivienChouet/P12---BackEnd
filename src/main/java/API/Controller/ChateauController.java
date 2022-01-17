package API.Controller;

import API.Entity.DTO.ChateauDto;
import API.Entity.Mapper.ChateauMapper;
import API.Service.ChateauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/chateau")
public class ChateauController {

    @Autowired
    ChateauService chateauService;

    @Autowired
    ChateauMapper chateauMapper;

    @PostMapping("/")
    public ResponseEntity<ChateauDto> newChateau (@RequestBody ChateauDto chateauDto){
        chateauService.saveNewChateau(chateauMapper.toEntity(chateauDto));
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
