package service;

import domain.Comment;
import domain.Criteria;
import domain.PageMaker;
import exception.ForbiddenException;
import exception.EntityNotFoundException;
import exception.errorcode.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import repository.CommentMapper;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentMapper commentMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper, JwtUtil jwtUtil) {
        this.commentMapper = commentMapper;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public Map getCommentList(Long boardId, Criteria criteria) {

        PageMaker pageMaker = new PageMaker(criteria, commentMapper.countCommentNum(boardId));

        Map map = new HashMap();
        map.put("boardId", boardId);
        map.put("pageStart", criteria.getPageStart());
        map.put("perPageNum", criteria.getPerPageNum());

        Map result = new HashMap();
        result.put("pageMaker", pageMaker);
        result.put("data", commentMapper.getCommentList(map));

        return result;
    }

    @Override
    public void createComment(Comment comment, Long boardId) {
        String token = jwtUtil.getTokenFromServlet();

        comment.setWriter_Id(jwtUtil.getUserIdFromJWT(token));
        comment.setBoard_Id(boardId);

        commentMapper.insertComment(comment);
    }

    @Override
    public void deleteComment(Long commentId) throws Exception {
        Comment selectComment = commentMapper.getCommentById(commentId);
        String token = jwtUtil.getTokenFromServlet();

        if(selectComment == null){
            throw new EntityNotFoundException(ErrorCode.COMMENT_NOT_FOUND);
        }

        if(!jwtUtil.getUserIdFromJWT(token).equals(selectComment.getWriter_Id())){
            throw new ForbiddenException(ErrorCode.ACCESS_DENIED);
        }

        commentMapper.deleteComment(commentId);
    }

    @Override
    public void updateComment(Comment comment, Long commentId) throws Exception{
        Comment selectComment = commentMapper.getCommentById(commentId);
        String token = jwtUtil.getTokenFromServlet();

        if(selectComment == null){
            throw new EntityNotFoundException(ErrorCode.COMMENT_NOT_FOUND);
        }

        if(!jwtUtil.getUserIdFromJWT(token).equals(selectComment.getWriter_Id())){
            throw new ForbiddenException(ErrorCode.ACCESS_DENIED);
        }

        comment.setComment_Id(commentId);
        commentMapper.updateComment(comment);
    }

    @Override
    public void deleteAllCommentsInBoard(Long boardId) {
        commentMapper.deleteAllCommentsInBoard(boardId);
    }
}
