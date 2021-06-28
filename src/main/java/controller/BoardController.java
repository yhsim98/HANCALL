package controller;

import annotation.Auth;
import domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.BoardService;

import java.net.URI;
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
    public ResponseEntity<Board> createBoard(
            @RequestBody Board board){

        Board created = boardService.createBoard(board);
        System.out.println(created.getBoard_Id());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{board_Id}")
                .buildAndExpand(created.getBoard_Id())
                .toUri();

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setLocation(location);

        return new ResponseEntity(created, responseHeader, HttpStatus.CREATED);
    }


    //게시물 목록 조회
    @ResponseBody
    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity<List<Board>> getBoardList(){
        return new ResponseEntity<>(boardService.getBoardList(), HttpStatus.OK);
    }

    //게시글 검색
    @ResponseBody
    @RequestMapping(value="/search", method=RequestMethod.GET)
    public ResponseEntity searchBoard(@RequestBody Board board){
        return new ResponseEntity(boardService.searchBoard(board), HttpStatus.OK);
    }


    //게시물 열람
    @ResponseBody
    @RequestMapping(value="/{boardId}", method = RequestMethod.GET)
    public ResponseEntity readBoard(@PathVariable("boardId") Long boardId){
        return new ResponseEntity(boardService.getBoard(boardId), HttpStatus.OK);
    }

    //게시글 수정
    @Auth
    @ResponseBody
    @RequestMapping(value="/{boardId}", method=RequestMethod.PATCH)
    public ResponseEntity updateBoard(
            @PathVariable("boardId") Long boardId,
            @RequestBody Board board,
            @RequestHeader("Authorization") String token){
        boardService.updateBoard(board, boardId, token);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    //게시글 삭제
    @Auth
    @ResponseBody
    @RequestMapping(value="/{boardId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBoard(@PathVariable("boardId") Long boardId, @RequestHeader("Authorization") String token){
        boardService.deleteBoard(boardId, token);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
