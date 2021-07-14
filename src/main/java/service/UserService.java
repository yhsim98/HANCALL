package service;


import domain.User;

import java.util.HashMap;

public interface UserService {
    User userRegister(User user) throws Exception;
    User getUserById() throws Exception;
    HashMap login(User user) throws Exception;
    void deleteUser() throws Exception;
    User updateUser(User user) throws Exception;
    boolean isDeleted(Long id);
}
