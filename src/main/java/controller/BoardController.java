package controller;

import domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @ResponseBody
    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity createPost(
            @RequestBody Board board){

        boardService.createBoard(board);

        return new ResponseEntity("created", HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity<Board> getPostsList(){
        return null;
    }

    @ResponseBody
    @RequestMapping(value="/{boardNum}", method = RequestMethod.GET)
    public ResponseEntity getPost(@PathVariable("boardNum") String boardNum){
        return null;
    }

    @ResponseBody
    @RequestMapping(value="/{boardNum}", method = RequestMethod.DELETE)
    public ResponseEntity deletePost(@PathVariable("boardNum") String boardNum){
        return null;
    }

}
