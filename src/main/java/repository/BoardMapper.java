package repository;

import domain.Board;
import domain.Criteria;
import domain.Participant;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface BoardMapper {
    void insertBoard(Board board);
    List<Board> getBoardList(Criteria criteria);
    Board getBoardById(Long boardId);
    void updateBoard(Board board);
    List<Board> searchBoard(HashMap hashMap);
    void deleteBoard(Long boardId);
    int getMaxParticipants(Long boardId);
    int countBoardNum();
}
