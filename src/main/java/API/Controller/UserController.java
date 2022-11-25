package API.Controller;

import API.Entity.DTO.UserDto;
import API.Entity.Entity.User;
import API.Entity.Mapper.UserMapper;
import API.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;


    /**
     * Save a New User
     *
     * @param userDto
     * @return user null if email already exist
     */

    @PostMapping("/")
    public ResponseEntity<UserDto> newUser(@RequestBody UserDto userDto) {
        User user = userService.saveNewUser(userMapper.toEntity(userDto));
        if (user != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/")
    @Operation(summary = "get Users", description = "Get list of users")
    public ResponseEntity<List<UserDto>> listUser() {
        List<User> users = this.userService.findAll();
        return new ResponseEntity<>(userMapper.toDto(users), HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> userId(@PathVariable int id) {
        User user = this.userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        User user = this.userService.update(id, userDto);
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable int id, @RequestHeader("Authorization") String token) {
        if (userService.verificationAdmin(token)) {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    //TODO: mettre sécurité si le user n'existe pas car actuellement si le user n'existe pas on a une erreur 500


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserDto userDto) {
        User user = userService.loginUser(userDto.email, userDto.password);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/token")
    public ResponseEntity<UserDto> userToken(@RequestHeader("Authorization") String token) {
        if (token != null) {
            User user = userService.findUserByToken(token);
            return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //TODO : mettre en place la verification du token


    @GetMapping("/verify/auth")
    public ResponseEntity<Boolean> verificationToken(@RequestHeader("Authorization") String token) {
        ;
        if (token != null) {
            Long expiresIn = userService.verificationToken(token);
            if (expiresIn != null && expiresIn > 0) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(false, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/verify/admin")
    public ResponseEntity<Boolean> verificationAdmin(@RequestHeader("Authorization") String token) {
        if (token != null) {
            Boolean isAdmin = userService.verificationAdmin(token);
            if (isAdmin == true) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
    }

    @PostMapping("/verify/author")
    public ResponseEntity<Boolean> verificationAuthor(@RequestHeader("Authorization") String token, @RequestBody int id) {
        if (token != null) {
            Boolean isAuthor = userService.verificationAuthor(token, id);
            if (isAuthor) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
