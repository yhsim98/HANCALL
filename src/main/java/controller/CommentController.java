package controller;

import annotation.Auth;
import domain.Comment;
import domain.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.CommentService;

import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    //댓글 생성
    @Auth
    @ResponseBody
    @RequestMapping(value = "/{boardId}", method = RequestMethod.POST)
    public ResponseEntity createComment(@RequestBody Comment comment,
                                        @PathVariable("boardId") Long boardId){
        commentService.createComment(comment, boardId);
        return new ResponseEntity("created", HttpStatus.CREATED);
    }

    //댓글 삭제
    @Auth
    @ResponseBody
    @RequestMapping(value="/{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@PathVariable("commentId") Long commentId) throws Exception{
        commentService.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //댓글 수정
    @Auth
    @ResponseBody
    @RequestMapping(value="/{commentId}", method = RequestMethod.PATCH)
    public ResponseEntity updateComment(@RequestBody Comment comment,
                                        @PathVariable Long commentId) throws Exception{

        commentService.updateComment(comment, commentId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //댓글 조회
    @ResponseBody
    @RequestMapping(value="/{boardId}", method = RequestMethod.GET)
    public ResponseEntity<Map> getCommentList(@PathVariable("boardId") Long boardId,
                                              @ModelAttribute Criteria criteria){
        return new ResponseEntity(commentService.getCommentList(boardId, criteria), HttpStatus.OK);
    }
}
