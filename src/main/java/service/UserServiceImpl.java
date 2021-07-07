package service;

import domain.User;
import exception.ConflictException;
import exception.NotFoundException;
import exception.UnauthorizedException;
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
        User selectUser = userMapper.getUserByEmail(user.getEmail());

        if(selectUser != null && !isDeleted(selectUser.getId())){
            throw new ConflictException("이미 사용하고 있는 이메일입니다");
        }

        selectUser = userMapper.getUserByNickname(user.getNickname());
        
        if(selectUser != null && !isDeleted(selectUser.getId())){
            throw new ConflictException("이미 사용하고 있는 별명입니다");
        }
        
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));

        userMapper.insertUser(user);
        return userMapper.getUserById(user.getId());
    }

    @Override
    public User getUserById() throws Exception{
        User selectUser = userMapper.getUserById(jwtUtil.getUserIdFromJWT(getTokenFromServlet()));

        if("".equals(selectUser) || selectUser == null){
            throw new NotFoundException("존재하지 않는 계정입니다");
        }

        return selectUser;
    }

    @Override
    public HashMap<String, String> login(User user) throws Exception {
        User selectUser = userMapper.getUserByEmail(user.getEmail());

        if(("".equals(selectUser) || selectUser == null) || isDeleted(selectUser.getId())){
            throw new NotFoundException("존재하지 않는 계정입니다");
        }

        if(!BCrypt.checkpw(user.getPassword(), selectUser.getPassword())){
            throw new UnauthorizedException("비밀번호가 틀립니다");
        }

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", jwtUtil.genJsonWebToken(selectUser.getId()));
        return hashMap;
    }

    @Override
    public User updateUser(User user) throws Exception {
        Long id = jwtUtil.getUserIdFromJWT(getTokenFromServlet());
        User selectUser = userMapper.getUserById(id);

        if(("".equals(selectUser) || selectUser == null) || isDeleted(selectUser.getId())){
            throw new NotFoundException("존재하지 않는 계정입니다");
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

        if(("".equals(selectUser) || selectUser == null) || isDeleted(selectUser.getId())){
            throw new NotFoundException("존재하지 않는 계정입니다");
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
