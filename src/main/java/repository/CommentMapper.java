package repository;

import domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentMapper {
    List<Comment> getCommentList(Map map);
    void insertComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(Long commentId);
    Comment getCommentById(Long commentId);
    void deleteAllCommentsInBoard(Long boardId);
    int countCommentNum(Long boardId);
}
