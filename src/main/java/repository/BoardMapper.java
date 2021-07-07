package repository;

import domain.Board;
import domain.Participant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {
    void insertBoard(Board board);
    List<Board> getBoardList();
    Board getBoardById(Long boardId);
    void updateBoard(Board board);
    List<Board> searchBoard(Board board);
    void deleteBoard(Long boardId);
    List<Participant> getParticipantList(Long boardId);
}
