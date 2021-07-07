package repository;

import domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User getUserByEmail(String email);
    void insertUser(User user);
    User getUserById(Long id);
    void deleteUser(Long id);
    void updateUser(User user);
    int isDeleted(Long id);
    User getUserByNickname(String nickname);
}
