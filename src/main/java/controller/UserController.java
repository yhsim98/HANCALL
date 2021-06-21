package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hancall/user")
public class UserController {

    @RequestMapping(value="", method = RequestMethod.POST)
    @ResponseBody
    public String register(){
        return "";
    }

}
