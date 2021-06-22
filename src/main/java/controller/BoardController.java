package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/post")
public class BoardController {



    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity createPost(){

        return new ResponseEntity("", HttpStatus.CREATED);
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity getPostsList(){

    }

    @RequestMapping(value="/{num}", method = RequestMethod.GET)
    public ResponseEntity getPost(@PathVariable("num") String boardNum){

    }

    @RequestMapping(value="/{num}", method = RequestMethod.DELETE)
    public ResponseEntity deletePost(@PathVariable("num") String boardNum){

    }

}
