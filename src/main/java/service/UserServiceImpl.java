package service;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserMapper;
import util.JwtUtil;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
    }

    @Override
    public void userRegister(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User getUserById(String token) {
        return userMapper.getUserById(jwtUtil.getUserIdFromJWT(token));
    }

    @Override
    public HashMap<String, String> login(User user) {
        User tmp = userMapper.getUserByEmail(user.getEmail());

        if(tmp.getPassword() != user.getPassword()){

        }

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", jwtUtil.genJsonWebToken(tmp.getId()));
        return hashMap;
    }

    @Override
    public String test() {
        return userMapper.test();
    }
}
