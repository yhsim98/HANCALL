package repository;

import domain.Board;
import domain.Participant;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {
    void insertBoard(Board board);
    List<Board> getBoardList(@Param("start")Long start, @Param("pageSize")Long pageSize);
    Board getBoardById(Long boardId);
    void updateBoard(Board board);
    List<Board> searchBoard(Board board);
    void deleteBoard(Long boardId);
    int getMaxParticipants(Long boardId);
}
