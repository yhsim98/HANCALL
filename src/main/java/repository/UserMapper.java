package repository;

import domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int checkEmail(String email);
    void insertUser(User user);
    User getUserById(Long id);
    void deleteUser(User user);
    void updateUser(User user);
    int isDeleted(Long id);
    int checkNickname(String nickname);
    User login(User user);
}
