package service;


import domain.User;

public interface UserService {
    public String test();
    public void userRegister(User user);
    public User getUserById(String token);
}
