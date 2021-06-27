package controller;

import domain.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;

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

    
    // 토큰이 있어야만 사용가능한 조회 api ( 로그인 )
    @ApiOperation(value="사용자 정보 조회", notes="토큰의 id를 사용하여 사용자 정보 조회")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<User> userInquiry(@RequestHeader(value="Authorization") String token) throws Exception{
        return new ResponseEntity<User>(userService.getUserById(token), HttpStatus.OK);
    }


    // 토큰을 발급하는 로그인 api
    @ApiOperation(value="사용자 로그인", notes="사용자 정보를 받고 토큰을 반환")
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody User user) throws Exception{
        return null;
    }
}
