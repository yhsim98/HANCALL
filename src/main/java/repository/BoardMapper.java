package repository;

import domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {
    void insertBoard(Board board);
    List<Board> getBoardList();
    Board getBoardById(Long boardId);
    void updateBoard(Board board);
    List<Board> getBoardListByDestination(Board board);
    List<Board> getBoardListByStartingPoint(Board board);
    List<Board> getBoardListBySPAndDes(Board board);
    void deleteBoard(Long boardId);
}
