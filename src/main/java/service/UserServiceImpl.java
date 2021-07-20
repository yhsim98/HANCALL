package service;

import domain.User;
import exception.ConflictException;
import exception.EntityNotFoundException;
import exception.UnauthorizedException;
import exception.errorcode.ErrorCode;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import repository.UserMapper;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
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

        if(userMapper.checkEmail(user.getEmail()) == 1){
            throw new ConflictException(ErrorCode.EMAIL_DUPLICATION);
        }
        
        if(userMapper.checkNickname(user.getNickname()) == 1){
            throw new ConflictException(ErrorCode.NICKNAME_DUPLICATION);
        }
        
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));

        userMapper.insertUser(user);
        return userMapper.getUserById(user.getId());
    }

    @Override
    public User getUserById() throws Exception{
        User selectUser = userMapper.getUserById(jwtUtil.getUserIdFromJWT(getTokenFromServlet()));

        if(selectUser == null){
            throw new EntityNotFoundException(ErrorCode.USER_NOT_FOUND);
        }

        return selectUser;
    }

    @Override
    public HashMap login(User user) throws Exception {
        User selectUser = userMapper.login(user);

        if(selectUser == null){
            throw new UnauthorizedException(ErrorCode.INVALID_EMAIL);
        }

        if(!BCrypt.checkpw(user.getPassword(), selectUser.getPassword())){
            throw new ConflictException(ErrorCode.INVALID_PASSWORD);
        }

        HashMap hashMap = new HashMap<>();
        hashMap.put("token", jwtUtil.genJsonWebToken(selectUser.getId()));
        return hashMap;
    }

    @Override
    public User updateUser(User user) throws Exception {
        Long id = jwtUtil.getUserIdFromJWT(getTokenFromServlet());
        User selectUser = userMapper.getUserById(id);

        if(selectUser == null || isDeleted(selectUser.getId())){
            throw new EntityNotFoundException(ErrorCode.USER_NOT_FOUND);
        }

        user.setId(id);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        userMapper.updateUser(user);

        return userMapper.getUserById(id);
    }

    @Override
    public void deleteUser() throws Exception {
        Long id = jwtUtil.getUserIdFromJWT(getTokenFromServlet());
        User selectUser = userMapper.getUserById(id);

        if(selectUser == null || isDeleted(selectUser.getId())){
            throw new EntityNotFoundException(ErrorCode.USER_NOT_FOUND);
        }

        userMapper.deleteUser(id);
    }

    @Override
    public boolean isDeleted(Long id) {
        return userMapper.isDeleted(id) == 0 ? false : true;
    }


    private String getTokenFromServlet(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("Authorization");
    }
}
