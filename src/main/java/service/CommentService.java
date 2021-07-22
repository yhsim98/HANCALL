package service;

import domain.Comment;
import domain.Criteria;

import java.util.Map;

public interface CommentService {
    Map getCommentList(Long boardId, Criteria criteria);
    void createComment(Comment comment, Long boardId);
    void deleteComment(Long commentId) throws Exception;
    void updateComment(Comment comment, Long commentId) throws Exception;
    void deleteAllCommentsInBoard(Long boardId);
}
