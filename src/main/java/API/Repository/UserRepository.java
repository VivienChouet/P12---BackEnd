package API.Repository;

import API.Entity.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByFirstName(String user);

    User findByEmail(String email);
}
