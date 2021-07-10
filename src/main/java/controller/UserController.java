package controller;

import annotation.Auth;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    //유저 생성
    @RequestMapping(value="", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> register(@RequestBody User user) throws Exception {
        User created = userService.userRegister(user);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // 토큰을 발급하는 로그인 api
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map> login(@RequestBody User user) throws Exception{
        return new ResponseEntity(userService.login(user), HttpStatus.OK);
    }

    // 유저 자신 정보조회
    @Auth
    @ResponseBody
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public ResponseEntity<User> userInquiry() throws Exception{
        return new ResponseEntity<>(userService.getUserById(), HttpStatus.OK);
    }

    //회원 정보 수정
    @Auth
    @ResponseBody
    @RequestMapping(value="/my", method = RequestMethod.PATCH)
    public ResponseEntity<User> updateUser(@RequestBody User user) throws Exception{
        return new ResponseEntity(userService.updateUser(user), HttpStatus.CREATED);
    }

    //회원 탈퇴
    @Auth
    @ResponseBody
    @RequestMapping(value = "/my", method = RequestMethod.DELETE)
    public ResponseEntity secession() throws Exception{
        userService.deleteUser();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
