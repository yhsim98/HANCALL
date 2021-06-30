package controller;

import annotation.Auth;
import domain.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.UserService;
import util.JwtUtil;

import java.net.URI;
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
    public ResponseEntity register(@RequestBody User user) throws Exception {
        User created = userService.userRegister(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setLocation(location);

        return new ResponseEntity(created, responseHeader, HttpStatus.CREATED);
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
    public ResponseEntity<User> userInquiry(@RequestHeader(value="Authorization") String token) throws Exception{
        return new ResponseEntity<>(userService.getUserById(token), HttpStatus.OK);
    }

    //회원 정보 수정
    @Auth
    @ResponseBody
    @RequestMapping(value="/my", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestHeader(value="Authorization") String token,
                                           @RequestBody User user) throws Exception{
        return new ResponseEntity(userService.updateUser(token, user), HttpStatus.CREATED);
    }

    //회원 탈퇴
    @Auth
    @ResponseBody
    @RequestMapping(value = "/my", method = RequestMethod.DELETE)
    public ResponseEntity secession(@RequestHeader(value="Authorization") String token) throws Exception{
        userService.deleteUser(token);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
