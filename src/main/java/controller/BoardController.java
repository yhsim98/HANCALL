package controller;

import annotation.Auth;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    
    //게시글 생성
    @Auth
    @ResponseBody
    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity<Map> createBoard(@RequestBody Board board) throws Exception{

        Board created = boardService.createBoard(board);

        Map result = new HashMap();

        result.put("data", created);
        result.put("result", Boolean.TRUE);

        return new ResponseEntity(result, HttpStatus.CREATED);
    }


    //게시물 목록 조회
    @ResponseBody
    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity<Map> getBoardList() throws Exception{

        List<Board> boardList = boardService.getBoardList();

        Map result = new HashMap();

        result.put("data", boardList);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    //게시글 검색
    @ResponseBody
    @RequestMapping(value="/search", method=RequestMethod.GET)
    public ResponseEntity<Map> searchBoard(@RequestBody Board board) throws Exception{

        List<Board> searchList = boardService.searchBoard(board);

        Map result = new HashMap();

        result.put("data", searchList);

        return new ResponseEntity(result, HttpStatus.OK);
    }


    //게시물 열람
    @ResponseBody
    @RequestMapping(value="/{boardId}", method = RequestMethod.GET)
    public ResponseEntity<Map> readBoard(@PathVariable("boardId") Long boardId) throws Exception{
        Board selectBoard = boardService.getBoard(boardId);

        Map result = new HashMap();

        result.put("data", selectBoard);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    //게시글 수정
    @Auth
    @ResponseBody
    @RequestMapping(value="/{boardId}", method=RequestMethod.PUT)
    public ResponseEntity updateBoard(
            @PathVariable("boardId") Long boardId,
            @RequestBody Board board) throws Exception {

        boardService.updateBoard(board, boardId);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    //게시글 삭제
    @Auth
    @ResponseBody
    @RequestMapping(value="/{boardId}", method = RequestMethod.POST)
    public ResponseEntity deleteBoard(@PathVariable("boardId") Long boardId) throws Exception {
        boardService.deleteBoard(boardId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
