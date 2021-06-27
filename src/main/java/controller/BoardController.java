package controller;

import domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BoardService;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    
    //게시글 생성
    @ResponseBody
    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity createPost(
            @RequestBody Board board){
        boardService.createBoard(board);
        return new ResponseEntity("created", HttpStatus.CREATED);
    }


    //게시물 목록 조회
    @ResponseBody
    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity<List<Board>> getBoardList(){
        return new ResponseEntity<>(boardService.getBoardList(), HttpStatus.OK);
    }


    //게시물 열람
    @ResponseBody
    @RequestMapping(value="/{boardId}", method = RequestMethod.GET)
    public ResponseEntity readBoard(@PathVariable("boardId") Long boardId){
        return new ResponseEntity(boardService.getBoard(boardId), HttpStatus.OK);
    }


    @ResponseBody
    @RequestMapping(value="/{boardId}", method=RequestMethod.PUT)
    public ResponseEntity updateBoard(@PathVariable("boardId") String boardNum, @RequestBody Board board){
        boardService.updateBoard(board);
        return new ResponseEntity(HttpStatus.OK);
    }


    @ResponseBody
    @RequestMapping(value="", method=RequestMethod.GET)
    public ResponseEntity searchBoard(@RequestBody Board board){
        return new ResponseEntity(boardService.searchBoard(board), HttpStatus.OK);
    }



    @ResponseBody
    @RequestMapping(value="/{boardId}", method = RequestMethod.DELETE)
    public ResponseEntity deletePost(@PathVariable("boardId") String boardNum){
        return null;
    }
}
