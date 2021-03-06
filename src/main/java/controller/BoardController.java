package controller;

import annotation.Auth;
import domain.Board;
import domain.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BoardService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    //게시글 생성
    @Auth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Board> createBoard(@Valid @RequestBody Board board) throws Exception {
        return new ResponseEntity(boardService.createBoard(board), HttpStatus.CREATED);
    }

    //게시물 목록 조회
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Map> getBoardList(@RequestParam(value="lastBoardId", required = false) Long lastBoardId) throws Exception {
        return new ResponseEntity(boardService.getBoardList(lastBoardId), HttpStatus.OK);
    }

    //게시글 검색
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<Map> searchBoard(@RequestParam(value="startingPoint", required = false) String startingPoint,
                                           @RequestParam(value="destination", required = false) String destination,
                                           @RequestParam(value="lastBoardId", required = false) Long lastBoardId) throws Exception {

        return new ResponseEntity(boardService.searchBoard(startingPoint, destination, lastBoardId), HttpStatus.OK);
    }


    //게시물 열람
    @ResponseBody
    @RequestMapping(value = "/{boardId}", method = RequestMethod.GET)
    public ResponseEntity<Map> readBoard(@PathVariable("boardId") Long boardId) throws Exception {
        return new ResponseEntity(boardService.getBoard(boardId), HttpStatus.OK);
    }

    //게시글 수정
    @Auth
    @ResponseBody
    @RequestMapping(value = "/{boardId}", method = RequestMethod.PATCH)
    public ResponseEntity updateBoard(@Valid @RequestBody Board board,
                                      @PathVariable Long boardId) throws Exception {
        boardService.updateBoard(board, boardId);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    //게시글 삭제
    @Auth
    @ResponseBody
    @RequestMapping(value = "/{boardId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBoard(@PathVariable("boardId") Long boardId) throws Exception {
        boardService.deleteBoard(boardId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
