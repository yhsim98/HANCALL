package service;

import domain.User;
import exception.ConflictException;
import exception.NotFoundException;
import exception.UnauthorizedException;
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
    public User userRegister(User user) throws Exception{
        if(userMapper.getUserByEmail(user.getEmail()) != null){
            throw new ConflictException("이미 사용하고 있는 이메일입니다");
        }

        userMapper.insertUser(user);
        return userMapper.getUserById(user.getId());
    }

    @Override
    public User getUserById(String token) throws Exception{
        User selectUser = userMapper.getUserById(jwtUtil.getUserIdFromJWT(token));

        if(selectUser == null){
            throw new NotFoundException("존재하지 않는 계정입니다");
        }

        return selectUser;
    }

    @Override
    public HashMap<String, String> login(User user) throws Exception {
        User selectUser = userMapper.getUserByEmail(user.getEmail());

        if(selectUser == null){
            throw new NotFoundException("존재하지 않는 계정입니다");
        }

        if(selectUser.getPassword() != user.getPassword()){
            throw new UnauthorizedException("비밀번호가 틀립니다");
        }

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", jwtUtil.genJsonWebToken(selectUser.getId()));
        return hashMap;
    }

    @Override
    public User updateUser(String token, User user) throws Exception {
        Long id = jwtUtil.getUserIdFromJWT(token);

        if(userMapper.getUserById(id) == null){
            throw new NotFoundException("존재하지 않는 계정입니다");
        }

        user.setId(id);
        userMapper.updateUser(user);

        return userMapper.getUserById(id);
    }

    @Override
    public void deleteUser(String token) throws Exception {
        Long id = jwtUtil.getUserIdFromJWT(token);
        User selectUser = userMapper.getUserById(id);

        if(selectUser == null){
            throw new NotFoundException("존재하지 않는 계정입니다");
        }

        userMapper.deleteUser(id);
    }
}
