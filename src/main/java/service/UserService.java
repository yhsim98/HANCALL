package service;


import domain.User;
import exception.NotFoundException;

import java.util.HashMap;

public interface UserService {
    User userRegister(User user) throws Exception;
    User getUserById(String token) throws Exception;
    HashMap login(User user) throws Exception;
    void deleteUser(String token) throws Exception;
    User updateUser(String token, User user) throws Exception;
}
