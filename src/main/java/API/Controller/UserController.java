package API.Controller;

import API.Entity.DTO.UserDto;
import API.Entity.DTO.UserSecureDTO;
import API.Entity.Entity.User;
import API.Entity.Mapper.UserMapper;
import API.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
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

    @GetMapping("/verify/auth")
    public ResponseEntity<Boolean> verificationToken(@RequestHeader("Authorization") String token) {
        if (token.length() > 2) {
            Long expiresIn = userService.verificationToken(token);
            if (expiresIn != null && expiresIn > 0) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            if(token == null){
                return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity(false, HttpStatus.FORBIDDEN);
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
        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/verify/author/{id}")
    public ResponseEntity<Boolean> verificationAuthor(@RequestHeader("Authorization") String token, @PathVariable int id) {
        if (token != null) {
            Boolean isAuthor = userService.verificationAuthor(token, id);
            if (isAuthor == true) {
                return new ResponseEntity<>(true,HttpStatus.OK);
            }
            return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(false,HttpStatus.FORBIDDEN);
    }

    @GetMapping("/verify/getuserconnected")
    public ResponseEntity<UserSecureDTO> getUserConnected (@RequestHeader("Authorization") String token) {
        User user = userService.findUserByToken(token);
        UserSecureDTO userSecureDTO = userService.convertToSecure(user);
        return new ResponseEntity<>(userSecureDTO , HttpStatus.OK);
    }
}
