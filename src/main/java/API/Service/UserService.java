package API.Service;

import API.Entity.Entity.User;
import API.Repository.UserRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public User saveNewUser(User user) {
        User save = new User();
        // todo-me mettre en place un systeme de verification de mail
        save.setPassword(passwordEncoder.encode(user.getPassword()));
        return save;
    }
}
