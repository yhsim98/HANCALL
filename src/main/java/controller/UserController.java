package controller;

import annotation.Auth;
import domain.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import util.JwtUtil;

import java.util.HashMap;
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
    public ResponseEntity register(@RequestBody User user){
        userService.userRegister(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // 유저 자신 정보조회
    @Auth
    @ResponseBody
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public ResponseEntity<User> userInquiry(@RequestHeader(value="Authorization") String token) throws Exception{
        System.out.println(token);
        return new ResponseEntity<User>(userService.getUserById(token), HttpStatus.OK);
    }


    // 토큰을 발급하는 로그인 api
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map> login(@RequestBody User user) throws Exception{
        return new ResponseEntity(userService.login(user), HttpStatus.OK);
    }
}
