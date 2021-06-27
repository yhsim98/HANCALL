package service;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public void userRegister(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User getUserById(String token) {
        Long id = 1L;
        return userMapper.getUserById(id);
    }

    @Override
    public String test() {
        return userMapper.test();
    }
}
