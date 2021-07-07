package service;

import domain.Comment;
import exception.NotFoundException;

import java.util.List;
import java.util.Map;

public interface CommentService {
    Map getCommentList(Long boardId);
    void createComment(Comment comment, Long boardId);
    void deleteComment(Long commentId) throws Exception;
    void updateComment(Comment comment, Long commentId) throws Exception;
    void deleteBoardComments(Long boardId);
}
