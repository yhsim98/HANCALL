package repository;

import domain.Board;
import domain.Criteria;
import domain.Participant;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BoardMapper {
    void insertBoard(Board board);
    List<Board> getBoardList(Criteria criteria);
    Board getBoardById(Long boardId);
    void updateBoard(Board board);
    List<Board> searchBoard(Map map);
    void deleteBoard(Long boardId);
    int getMaxParticipants(Long boardId);
    int countBoardNum();
}
