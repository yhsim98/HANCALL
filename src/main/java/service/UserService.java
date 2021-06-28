package service;


import domain.User;

import java.util.HashMap;

public interface UserService {
    public String test();
    public void userRegister(User user);
    public User getUserById(String token);
    public HashMap login(User user);
}
