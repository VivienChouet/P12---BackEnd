package API.Controller;

import API.Entity.DTO.UserDto;
import API.Entity.Entity.User;
import API.Entity.Mapper.UserMapper;
import API.Service.UserService;
import API.Utility.Security.JWT;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.jsonwebtoken.impl.crypto.JwtSignatureValidator;
import io.swagger.v3.oas.annotations.Operation;
import jdk.management.resource.internal.ResourceNatives;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;


    /**
     * Save a New User
     * @param userDto
     * @return user null if email already exist
     */
    @CrossOrigin("http://localhost:4200")
    @PostMapping("/")
    public ResponseEntity<UserDto> newUser (@RequestBody UserDto userDto){
        User user = userService.saveNewUser(userMapper.toEntity(userDto));
        if (user != null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/")
    @Operation(summary = "get Users", description = "Get list of users")
    public ResponseEntity<List<UserDto>> listUser() {
        List<User> users = this.userService.findAll();
        return new ResponseEntity<>(userMapper.toDto(users), HttpStatus.OK);

    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> userId(@PathVariable int id) {
        User user = this.userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }
    @CrossOrigin("http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        User user = this.userService.update(id, userDto);
        return new ResponseEntity<>(userMapper.toDto(user),HttpStatus.OK);
    }
    @CrossOrigin("http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO: mettre sécurité si le user n'existe pas car actuellement si le user n'existe pas on a une erreur 500

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<User>login(@RequestBody UserDto userDto) {
        System.out.println(userDto );
    User user = userService.loginUser(userDto.email, userDto.password);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK) ;
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/token")
    public ResponseEntity<UserDto> userToken(@RequestHeader("Authorization") String token) {
        if (token != null) {
           User user = userService.findUserByToken(token);
           return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //TODO : mettre en place la verification du token

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/verify/auth")
    public ResponseEntity <Boolean> verificationToken(@RequestHeader("Authorization") String token){
;
        if(token != null){
            Long expiresIn = userService.verificationToken(token);
            if(expiresIn != null && expiresIn > 0 ){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(false,HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(false,HttpStatus.NOT_FOUND);
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/verify/admin")
    public ResponseEntity<Boolean> verificationAdmin (@RequestHeader("Authorization") String token){
        if(token != null){
        Boolean isAdmin = userService.verificationAdmin(token);
        if (isAdmin == true){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
            return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(false,HttpStatus.FORBIDDEN);
    }
}
