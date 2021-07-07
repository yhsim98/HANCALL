package repository;

import domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    List<Comment> getCommentList(Long boardId);
    void insertComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(Long commentId);
    Comment getCommentById(Long commentId);
    void deleteBoardComments(Long boardId);
}
