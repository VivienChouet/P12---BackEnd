package API.Service;

import API.Entity.DTO.UserDto;
import API.Entity.Entity.User;
import API.Repository.UserRepository;
import API.Utility.JWT;
import API.Utility.LoggingController;
import com.sun.xml.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static API.Utility.JWT.decodeJWT;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Save New User
     * @param user
     * @return
     */
    public User saveNewUser(User user) {
        User save = new User();
        logger.info("test save = " + user);
        // todo-me mettre en place un systeme de verification de mail
        save.setPassword(passwordEncoder.encode(user.getPassword()));
        save.setFirstName(user.getFirstName());
        save.setLastName(user.getLastName());
        save.setEmail(user.getEmail());
        userRepository.save(save);
        logger.info("save new user = " + save.getFirstName());
        return save;
    }

    /**
     * Find User By Id
     * @param id
     * @return
     */
    public User findById (int id){
        User user = new User();
        user = userRepository.getById(id);
        return user;
    }

    /**
     * Delete User By Id
     * @param id
     */
    public void delete(int id){
        logger.info("delete user id = " + id);
        userRepository.delete(findById(id));
    }

    /**
     * Login Check
     * @param user
     * @param password
     * @return
     */
    public Boolean loginUser(String user, String password) {
        logger.info("check login in progress");
        User user1 = userRepository.findByFirstName(user);
        if (passwordEncoder.matches(password, user1.getPassword())) {
            logger.info("Check login success");
            return true;
        }
        logger.info("check login failed");
        return false;
    }

    /**
     * Find User With Token
     * @param token
     * @return
     */
    public User findUserByToken(String token) {
        String jwtToken = token.replace("Bearer ", "");
        String username = decodeJWT(jwtToken).getSubject();
        User user = new User();
        user = userRepository.findByFirstName(username);
        return user;
    }

    /**
     * Check Email
     * @param email
     * @return
     */
    public boolean emailExists(final String email) {
        logger.info("find if email exist");
        return userRepository.findByEmail(email) == null;
    }

    /**
     * Update User
     * @param id
     * @param userDTO
     * @return
     */
    public User update(int id, UserDto userDTO) {
        User user = this.findById(id);
        if (userDTO.firstName != null) {
            user.setFirstName(userDTO.firstName);
        }
        if (userDTO.lastName != null) {
            user.setLastName(userDTO.lastName);
        }
        if (userDTO.email != null) {
            user.setEmail(userDTO.email);
        }
        if (userDTO.password != null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        userRepository.save(user);
        String token = JWT.createJWT(user.getFirstName(), 60000);
        user.setToken(token);
        logger.info("user : " + user.getFirstName() + " mis a jour");
        return user;
    }
}
