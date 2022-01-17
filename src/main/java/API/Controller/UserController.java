package API.Controller;

import API.Entity.DTO.UserDto;
import API.Entity.Mapper.UserMapper;
import API.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Resource
    UserMapper userMapper;

    @PostMapping("/")
    public ResponseEntity<UserDto> newUser (@RequestBody UserDto userDto){
        System.out.println("user to save = " + userDto);
        userService.saveNewUser(userMapper.toEntity(userDto));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
