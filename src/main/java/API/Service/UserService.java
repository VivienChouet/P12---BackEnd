package API.Service;

import API.Entity.DTO.UserDto;
import API.Entity.Entity.User;
import API.Repository.UserRepository;
import API.Utility.Security.JWT;
import API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static API.Utility.Security.JWT.decodeJWT;

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
     * @return user if email not exist
     * @return null if email exist
     */
    public User saveNewUser(User user)
    {
        if(emailExists(user.getEmail())) {
            logger.info("new user = " + user.getFirstName());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return user;
        }
        else{
            logger.warn("email exist");
            return null;
        }
    }

    /**
     * Find User By Id
     * @param id
     * @return User
     */
    public User findById (int id){
        logger.info("find user by id : id " + id);
        User user = userRepository.getById(id);
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
     * Login Check by Email & Password
     * @param email
     * @param password
     * @return boolean
     */
    public Boolean loginUser(String email, String password) {
        logger.info("check login in progress");
        User user1 = userRepository.findByEmail(email);
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
     * @return user
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
     * @return boolean
     */
    public boolean emailExists(final String email) {
        logger.info("find if email exist");
        return userRepository.findByEmail(email) == null;
    }

    /**
     * Update User
     * @param id
     * @param userDTO
     * @return user
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
        String token = JWT.createJWT(user.getEmail(), 60000);
        user.setToken(token);
        logger.info("user : " + user.getFirstName() + " mis a jour");
        return user;
    }

    /**
     * List of All User
     * @return List<User>
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
