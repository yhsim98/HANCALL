package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

@Controller
@RequestMapping("/hancall/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    @ResponseBody
    public String register(){
        return "";
    }

    @RequestMapping(value="/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        return userService.test();
    }
}
