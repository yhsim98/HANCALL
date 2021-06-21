package repository;

import domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    String test();
    User getUserByEmail(String email);
    void insertUser(User user);
    User getUserById(Long id);
}
